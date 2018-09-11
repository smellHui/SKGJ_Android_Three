/*
 * The MIT License (MIT)
 *
 * Copyright 2016 by OCN.YAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND TORT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tepia.base.view.dialog.loading;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;


/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:自定义指示器                                      *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.views                      *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public abstract class BaseIndicator extends Drawable implements Animatable {

    private HashMap<ValueAnimator,ValueAnimator.AnimatorUpdateListener> mUpdateListeners=new HashMap<>();

    private ArrayList<ValueAnimator> mAnimators;
    private int alpha = 255;
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    protected Rect drawBounds = ZERO_BOUNDS_RECT;

    private boolean mHasAnimators;

    private Paint mPaint=new Paint();

    public BaseIndicator(){
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public int getColor() {
        return mPaint.getColor();
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return PixelFormat.RGBA_8888;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public void draw(Canvas canvas) {
        draw(canvas,mPaint);
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract ArrayList<ValueAnimator> onCreateAnimators();

    @Override
    public void start() {
        ensureAnimators();

        if (mAnimators == null) {
            return;
        }
        if (isStarted()) {
            return;
        }
        startAnimators();
        invalidateSelf();
    }

    private void startAnimators() {
        for (int i = 0; i < mAnimators.size(); i++) {
            ValueAnimator animator = mAnimators.get(i);
            ValueAnimator.AnimatorUpdateListener updateListener=mUpdateListeners.get(animator);
            if (updateListener!=null){
                animator.addUpdateListener(updateListener);
            }

            animator.start();
        }
    }

    private void stopAnimators() {
        if (mAnimators!=null){
            for (ValueAnimator animator : mAnimators) {
                if (animator != null && animator.isStarted()) {
                    animator.removeAllUpdateListeners();
                    animator.end();
                }
            }
        }
    }

    private void ensureAnimators() {
        if (!mHasAnimators) {
            mAnimators = onCreateAnimators();
            mHasAnimators = true;
        }
    }

    @Override
    public void stop() {
        stopAnimators();
    }

    private boolean isStarted() {
        for (ValueAnimator animator : mAnimators) {
            return animator.isStarted();
        }
        return false;
    }

    @Override
    public boolean isRunning() {
        for (ValueAnimator animator : mAnimators) {
            return animator.isRunning();
        }
        return false;
    }

    /**
     *  Your should use this to add AnimatorUpdateListener when
     *  create animator , otherwise , animator doesn't work when
     *  the animation restart .
     * @param updateListener
     */
    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener){
        mUpdateListeners.put(animator,updateListener);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setDrawBounds(bounds);
    }

    public void setDrawBounds(Rect drawBounds) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
    }

    public void setDrawBounds(int left, int top, int right, int bottom) {
        this.drawBounds = new Rect(left, top, right, bottom);
    }

    public void postInvalidate(){
        invalidateSelf();
    }

    public Rect getDrawBounds() {
        return drawBounds;
    }

    public int getWidth(){
        return drawBounds.width();
    }

    public int getHeight(){
        return drawBounds.height();
    }

    public int centerX(){
        return drawBounds.centerX();
    }

    public int centerY(){
        return drawBounds.centerY();
    }

    public float exactCenterX(){
        return drawBounds.exactCenterX();
    }

    public float exactCenterY(){
        return drawBounds.exactCenterY();
    }

}
