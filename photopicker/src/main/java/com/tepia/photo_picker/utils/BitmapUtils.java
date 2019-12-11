package com.tepia.photo_picker.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tepia.photo_picker.widget.WaterView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    public static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, float paddingLeft, float paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
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

    public static void addWaterOnPhoto(Activity activity, String photoPath, View waterView, SaveBitmapCallBack callBack){
        Glide.with(activity).asBitmap().load(photoPath).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                Bitmap waterBitmap = createBitmapFromView(waterView);
                Bitmap newBitmap = createWaterMaskBitmap(bitmap, waterBitmap, 0, 0);
                saveBitmapToDir(activity, photoPath, newBitmap, callBack);
            }
        });
    }

    interface SaveBitmapCallBack {
        void onSuccess(File file);

        void onIOFailed(IOException exception);

        void onCreateDirFailed();
    }
}
