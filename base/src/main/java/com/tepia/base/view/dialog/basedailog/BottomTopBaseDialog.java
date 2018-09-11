package com.tepia.base.view.dialog.basedailog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:基类                                             *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.views                      *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public abstract class BottomTopBaseDialog<T extends BottomTopBaseDialog<T>> extends BaseDialog<T> {

    protected View mAnimateView;
    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;
    protected Animation mInnerShowAnim;
    protected Animation mInnerDismissAnim;
    protected long mInnerAnimDuration = 350;
    protected boolean mIsInnerShowAnim;
    protected boolean mIsInnerDismissAnim;
    protected int mLeft, mTop, mRight, mBottom;

    public BottomTopBaseDialog(Context context) {
        super(context);
    }

    /**
     * 设置animateView内置动画时长
     */
    public T innerAnimDuration(long innerAnimDuration) {
        mInnerAnimDuration = innerAnimDuration;
        return (T) this;
    }

    public T padding(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
        return (T) this;
    }

    /**
     * 设置dialog和animateView显示动画
     */
    protected void showWithAnim() {
        if (mInnerShowAnim != null) {
            mInnerShowAnim.setDuration(mInnerAnimDuration);
            mInnerShowAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsInnerShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mIsInnerShowAnim = false;
                }
            });
            mLlControlHeight.startAnimation(mInnerShowAnim);
        }

        if (mAnimateView != null) {
            if (getWindowInAs() != null) {
                mWindowInAs = getWindowInAs();
            }
            mWindowInAs.duration(mInnerAnimDuration).playOn(mAnimateView);
        }
    }

    /**
     * 设置dialog和animateView消失动画
     */
    protected void dismissWithAnim() {
        if (mInnerDismissAnim != null) {
            mInnerDismissAnim.setDuration(mInnerAnimDuration);
            mInnerDismissAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsInnerDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mIsInnerDismissAnim = false;
                    superDismiss();
                }
            });

            mLlControlHeight.startAnimation(mInnerDismissAnim);
        } else {
            superDismiss();
        }

        if (mAnimateView != null) {
            if (getWindowOutAs() != null) {
                mWindowOutAs = getWindowOutAs();
            }
            mWindowOutAs.duration(mInnerAnimDuration).playOn(mAnimateView);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsInnerDismissAnim || mIsInnerShowAnim) {
            return;
        }
        super.onBackPressed();
    }

    protected abstract BaseAnimatorSet getWindowInAs();
    protected abstract BaseAnimatorSet getWindowOutAs();
}
