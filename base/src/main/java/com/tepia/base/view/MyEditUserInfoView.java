package com.tepia.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.base.R;


/**
 * 复写设置中个人信息编辑页面
 * 设置页面的item
 * Created by liying on 2018-3-6.
 */
public class MyEditUserInfoView extends FrameLayout {
    private Context mContext;
    private TextView titleTv;
    private ImageView leftIv;
    private ImageView rightImageV;
    private ImageView rightHeadImageV;
    private TextView rightTextV;
    private EditText rightEditV;
    private CheckBox checkBox;
    private View shortLine;
    private View longLine;
    public MyEditUserInfoView(Context context) {
        this(context,null);
    }

    public MyEditUserInfoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyEditUserInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.fragment_mine_item_rolemsg, null);
        leftIv = view.findViewById(R.id.leftIv);
        leftIv.setVisibility(View.GONE);
        rightImageV = view.findViewById(R.id.rightImageV);
        rightImageV.setVisibility(View.GONE);
        rightHeadImageV = view.findViewById(R.id.rightHeadImageV);
        rightHeadImageV.setVisibility(View.GONE);
        titleTv = view.findViewById(R.id.titleTv);
        rightTextV = view.findViewById(R.id.rightTextV);
        rightTextV.setVisibility(View.GONE);
        rightEditV = view.findViewById(R.id.rightEditV);
        rightEditV.setVisibility(View.GONE);
        checkBox = view.findViewById(R.id.rightCheckB);
        checkBox.setVisibility(View.GONE);
        shortLine = view.findViewById(R.id.shortLine);
        longLine = view.findViewById(R.id.longLine);
        addView(view);
    }


    public TextView getTitleTv() {
        return titleTv;
    }

    public ImageView getLeftIv() {
        return leftIv;
    }
    public ImageView getRightImageV() {
        return rightImageV;
    }
    public ImageView getRightHeadImageV(){return rightHeadImageV;}
    public EditText getRightEditV(){
        return rightEditV;
    }
    public TextView getRightTextV(){return rightTextV;}
    public CheckBox getCheckBox(){
        return checkBox;
    }
    public View getShortLine(){return  shortLine;}
    public View getLongLine(){return longLine;}
    public void setIvLeft(int resId){
        leftIv.setImageResource(resId);
    }
    public void setLeftTitle(String titleStr){
        titleTv.setText(titleStr);
    }
}
