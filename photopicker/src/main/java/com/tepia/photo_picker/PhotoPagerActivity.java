package com.tepia.photo_picker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.githang.statusbar.StatusBarCompat;
import com.tepia.base.utils.ToastUtils;
import com.tepia.photo_picker.fragment.ImagePagerFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import static com.tepia.photo_picker.PhotoPicker.KEY_SELECTED_PHOTOS;
import static com.tepia.photo_picker.PhotoPicker.SHOW_PHOTOS;
import static com.tepia.photo_picker.PhotoPreview.EXTRA_CURRENT_ITEM;
import static com.tepia.photo_picker.PhotoPreview.EXTRA_PHOTOS;
import static com.tepia.photo_picker.PhotoPreview.EXTRA_SHOW_DELETE;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker                               *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class PhotoPagerActivity extends AppCompatActivity {

    private ImagePagerFragment pagerFragment;
    private ActionBar actionBar;
    private boolean showDelete;
    private boolean isdelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker_activity_photo_pager);
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.picker_colorPrimary), true);

        int currentItem = getIntent().getIntExtra(EXTRA_CURRENT_ITEM, 0);
        List<String> paths = getIntent().getStringArrayListExtra(EXTRA_PHOTOS);
        showDelete = getIntent().getBooleanExtra(EXTRA_SHOW_DELETE, true);
        if (pagerFragment == null) {
            pagerFragment =
                    (ImagePagerFragment) getSupportFragmentManager().findFragmentById(R.id.photoPagerFragment);
        }
        pagerFragment.setPhotos(paths, currentItem);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            updateActionBarTitle();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                actionBar.setElevation(25);
            }
        }
        pagerFragment.getViewPager().addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateActionBarTitle();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (showDelete) {
            getMenuInflater().inflate(R.menu.picker_menu_preview, menu);
//            menu.removeItem(R.id.delete);
        }else {
            getMenuInflater().inflate(R.menu.picker_menu_preview, menu);
            menu.removeItem(R.id.delete);
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if (isdelete) {
            intent.putExtra(KEY_SELECTED_PHOTOS, pagerFragment.getPaths());
        } else {
            intent.putExtra(SHOW_PHOTOS, pagerFragment.getPaths());
        }
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.delete) {
            isdelete = true;
            final int index = pagerFragment.getCurrentItem();
            final String deletedPath = pagerFragment.getPaths().get(index);
            Snackbar snackbar = Snackbar.make(pagerFragment.getView(), R.string.picker_deleted_a_photo,
                    Snackbar.LENGTH_LONG);
            if (pagerFragment.getPaths().size() <= 1) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.picker_confirm_to_delete)
                        .setPositiveButton(R.string.picker_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                pagerFragment.getPaths().remove(index);
                                pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
                                onBackPressed();
                            }
                        })
                        .setNegativeButton(R.string.picker_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();

            } else {
                snackbar.show();
                pagerFragment.getPaths().remove(index);
                pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
            }
            snackbar.setAction(R.string.picker_undo, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pagerFragment.getPaths().size() > 0) {
                        pagerFragment.getPaths().add(index, deletedPath);
                    } else {
                        pagerFragment.getPaths().add(deletedPath);
                    }
                    pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
                    pagerFragment.getViewPager().setCurrentItem(index, true);
                }
            });

            return true;
        }
        if (item.getItemId() == R.id.save){
            final int index = pagerFragment.getCurrentItem();
            String path = pagerFragment.getPaths().get(index);
            if (!path.contains("http")){
                ToastUtils.shortToast("图片已在本地！");
            }else {
                new AlertDialog.Builder(this)
                        .setTitle("保存图片到本地？")
                        .setPositiveButton(R.string.picker_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Glide.with(PhotoPagerActivity.this).asBitmap().load(path).listener(new RequestListener<Bitmap>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                        ToastUtils.shortToast("图片下载失败，请稍后再试！");
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                }).into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        saveImage(resource);
                                    }
                                });
                            }
                        })
                        .setNegativeButton(R.string.picker_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateActionBarTitle() {
        if (actionBar != null) {
            actionBar.setTitle(
                    getString(R.string.picker_image_index, pagerFragment.getViewPager().getCurrentItem() + 1,
                            pagerFragment.getPaths().size()));
        }
    }


    private void saveImage(Bitmap image) {
        String saveImagePath = null;
        Random random = new Random();
        String imageFileName = "JPEG_" + "down" + random.nextInt(10) + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES) + "test");

        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            saveImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fout = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(saveImagePath);
//            Toast.makeText(mContext, "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
//        return saveImagePath;
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        ToastUtils.shortToast("保存成功！");
    }
}
