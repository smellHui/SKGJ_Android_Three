package com.tepia.base.view.dialog.basedailog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;



@SuppressWarnings("deprecation")
public class NormalDialog extends BaseAlertDialog<NormalDialog> {
    private View mVLineTitle;
    private View mVLineVertical;
    private View mVLineVertical2;
    private View mVLineHorizontal;
    private int mTitleLineColor = Color.parseColor("#61AEDC");
    private float mTitleLineHeight = 1f;
    private int mDividerColor = Color.parseColor("#DCDCDC");

    public static final int STYLE_ONE = 0;
    public static final int STYLE_TWO = 1;
    private int mStyle = STYLE_ONE;

    public NormalDialog(Context context) {
        super(context);
        mTitleTextColor = Color.parseColor("#61AEDC");
        mTitleTextSize = 22f;
        mContentTextColor = Color.parseColor("#383838");
        mContentTextSize = 17f;
        mLeftBtnTextColor = Color.parseColor("#8a000000");
        mRightBtnTextColor = Color.parseColor("#8a000000");
        mMiddleBtnTextColor = Color.parseColor("#8a000000");
    }

    @Override
    public View onCreateView() {
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(mTvTitle);

        mVLineTitle = new View(mContext);
        mLlContainer.addView(mVLineTitle);
        mTvContent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(mTvContent);

        mVLineHorizontal = new View(mContext);
        mVLineHorizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        mLlContainer.addView(mVLineHorizontal);
        mTvBtnLeft.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnLeft);
        mVLineVertical = new View(mContext);
        mVLineVertical.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLlBtns.addView(mVLineVertical);
        mTvBtnMiddle.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnMiddle);
        mVLineVertical2 = new View(mContext);
        mVLineVertical2.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLlBtns.addView(mVLineVertical2);
        mTvBtnRight.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLlBtns.addView(mTvBtnRight);
        mLlContainer.addView(mLlBtns);
        return mLlContainer;
    }

    @Override
    public void setUiBeforeShow() {
        super.setUiBeforeShow();
        if (mStyle == STYLE_ONE) {
            mTvTitle.setMinHeight(dp2px(48));
            mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
            mTvTitle.setPadding(dp2px(15), dp2px(5), dp2px(0), dp2px(5));
            mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);
        } else if (mStyle == STYLE_TWO) {
            mTvTitle.setGravity(Gravity.CENTER);
            mTvTitle.setPadding(dp2px(0), dp2px(15), dp2px(0), dp2px(0));
        }
        mVLineTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(mTitleLineHeight)));
        mVLineTitle.setBackgroundColor(mTitleLineColor);
        mVLineTitle.setVisibility(mIsTitleShow && mStyle == STYLE_ONE ? View.VISIBLE : View.GONE);

        if (mStyle == STYLE_ONE) {
            mTvContent.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
            mTvContent.setMinHeight(dp2px(68));
            mTvContent.setGravity(mContentGravity);
        } else if (mStyle == STYLE_TWO) {
            mTvContent.setPadding(dp2px(15), dp2px(7), dp2px(15), dp2px(20));
            mTvContent.setMinHeight(dp2px(56));
            mTvContent.setGravity(Gravity.CENTER);
        }

        mVLineHorizontal.setBackgroundColor(mDividerColor);
        mVLineVertical.setBackgroundColor(mDividerColor);
        mVLineVertical2.setBackgroundColor(mDividerColor);

        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
            mVLineVertical2.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
        }

        float radius = dp2px(mCornerRadius);
        mLlContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 0));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 1));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(mBtnNum == 1 ? radius : 0, mBgColor, mBtnPressColor, -1));
    }


    public NormalDialog style(int style) {
        this.mStyle = style;
        return this;
    }

    public NormalDialog titleLineColor(int titleLineColor) {
        this.mTitleLineColor = titleLineColor;
        return this;
    }

    public NormalDialog titleLineHeight(float titleLineHeight_DP) {
        this.mTitleLineHeight = titleLineHeight_DP;
        return this;
    }

    public NormalDialog dividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        return this;
    }
}
