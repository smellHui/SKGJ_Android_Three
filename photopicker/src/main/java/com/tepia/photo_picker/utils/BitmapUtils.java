package com.tepia.photo_picker.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author:xch
 * Date:2019/12/10
 * Description:
 */
public class BitmapUtils {

    /**
     * 把View画成Bitmap
     *
     * @param view 要处理的View
     * @return Bitmap
     */
    public static Bitmap createBitmapFromView(HorizontalScrollView view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getChildAt(0).getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
//    public static Bitmap createBitmapFromView(View view) {
//        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        int src_w = bitmap.getWidth();
//        int src_h = bitmap.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.postScale(2f, 2f);
//        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
//        Log.e("newWidth", "newWidth" + dstbmp.getWidth());
//        Log.e("newHeight", "newHeight" + dstbmp.getHeight());
//        Canvas canvas = new Canvas(dstbmp);
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.width = src_w * 2;
//        layoutParams.height = src_h * 2;
//        view.setLayoutParams(layoutParams);
//        view.draw(canvas);
//        Log.e("newHeight", "view" + view.getWidth());
//        Log.e("newHeight", "view" + view.getHeight());
//        return dstbmp;
//    }


    public static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, float paddingLeft, float paddingTop) {
        ///1.加载位图
        //String path = Environment.getExternalStorageDirectory().toString()+"/DCIM/device.png";
        //inputStream is = new FileInputStream(path)
        ////2.为位图设置100K的缓存
        //BitmapFactory.Options opts=new BitmapFactory.Options();
        //opts.inTempStorage = new byte[100 * 1024];
        ////3.设置位图颜色显示优化方式
        ////ALPHA_8：每个像素占用1byte内存（8位）
        ////ARGB_4444:每个像素占用2byte内存（16位）
        ////ARGB_8888:每个像素占用4byte内存（32位）
        ////RGB_565:每个像素占用2byte内存（16位）
        ////Android默认的颜色模式为ARGB_8888，这个颜色模式色彩最细腻，显示质量最高。但同样的，占用的内存//也最大。也就意味着一个像素点占用4个字节的内存。我们来做一个简单的计算题：320024004 bytes //=30M。如此惊人的数字！哪怕生命周期超不过10s，Android也不会答应的。
        //opts.inPreferredConfig = Bitmap.Config.RGB_565;
        ////4.设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
        //opts.inPurgeable = true;
        ////5.设置位图缩放比例
        ////width，hight设为原来的四分一（该参数请使用2的整数倍）,这也减小了位图占用的内存大小；例如，一张//分辨率为20481536px的图像使用inSampleSize值为4的设置来解码，产生的Bitmap大小约为//512384px。相较于完整图片占用12M的内存，这种方式只需0.75M内存(假设Bitmap配置为//ARGB_8888)。
        //opts.inSampleSize = 4;
        ////6.设置解码位图的尺寸信息
        //opts.inInputShareable = true;
        ////7.解码位图
        //Bitmap btp =BitmapFactory.decodeStream(is,null, opts);
        ////8.显示位图
        //preview.setImageBitmap(bitmap);
        //
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, paint);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, width - watermark.getWidth(), height - watermark.getHeight(), paint);
        // 保存
        canvas.save();
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     * @param context
     *@param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }


    /**
     * 保存Bitmap到指定文件夹
     *
     * @param act      上下文
     * @param dirPath  文件夹全路径
     * @param bitmap   bitmap
     * @param callBack 保存图片后的回调，回调已经处于UI线程
     */
    public static void saveBitmapToDir(final Activity act, final String dirPath, final Bitmap bitmap, final SaveBitmapCallBack callBack) {
        new Thread(() -> {
            if (bitmap == null || dirPath == null) {
                act.runOnUiThread(callBack::onCreateDirFailed);
                return;
            }
            try {
                File dirF = new File(dirPath);
                FileOutputStream fos = null;
                fos = new FileOutputStream(dirF);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                act.runOnUiThread(() -> callBack.onSuccess(dirF));
            } catch (final IOException e) {
                act.runOnUiThread(() -> callBack.onIOFailed(e));

            }
        }).start();

    }

    public static void addWaterOnPhoto(Activity activity, String photoPath, HorizontalScrollView waterView, SaveBitmapCallBack callBack) {
        Glide.with(activity).asBitmap().load(photoPath).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                Bitmap waterBitmap = createBitmapFromView(waterView);
                Bitmap newBitmap = createWaterMaskBitmap(bitmap, waterBitmap, 0, 0);
                saveBitmapToDir(activity, photoPath, newBitmap, callBack);
            }
        });
    }

    public interface SaveBitmapCallBack {
        void onSuccess(File file);

        void onIOFailed(IOException exception);

        void onCreateDirFailed();
    }
}
