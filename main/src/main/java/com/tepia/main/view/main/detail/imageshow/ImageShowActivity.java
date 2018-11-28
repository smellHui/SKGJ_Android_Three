package com.tepia.main.view.main.detail.imageshow;

import android.support.v4.view.ViewPager;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.PhotoViewPager;
import com.tepia.main.model.detai.ImageBean;

import java.util.ArrayList;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :图片详情
  * Version :1.0
  * 功能描述 :
 **/
public class ImageShowActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private PhotoViewPager mViewPager;
    private GirlAdapter mAdapter;
    private ArrayList<ImageBean.DataBean.PicturesBean.ListBean> datas;
    private int current;
    private int total;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_show;
    }

    @Override
    public void initView() {

        mViewPager = findViewById(R.id.view_pager);

        if (getIntent() != null) {
            datas = getIntent().getParcelableArrayListExtra("girls");
            total = datas.size();
            current = getIntent().getIntExtra("current",0);
            int position = current + 1;
            setCenterTitle(position + "/" + total);
            showBack();
        }

        mAdapter = new GirlAdapter(this, datas);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(current);
        mViewPager.setOnPageChangeListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ++position;
        setCenterTitle(position + "/" + total);
        showBack();


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
