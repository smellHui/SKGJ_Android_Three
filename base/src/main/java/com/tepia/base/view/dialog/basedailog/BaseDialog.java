package com.tepia.base.view.dialog.basedailog;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.Utils;


/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:基类                                             *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.views                      *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public abstract class BaseDialog<T extends BaseDialog<T>> extends Dialog {
    protected Context mContext;
    protected DisplayMetrics mDisplayMetrics;
    protected boolean mCancel;
    protected float mWidthScale = 1;
    protected float mHeightScale;
    private BaseAnimatorSet mShowAnim;
    private BaseAnimatorSet mDismissAnim;
    protected LinearLayout mLlTop;
    protected LinearLayout mLlControlHeight;
    protected View mOnCreateView;
    private boolean mIsShowAnim;
    private boolean mIsDismissAnim;
    protected float mMaxHeight;
    private boolean mIsPopupStyle;
    private boolean mAutoDismiss;
    private long mAutoDismissDelay = 1500;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public BaseDialog(Context context) {
        super(context);
        setDialogTheme();
        mContext = context;
        setCanceledOnTouchOutside(false);
    }

    public BaseDialog(Context context, boolean isPopupStyle) {
        this(context);
        mIsPopupStyle = isPopupStyle;
    }

    /**
     * 设置对话框主题
     */
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);
    }


    public void onViewCreated(View view) {

    }

    /**
     * 在对话框显示之前,设置界面数据或者逻辑
     */
    public abstract void setUiBeforeShow();

    public abstract View onCreateView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mMaxHeight = mDisplayMetrics.heightPixels - ScreenUtil.dp2px(Utils.getContext(),100);
        mLlTop = new LinearLayout(mContext);
        mLlTop.setGravity(Gravity.CENTER);
        mLlControlHeight = new LinearLayout(mContext);
        mLlControlHeight.setOrientation(LinearLayout.VERTICAL);
        mOnCreateView = onCreateView();
        mLlControlHeight.addView(mOnCreateView);
        mLlTop.addView(mLlControlHeight);
        onViewCreated(mOnCreateView);
        if (mIsPopupStyle) {
            setContentView(mLlTop, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            setContentView(mLlTop, new ViewGroup.LayoutParams(mDisplayMetrics.widthPixels, (int) mMaxHeight));
        }
        mLlTop.setOnClickListener((v)-> {
            if (mCancel) {
                dismiss();
            }
        });
        mOnCreateView.setClickable(true);
    }

    /**
     * 获取实际创建的View
     */
    public View getCreateView() {
        return mOnCreateView;
    }

    /**
     * 当dailog依附在window上,设置对话框宽高以及显示动画
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setUiBeforeShow();
        int width;
        if (mWidthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (mDisplayMetrics.widthPixels * mWidthScale);
        }
        int height;
        if (mHeightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (mHeightScale == 1) {
            height = (int) mMaxHeight;
        } else {
            height = (int) (mMaxHeight * mHeightScale);
        }
        mLlControlHeight.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        if (mShowAnim != null) {
            mShowAnim.listener(new AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsShowAnim = false;
                    delayDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsShowAnim = false;
                }
            }).playOn(mLlControlHeight);
        } else {
            BaseAnimatorSet.reset(mLlControlHeight);
            delayDismiss();
        }
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void dismiss() {
        if (mDismissAnim != null) {
            mShowAnim.listener(new AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }
            }).playOn(mLlControlHeight);
        } else {
            superDismiss();
        }
    }

    /**
     * 无动画dismiss)
     */
    public void superDismiss() {
        super.dismiss();
    }

    /**
     * 动画弹出对话框,style动画资源
     */
    public void show(int animStyle) {
        Window window = getWindow();
        window.setWindowAnimations(animStyle);
        show();
    }

    /**
     * 指定位置显示,只对isPopupStyle为true有效
     */
    public void showAtLocation(int gravity, int x, int y) {
        if (mIsPopupStyle) {
            Window window = getWindow();
            LayoutParams params = window.getAttributes();
            window.setGravity(gravity);
            params.x = x;
            params.y = y;
        }

        show();
    }

    /**
     * 指定位置显示,只对isPopupStyle为true有效
     */
    public void showAtLocation(int x, int y) {
        int gravity = Gravity.LEFT | Gravity.TOP;
        showAtLocation(gravity, x, y);
    }

    /**
     * 设置背景是否昏暗
     */
    public T dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        }
        return (T) this;
    }

    /**
     *设置对话框宽度,占屏幕宽的比例0-1
     */
    public T widthScale(float widthScale) {
        this.mWidthScale = widthScale;
        return (T) this;
    }

    /**
     * 设置对话框高度,占屏幕宽的比例0-1
     */
    public T heightScale(float heightScale) {
        mHeightScale = heightScale;
        return (T) this;
    }

    /**
     * 设置显示的动画
     */
    public T showAnim(BaseAnimatorSet showAnim) {
        mShowAnim = showAnim;
        return (T) this;
    }

    /**
     * 设置隐藏的动画
     */
    public T dismissAnim(BaseAnimatorSet dismissAnim) {
        mDismissAnim = dismissAnim;
        return (T) this;
    }

    /**
     * 在给定时间后,自动消失
     */
    public T autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return (T) this;
    }

    /**
     * 对话框消失延时时间,毫秒值
     */
    public T autoDismissDelay(long autoDismissDelay) {
        mAutoDismissDelay = autoDismissDelay;
        return (T) this;
    }

    private void delayDismiss() {
        if (mAutoDismiss && mAutoDismissDelay > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, mAutoDismissDelay);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * dp to px
     */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
