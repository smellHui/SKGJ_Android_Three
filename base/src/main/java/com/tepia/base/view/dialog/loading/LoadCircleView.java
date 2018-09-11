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

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.tepia.base.R;
import com.tepia.base.utils.ResUtils;



/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:仿百度加载中view                                    *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.views                       *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                               *
 *************************************************************/
public class LoadCircleView extends View {
    /**
     * 第一个动画的索引
     */
    private int changeIndex = 0;
    /**
     * 圆的颜色值
     */
    private int[] colors = new int[]{
            ResUtils.getColor(R.color.color_load_red),
            ResUtils.getColor(R.color.color_load_purple),
            ResUtils.getColor(R.color.color_load_blue),
            ResUtils.getColor(R.color.color_load_yellow)};
    /**
     * 偏移量
     */
    private Float maxWidth = 60f;
    /**
     * 圆的半径 默认10f
     */
    private Float circleRadius = 10f;

    /**
     * 当前偏移的X坐标
     */
    private Float currentX = 1f;
    /**
     * 画笔
     */
    private Paint paint;
    /**
     * 属性动画
     */
    private ValueAnimator valueAnimator;
    /**
     * 持续时间
     */
    private int duration = 800;

    public LoadCircleView(Context context) {
        this(context, null);
    }

    public LoadCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadCircleView);
            circleRadius = typedArray.getFloat(R.styleable.LoadCircleView_circle_radius, circleRadius);
            duration = typedArray.getInt(R.styleable.LoadCircleView_duration, duration);
            typedArray.recycle();//记得回收
        }
        startAnimator();
    }

    /**
     * 位移动画
     */
    private void startAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0f, maxWidth, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentX = (Float) animation.getAnimatedValue();
                //执行刷新onDraw()
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                changePoint(changeIndex);
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    /**
     * 先执行动画的目标和中间停止的动画目标交换
     * （颜色切换）
     *
     * @param index 最先执行的动画的索引
     */
    private void changePoint(int index) {
        int temp = colors[2];
        colors[2] = colors[index];
        colors[index] = temp;
        changeIndex = (index == 0) ? 1 : 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        /**左边圆**/
        paint.setColor(colors[0]);
        canvas.drawCircle(centerX - currentX, centerY, circleRadius, paint);
        /**右边圆**/
        paint.setColor(colors[1]);
        canvas.drawCircle(centerX + currentX, centerY, circleRadius, paint);
        /**中间圆**/
        paint.setColor(colors[2]);
        canvas.drawCircle(centerX, centerY, circleRadius, paint);
    }

    //销毁View的时候回调
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }
}
