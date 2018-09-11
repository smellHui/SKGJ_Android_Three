package com.tepia.photo_picker.fragment;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.bumptech.glide.Glide;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.tepia.photo_picker.R;
import com.tepia.photo_picker.adapter.PhotoPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker fragment                      *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class ImagePagerFragment extends Fragment {

    public final static String ARG_PATH = "PATHS";
    public final static String ARG_CURRENT_ITEM = "ARG_CURRENT_ITEM";
    private ArrayList<String> paths;
    private ViewPager mViewPager;
    private PhotoPagerAdapter mPagerAdapter;
    public final static long ANIM_DURATION = 200L;
    public final static String ARG_THUMBNAIL_TOP = "THUMBNAIL_TOP";
    public final static String ARG_THUMBNAIL_LEFT = "THUMBNAIL_LEFT";
    public final static String ARG_THUMBNAIL_WIDTH = "THUMBNAIL_WIDTH";
    public final static String ARG_THUMBNAIL_HEIGHT = "THUMBNAIL_HEIGHT";
    public final static String ARG_HAS_ANIM = "HAS_ANIM";
    private int thumbnailTop = 0;
    private int thumbnailLeft = 0;
    private int thumbnailWidth = 0;
    private int thumbnailHeight = 0;
    private boolean hasAnim = false;
    private final ColorMatrix colorizerMatrix = new ColorMatrix();
    private int currentItem = 0;


    public static ImagePagerFragment newInstance(List<String> paths, int currentItem) {
        ImagePagerFragment imagePagerFragment = new ImagePagerFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PATH, paths.toArray(new String[paths.size()]));
        args.putInt(ARG_CURRENT_ITEM, currentItem);
        args.putBoolean(ARG_HAS_ANIM, false);
        imagePagerFragment.setArguments(args);
        return imagePagerFragment;
    }


    public static ImagePagerFragment newInstance(List<String> paths, int currentItem, int[] screenLocation, int thumbnailWidth, int thumbnailHeight) {
        ImagePagerFragment imagePagerFragment = newInstance(paths, currentItem);
        imagePagerFragment.getArguments().putInt(ARG_THUMBNAIL_LEFT, screenLocation[0]);
        imagePagerFragment.getArguments().putInt(ARG_THUMBNAIL_TOP, screenLocation[1]);
        imagePagerFragment.getArguments().putInt(ARG_THUMBNAIL_WIDTH, thumbnailWidth);
        imagePagerFragment.getArguments().putInt(ARG_THUMBNAIL_HEIGHT, thumbnailHeight);
        imagePagerFragment.getArguments().putBoolean(ARG_HAS_ANIM, true);
        return imagePagerFragment;
    }


    public void setPhotos(List<String> paths, int currentItem) {
        this.paths.clear();
        this.paths.addAll(paths);
        this.currentItem = currentItem;
        mViewPager.setCurrentItem(currentItem);
        mViewPager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paths = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String[] pathArr = bundle.getStringArray(ARG_PATH);
            paths.clear();
            if (pathArr != null) {
                paths = new ArrayList<>(Arrays.asList(pathArr));
            }
            hasAnim = bundle.getBoolean(ARG_HAS_ANIM);
            currentItem = bundle.getInt(ARG_CURRENT_ITEM);
            thumbnailTop = bundle.getInt(ARG_THUMBNAIL_TOP);
            thumbnailLeft = bundle.getInt(ARG_THUMBNAIL_LEFT);
            thumbnailWidth = bundle.getInt(ARG_THUMBNAIL_WIDTH);
            thumbnailHeight = bundle.getInt(ARG_THUMBNAIL_HEIGHT);
        }
        mPagerAdapter = new PhotoPagerAdapter(Glide.with(this), paths);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.picker_picker_fragment_image_pager, container, false);
        mViewPager = rootView.findViewById(R.id.vp_photos);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentItem);
        mViewPager.setOffscreenPageLimit(5);
        if (savedInstanceState == null && hasAnim) {
            ViewTreeObserver observer = mViewPager.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mViewPager.getViewTreeObserver().removeOnPreDrawListener(this);
                    // Figure out where the thumbnail and full size versions are, relative
                    // to the screen and each other
                    int[] screenLocation = new int[2];
                    mViewPager.getLocationOnScreen(screenLocation);
                    thumbnailLeft = thumbnailLeft - screenLocation[0];
                    thumbnailTop = thumbnailTop - screenLocation[1];
                    runEnterAnimation();
                    return true;
                }
            });
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                hasAnim = currentItem == position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return rootView;
    }


    /**
     * The enter animation scales the picture in from its previous thumbnail
     * size/location, colorizing it in parallel. In parallel, the background of the
     * activity is fading in. When the pictue is in place, the text description
     * drops down.
     */
    private void runEnterAnimation() {
        final long duration = ANIM_DURATION;
        ViewHelper.setPivotX(mViewPager, 0);
        ViewHelper.setPivotY(mViewPager, 0);
        ViewHelper.setScaleX(mViewPager, (float) thumbnailWidth / mViewPager.getWidth());
        ViewHelper.setScaleY(mViewPager, (float) thumbnailHeight / mViewPager.getHeight());
        ViewHelper.setTranslationX(mViewPager, thumbnailLeft);
        ViewHelper.setTranslationY(mViewPager, thumbnailTop);

        // Animate scale and translation to go from thumbnail to full size
        ViewPropertyAnimator.animate(mViewPager)
                .setDuration(duration)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator());

        // Fade in the black background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mViewPager.getBackground(), "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();

        // Animate a color filter to take the image from grayscale to full color.
        // This happens in parallel with the image scaling and moving into place.
        ObjectAnimator colorizer = ObjectAnimator.ofFloat(ImagePagerFragment.this,
                "saturation", 0, 1);
        colorizer.setDuration(duration);
        colorizer.start();

    }

    public void runExitAnimation(final Runnable endAction) {
        if (!getArguments().getBoolean(ARG_HAS_ANIM, false) || !hasAnim) {
            endAction.run();
            return;
        }
        final long duration = ANIM_DURATION;
        ViewPropertyAnimator.animate(mViewPager)
                .setDuration(duration)
                .setInterpolator(new AccelerateInterpolator())
                .scaleX((float) thumbnailWidth / mViewPager.getWidth())
                .scaleY((float) thumbnailHeight / mViewPager.getHeight())
                .translationX(thumbnailLeft)
                .translationY(thumbnailTop)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        endAction.run();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });

        // Fade out background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mViewPager.getBackground(), "alpha", 0);
        bgAnim.setDuration(duration);
        bgAnim.start();

        ObjectAnimator colorizer =
                ObjectAnimator.ofFloat(ImagePagerFragment.this, "saturation", 1, 0);
        colorizer.setDuration(duration);
        colorizer.start();
    }


    public void setSaturation(float value) {
        colorizerMatrix.setSaturation(value);
        ColorMatrixColorFilter colorizerFilter = new ColorMatrixColorFilter(colorizerMatrix);
        mViewPager.getBackground().setColorFilter(colorizerFilter);
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        paths.clear();
        paths = null;
        if (mViewPager != null) {
            mViewPager.setAdapter(null);
        }
    }
}
