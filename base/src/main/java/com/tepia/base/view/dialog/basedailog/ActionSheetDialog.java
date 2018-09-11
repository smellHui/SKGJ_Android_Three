package com.tepia.base.view.dialog.basedailog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:iOS风格对话框                                      *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.views                      *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class ActionSheetDialog extends BottomBaseDialog<ActionSheetDialog> {
    private ListView mLv;
    private TextView mTvTitle;
    private View mVLineTitle;
    private TextView mTvCancel;
    private float mCornerRadius = 5;
    private int mTitleBgColor = Color.parseColor("#ddffffff");
    private String mTitle = "提示";
    private float mTitleHeight = 48;
    private int mTitleTextColor = Color.parseColor("#8F8F8F");
    private float mTitleTextSize = 17.5f;
    private int mLvBgColor = Color.parseColor("#ddffffff");
    private int mDividerColor = Color.parseColor("#D7D7D9");
    private float mDividerHeight = 0.8f;
    private int mItemPressColor = Color.parseColor("#ffcccccc");
    private int mItemTextColor = Color.parseColor("#44A2FF");
    private float mItemTextSize = 17.5f;
    /**
     * item height(ListView item高度)
     */
    private float mItemHeight = 48;
    private boolean mIsTitleShow = true;
    private String mCancelText = "取消";
    private int mCancelTextColor = Color.parseColor("#44A2FF");
    private float mCancelTextSize = 17.5f;
    private BaseAdapter mAdapter;
    private ArrayList<DialogMenuItem> mContents = new ArrayList<>();
    private OnOpenItemClick mOnOpenItemClick;
    private LayoutAnimationController mLac;

    public void setOnOpenItemClickL(OnOpenItemClick onOpenItemClick) {
        mOnOpenItemClick = onOpenItemClick;
    }

    public ActionSheetDialog(Context context, ArrayList<DialogMenuItem> baseItems, View animateView) {
        super(context, animateView);
        mContents.addAll(baseItems);
        init();
    }

    public ActionSheetDialog(Context context, String[] items, View animateView) {
        super(context, animateView);
        mContents = new ArrayList<>();
        for (String item : items) {
            DialogMenuItem customBaseItem = new DialogMenuItem(item, 0);
            mContents.add(customBaseItem);
        }
        init();
    }

    public ActionSheetDialog(Context context, BaseAdapter adapter, View animateView) {
        super(context, animateView);
        mAdapter = adapter;
        init();
    }

    private void init() {
        widthScale(0.95f);
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 6f, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(350);
        animation.setStartOffset(150);
        mLac = new LayoutAnimationController(animation, 0.12f);
        mLac.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public View onCreateView() {
        LinearLayout ll_container = new LinearLayout(mContext);
        ll_container.setOrientation(LinearLayout.VERTICAL);
        ll_container.setBackgroundColor(Color.TRANSPARENT);
        mTvTitle = new TextView(mContext);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvTitle.setPadding(dp2px(10), dp2px(5), dp2px(10), dp2px(5));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = dp2px(20);
        ll_container.addView(mTvTitle, params);
        mVLineTitle = new View(mContext);
        ll_container.addView(mVLineTitle);
        mLv = new ListView(mContext);
        mLv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        mLv.setCacheColorHint(Color.TRANSPARENT);
        mLv.setFadingEdgeLength(0);
        mLv.setVerticalScrollBarEnabled(false);
        mLv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        ll_container.addView(mLv);
        mTvCancel = new TextView(mContext);
        mTvCancel.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin = dp2px(7);
        lp.bottomMargin = dp2px(7);
        mTvCancel.setLayoutParams(lp);
        ll_container.addView(mTvCancel);
        return ll_container;
    }

    @Override
    public void setUiBeforeShow() {
        float radius = dp2px(mCornerRadius);
        mTvTitle.setHeight(dp2px(mTitleHeight));
        mTvTitle.setBackgroundDrawable(CornerUtils.cornerDrawable(mTitleBgColor, new float[]{radius, radius, radius,
                radius, 0, 0, 0, 0}));
        mTvTitle.setText(mTitle);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleTextSize);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        mVLineTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(mDividerHeight)));
        mVLineTitle.setBackgroundColor(mDividerColor);
        mVLineTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);

        mTvCancel.setHeight(dp2px(mItemHeight));
        mTvCancel.setText(mCancelText);
        mTvCancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, mCancelTextSize);
        mTvCancel.setTextColor(mCancelTextColor);
        mTvCancel.setBackgroundDrawable(CornerUtils.listItemSelector(radius, mLvBgColor, mItemPressColor, 1, 0));
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLv.setDivider(new ColorDrawable(mDividerColor));
        mLv.setDividerHeight(dp2px(mDividerHeight));
        if (mIsTitleShow) {
            mLv.setBackgroundDrawable(CornerUtils.cornerDrawable(mLvBgColor, new float[]{0, 0, 0, 0, radius, radius, radius,
                    radius}));
        } else {
            mLv.setBackgroundDrawable(CornerUtils.cornerDrawable(mLvBgColor, radius));
        }

        if (mAdapter == null) {
            mAdapter = new ListDialogAdapter();
        }

        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mOnOpenItemClick != null) {
                    mOnOpenItemClick.onOpenItemClick(parent, view, position, id);
                }
            }
        });
        mLv.setLayoutAnimation(mLac);
    }

    /**
     * 设置标题栏背景色
     */
    public ActionSheetDialog titleBgColor(int titleBgColor) {
        mTitleBgColor = titleBgColor;
        return this;
    }

    /**
     * 设置标题内容
     */
    public ActionSheetDialog title(String title) {
        mTitle = title;
        return this;
    }

    /**
     * 设置标题高度
     */
    public ActionSheetDialog titleHeight(float titleHeight) {
        mTitleHeight = titleHeight;
        return this;
    }

    /**
     * 设置标题字体大小
     */
    public ActionSheetDialog titleTextSize_SP(float titleTextSize_SP) {
        mTitleTextSize = titleTextSize_SP;
        return this;
    }

    /**
     * 设置标题字体颜色
     */
    public ActionSheetDialog titleTextColor(int titleTextColor) {
        mTitleTextColor = titleTextColor;
        return this;
    }

    /**
     * 设置标题是否显示
     */
    public ActionSheetDialog isTitleShow(boolean isTitleShow) {
        mIsTitleShow = isTitleShow;
        return this;
    }

    /**
     * 设置ListView背景
     */
    public ActionSheetDialog lvBgColor(int lvBgColor) {
        mLvBgColor = lvBgColor;
        return this;
    }

    /**
     * 设置圆角程度,单位dp
     */
    public ActionSheetDialog cornerRadius(float cornerRadius_DP) {
        mCornerRadius = cornerRadius_DP;
        return this;
    }

    /**
     * sListView divider颜色
     */
    public ActionSheetDialog dividerColor(int dividerColor) {
        mDividerColor = dividerColor;
        return this;
    }

    /**
     * ListView divider高度
     */
    public ActionSheetDialog dividerHeight(float dividerHeight_DP) {
        mDividerHeight = dividerHeight_DP;
        return this;
    }

    /**
     * item按住颜色
     */
    public ActionSheetDialog itemPressColor(int itemPressColor) {
        mItemPressColor = itemPressColor;
        return this;
    }

    /**
     * item字体颜色
     */
    public ActionSheetDialog itemTextColor(int itemTextColor) {
        mItemTextColor = itemTextColor;
        return this;
    }

    /**
     * item字体大小
     */
    public ActionSheetDialog itemTextSize(float itemTextSize_SP) {
        mItemTextSize = itemTextSize_SP;
        return this;
    }

    /**
     * item高度
     */
    public ActionSheetDialog itemHeight(float itemHeight_DP) {
        mItemHeight = itemHeight_DP;
        return this;
    }

    /**
     * 设置layout动画 ,传入null将不显示layout动画
     */
    public ActionSheetDialog layoutAnimation(LayoutAnimationController lac) {
        mLac = lac;
        return this;
    }

    /**
     * 设置取消按钮内容
     */
    public ActionSheetDialog cancelText(String cancelText) {
        mCancelText = cancelText;
        return this;
    }

    /**
     * 取消按钮文字颜色
     */
    public ActionSheetDialog cancelText(int cancelTextColor) {
        mCancelTextColor = cancelTextColor;
        return this;
    }

    /**
     * 取消按钮文字大小
     */
    public ActionSheetDialog cancelTextSize(float cancelTextSize) {
        mCancelTextSize = cancelTextSize;
        return this;
    }

    class ListDialogAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mContents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final DialogMenuItem item = mContents.get(position);

            LinearLayout llItem = new LinearLayout(mContext);
            llItem.setOrientation(LinearLayout.HORIZONTAL);
            llItem.setGravity(Gravity.CENTER_VERTICAL);

            ImageView ivItem = new ImageView(mContext);
            ivItem.setPadding(0, 0, dp2px(15), 0);
            llItem.addView(ivItem);

            TextView tvItem = new TextView(mContext);
            tvItem.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            tvItem.setSingleLine(true);
            tvItem.setGravity(Gravity.CENTER);
            tvItem.setTextColor(mItemTextColor);
            tvItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, mItemTextSize);
            tvItem.setHeight(dp2px(mItemHeight));

            llItem.addView(tvItem);
            float radius = dp2px(mCornerRadius);
            if (mIsTitleShow) {
                llItem.setBackgroundDrawable((CornerUtils.listItemSelector(radius, Color.TRANSPARENT, mItemPressColor,
                        position == mContents.size() - 1)));
            } else {
                llItem.setBackgroundDrawable(CornerUtils.listItemSelector(radius, Color.TRANSPARENT, mItemPressColor,
                        mContents.size(), position));
            }

            ivItem.setImageResource(item.mResId);
            tvItem.setText(item.mOpenName);
            ivItem.setVisibility(item.mResId == 0 ? View.GONE : View.VISIBLE);
            return llItem;
        }
    }

    private class DialogMenuItem {


        public String mOpenName;
        public int mResId;

        public DialogMenuItem(String openName, int resId) {
            mOpenName = openName;
            mResId = resId;
        }


    }
}
