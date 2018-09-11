package com.tepia.base.view.dialog.basedailog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                              *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.interfaces                    *
 * 创建时间:2017年11月15日10:44                                 *                                                                 *                                                               *
 * 更新时间:2017年11月15日10:44                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public abstract class BaseAlertDialog<T extends BaseAlertDialog<T>> extends BaseDialog<T> {
    protected LinearLayout mLlContainer;
    protected TextView mTvTitle;
    protected String mTitle;
    protected int mTitleTextColor;
    protected float mTitleTextSize;
    protected boolean mIsTitleShow = true;

    protected TextView mTvContent;
    protected String mContent;
    protected int mContentGravity = Gravity.CENTER_VERTICAL;
    protected int mContentTextColor;
    protected float mContentTextSize;

    protected int mBtnNum = 2;
    protected LinearLayout mLlBtns;
    protected TextView mTvBtnLeft;
    protected TextView mTvBtnRight;
    protected TextView mTvBtnMiddle;
    /**
     * 按钮内容
     */
    protected String mBtnLeftText = "取消";
    protected String mBtnRightText = "确定";
    protected String mBtnMiddleText = "继续";
    /**
     * 按钮字体颜色
     */
    protected int mLeftBtnTextColor;
    protected int mRightBtnTextColor;
    protected int mMiddleBtnTextColor;
    /**
     * 按钮字体大小
     */
    protected float mLeftBtnTextSize = 15f;
    protected float mRightBtnTextSize = 15f;
    protected float mMiddleBtnTextSize = 15f;
    /**
     * 按钮点击颜色
     */
    protected int mBtnPressColor = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /**
     * 左按钮接口
     */
    protected OnBtnClickListener mOnBtnLeftClickL;
    /**
     * 右按钮接口
     */
    protected OnBtnClickListener mOnBtnRightClickL;
    /**
     * 右按钮接口
     */
    protected OnBtnClickListener mOnBtnMiddleClickL;

    /**
     * 圆角程度,单位dp
     */
    protected float mCornerRadius = 3;
    /**
     * 背景颜色
     */
    protected int mBgColor = Color.parseColor("#ffffff");

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public BaseAlertDialog(Context context) {
        super(context);
        widthScale(0.88f);
        mLlContainer = new LinearLayout(context);
        mLlContainer.setOrientation(LinearLayout.VERTICAL);
        mTvTitle = new TextView(context);
        mTvContent = new TextView(context);
        mLlBtns = new LinearLayout(context);
        mLlBtns.setOrientation(LinearLayout.HORIZONTAL);
        mTvBtnLeft = new TextView(context);
        mTvBtnLeft.setGravity(Gravity.CENTER);
        mTvBtnMiddle = new TextView(context);
        mTvBtnMiddle.setGravity(Gravity.CENTER);
        mTvBtnRight = new TextView(context);
        mTvBtnRight.setGravity(Gravity.CENTER);
    }

    @Override
    public void setUiBeforeShow() {
        mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);
        mTvTitle.setText(TextUtils.isEmpty(mTitle) ? "温馨提示" : mTitle);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize);
        mTvContent.setGravity(mContentGravity);
        mTvContent.setText(mContent);
        mTvContent.setTextColor(mContentTextColor);
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, mContentTextSize);
        mTvContent.setLineSpacing(0, 1.3f);
        mTvBtnLeft.setText(mBtnLeftText);
        mTvBtnRight.setText(mBtnRightText);
        mTvBtnMiddle.setText(mBtnMiddleText);
        mTvBtnLeft.setTextColor(mLeftBtnTextColor);
        mTvBtnRight.setTextColor(mRightBtnTextColor);
        mTvBtnMiddle.setTextColor(mMiddleBtnTextColor);
        mTvBtnLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, mLeftBtnTextSize);
        mTvBtnRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, mRightBtnTextSize);
        mTvBtnMiddle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mMiddleBtnTextSize);
        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
        }
        mTvBtnLeft.setOnClickListener(v-> {
            if (mOnBtnLeftClickL != null) {
                mOnBtnLeftClickL.onBtnClick();
            } else {
                dismiss();
            }
        });

        mTvBtnRight.setOnClickListener(v -> {
            if (mOnBtnRightClickL != null) {
                mOnBtnRightClickL.onBtnClick();
            } else {
                dismiss();
            }
        });

        mTvBtnMiddle.setOnClickListener(v -> {
            if (mOnBtnMiddleClickL != null) {
                mOnBtnMiddleClickL.onBtnClick();
            } else {
                dismiss();
            }
        });
    }

    public T title(String title) {
        mTitle = title;
        return (T) this;
    }

    /**
     * 设置标题字体颜色
     */
    public T titleTextColor(int titleTextColor) {
        mTitleTextColor = titleTextColor;
        return (T) this;
    }

    /**
     * 设置标题字体大小
     */
    public T titleTextSize(float titleTextSize_SP) {
        mTitleTextSize = titleTextSize_SP;
        return (T) this;
    }

    /**
     * 设置标题是否显示
     */
    public T isTitleShow(boolean isTitleShow) {
        mIsTitleShow = isTitleShow;
        return (T) this;
    }

    /**
     * 设置正文内容
     */
    public T content(String content) {
        mContent = content;
        return (T) this;
    }

    /**
     * 设置正文内容,显示位置
     */
    public T contentGravity(int contentGravity) {
        mContentGravity = contentGravity;
        return (T) this;
    }

    /**
     * 设置正文字体颜色
     */
    public T contentTextColor(int contentTextColor) {
        mContentTextColor = contentTextColor;
        return (T) this;
    }

    /**
     * 设置正文字体大小,单位sp
     */
    public T contentTextSize(float contentTextSize_SP) {
        mContentTextSize = contentTextSize_SP;
        return (T) this;
    }

    /**
     * 设置按钮文字内容
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnNum(int btnNum) {
        if (btnNum < 1 || btnNum > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        mBtnNum = btnNum;

        return (T) this;
    }

    /**
     * 设置按钮文字内容
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnText(String... btnTexts) {
        if (btnTexts.length < 1 || btnTexts.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }

        if (btnTexts.length == 1) {
            mBtnMiddleText = btnTexts[0];
        } else if (btnTexts.length == 2) {
            mBtnLeftText = btnTexts[0];
            mBtnRightText = btnTexts[1];
        } else if (btnTexts.length == 3) {
            mBtnLeftText = btnTexts[0];
            mBtnRightText = btnTexts[1];
            mBtnMiddleText = btnTexts[2];
        }

        return (T) this;
    }

    /**
     * 设置按钮字体颜色
     * btnTextColors size 1, middle
     * btnTextColors size 2, left right
     * btnTextColors size 3, left right middle
     */
    public T btnTextColor(int... btnTextColors) {
        if (btnTextColors.length < 1 || btnTextColors.length > 3) {
            throw new IllegalStateException(" range of param textColors length is [1,3]!");
        }

        if (btnTextColors.length == 1) {
            mMiddleBtnTextColor = btnTextColors[0];
        } else if (btnTextColors.length == 2) {
            mLeftBtnTextColor = btnTextColors[0];
            mRightBtnTextColor = btnTextColors[1];
        } else if (btnTextColors.length == 3) {
            mLeftBtnTextColor = btnTextColors[0];
            mRightBtnTextColor = btnTextColors[1];
            mMiddleBtnTextColor = btnTextColors[2];
        }

        return (T) this;
    }

    /**
     * 设置字体大小,单位sp
     * btnTextSizes size 1, middle
     * btnTextSizes size 2, left right
     * btnTextSizes size 3, left right middle
     */
    public T btnTextSize(float... btnTextSizes) {
        if (btnTextSizes.length < 1 || btnTextSizes.length > 3) {
            throw new IllegalStateException(" range of param btnTextSizes length is [1,3]!");
        }

        if (btnTextSizes.length == 1) {
            mMiddleBtnTextSize = btnTextSizes[0];
        } else if (btnTextSizes.length == 2) {
            mLeftBtnTextSize = btnTextSizes[0];
            mRightBtnTextSize = btnTextSizes[1];
        } else if (btnTextSizes.length == 3) {
            mLeftBtnTextSize = btnTextSizes[0];
            mRightBtnTextSize = btnTextSizes[1];
            mMiddleBtnTextSize = btnTextSizes[2];
        }

        return (T) this;
    }

    /**
     * 设置按钮点击颜色
     */
    public T btnPressColor(int btnPressColor) {
        mBtnPressColor = btnPressColor;
        return (T) this;
    }

    /**
     * 设置圆角程度
     */
    public T cornerRadius(float cornerRadius_DP) {
        mCornerRadius = cornerRadius_DP;
        return (T) this;
    }

    /**
     * 设置背景色
     */
    public T bgColor(int bgColor) {
        mBgColor = bgColor;
        return (T) this;
    }

    /**
     * 设置按钮监听事件
     * onBtnClickLs size 1, middle
     * onBtnClickLs size 2, left right
     * onBtnClickLs size 3, left right middle
     */
    public void setOnBtnClickL(OnBtnClickListener... onBtnClickLs) {
        if (onBtnClickLs.length < 1 || onBtnClickLs.length > 3) {
            throw new IllegalStateException(" range of param onBtnClickLs length is [1,3]!");
        }
        if (onBtnClickLs.length == 1) {
            mOnBtnMiddleClickL = onBtnClickLs[0];
        } else if (onBtnClickLs.length == 2) {
            mOnBtnLeftClickL = onBtnClickLs[0];
            mOnBtnRightClickL = onBtnClickLs[1];
        } else if (onBtnClickLs.length == 3) {
            mOnBtnLeftClickL = onBtnClickLs[0];
            mOnBtnRightClickL = onBtnClickLs[1];
            mOnBtnMiddleClickL = onBtnClickLs[2];
        }
    }
}
