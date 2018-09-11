package com.tepia.main.view.main.work.task.tasklist;


import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskBean;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeAnchor;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeRule;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的任务,待执行，执行中，已完成，已审核viewpager页面
 *
 * @author zxh
 *         update on 2018/7/12 by ly
 */

public class TaskTypeFragment extends MVPBaseFragment<TaskTypeContract.View, TaskTypePresenter> implements TaskTypeContract.View {

    private android.support.v7.widget.Toolbar loToolbarCommon;
    private MagicIndicator rvTabTitle;
    private ViewPager viewPager;
    private ImageView ivSearch;

    private List<String> titleBarList;
    private LinearLayout loToolbarSearch;
    private EditText etSearch;
    private ImageView ivFilter;
    private RecyclerView rvSearchTaskList;
    private LinearLayout loTaskType;
    private AdapterTaskList adapterTaskList;

    private CommonNavigatorAdapter commonNavigatorAdapter;
    private String typeStr;
    private int pagerCount;

    public static TaskTypeFragment newInstance(String typekey, int pagerCount) {
        TaskTypeFragment f = new TaskTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPEKEY", typekey);
        bundle.putInt("pagerCount", pagerCount);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tahmain_task;
    }

    @Override
    protected void initData() {
        titleBarList = new ArrayList<>();
//        titleBarList.add("未下发");
        titleBarList.add(ResUtils.getString(R.string.text_task_status_dzx));
        titleBarList.add(ResUtils.getString(R.string.text_task_status_zxz));
        titleBarList.add(ResUtils.getString(R.string.text_task_status_ywc));
        titleBarList.add(ResUtils.getString(R.string.text_task_status_ysh));
    }

    @Override
    protected void initView(View view) {
        typeStr = getArguments().getString("TYPEKEY");
        pagerCount = getArguments().getInt("pagerCount");
        setCenterTitle(typeStr);
        showBack();

        loToolbarCommon = view.findViewById(R.id.lo_toolbar_common);
        loToolbarSearch = view.findViewById(R.id.lo_toolbar_search);
        ivSearch = view.findViewById(R.id.iv_search);
        etSearch = view.findViewById(R.id.et_search);
        rvTabTitle = view.findViewById(R.id.rv_tab_title);
        ivFilter = view.findViewById(R.id.iv_filter);
        viewPager = view.findViewById(R.id.viewPager);
        loTaskType = view.findViewById(R.id.lo_task_type);
        rvSearchTaskList = view.findViewById(R.id.rv_search_task_list);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = etSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(temp)) {
                    taskSearch(temp, true);
                } else {
                    taskSearch("", false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (getString(R.string.zonglanstr).equals(typeStr)) {
            ivFilter.setVisibility(View.GONE);
            loToolbarSearch.setVisibility(View.VISIBLE);
        } else {
            ivFilter.setVisibility(View.GONE);
            loToolbarSearch.setVisibility(View.GONE);
        }
        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList();
                list.add(getString(R.string.zonglanstr));
                list.add(getString(R.string.xunjianstr));
                list.add(getString(R.string.weihustr));
                list.add(getString(R.string.baojiestr));
//                showTypeDialog(list);
                showTypeDialog2();
            }
        });
        initTitle();
        initViewPager();
        initTabTitle();
        viewPager.setCurrentItem(pagerCount);
    }

    private void showTypeDialog2() {
        MySelectTypeDialog mdf = new MySelectTypeDialog();
        FragmentTransaction ft = getBaseActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        mdf.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemCur = viewPager.getCurrentItem();
                TabTaskListFragment tabTaskListFragment = (TabTaskListFragment) ((CommonFragmentPagerAdapter) viewPager.getAdapter())
                        .getItem(itemCur);
                if (position == 0) {
                    tabTaskListFragment.setType("");
                } else {
                    tabTaskListFragment.setType(position + "");
                }
                mdf.dismiss();
            }
        });
        mdf.show(ft, "123");
    }

    public void taskSearch(String keyWord, boolean isShow) {
        if (isShow) {
            rvSearchTaskList.setVisibility(View.VISIBLE);
            loTaskType.setVisibility(View.GONE);
            mPresenter.taskSearch(keyWord);
        } else {
            rvSearchTaskList.setVisibility(View.GONE);
            loTaskType.setVisibility(View.VISIBLE);
        }
    }

    private void initTitle() {

    }


    @Override
    protected void initRequestData() {

    }

    private void initTabTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        rvTabTitle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.magicIndicator));
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleBarList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(titleBarList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getContext(), R.color.magicIndicatortext_color_unselect));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getContext(), R.color.magicIndicatortext_color_select));
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                if (index == viewPager.getCurrentItem()) {
                    // setup badge
                    TextView badgeTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.simple_red_dot_badge_layout, null);
                    TabTaskListFragment fragment = (TabTaskListFragment) ((CommonFragmentPagerAdapter) viewPager.getAdapter()).getItem(index);


                    // set badge position
                    badgePagerTitleView.setXBadgeRule(new BadgeRule(BadgeAnchor.CONTENT_RIGHT, -UIUtil.dip2px(context, 8)));
                    badgePagerTitleView.setYBadgeRule(new BadgeRule(BadgeAnchor.CONTENT_TOP, 0));
                    // don't cancel badge when tab selected
                    badgePagerTitleView.setAutoCancelBadge(false);
                    badgePagerTitleView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (fragment.total > 0) {
                                badgePagerTitleView.setBadgeView(badgeTextView);
                                badgeTextView.setText("" + fragment.total);
                            } else {
                                badgePagerTitleView.postDelayed(this, 500);
                                badgePagerTitleView.setBadgeView(null);
                            }
                        }
                    }, 500);
                    if (fragment.total > 0) {
                        badgePagerTitleView.setBadgeView(badgeTextView);
                        badgeTextView.setText("" + fragment.total);
                    } else {
                        badgePagerTitleView.setBadgeView(null);
                    }
                } else {
                    // cancel badge when click tab
                    badgePagerTitleView.setBadgeView(null);
                }
                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 100));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(getContext(), R.color.magicIndicator_tab_select));
                return indicator;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        rvTabTitle.setNavigator(commonNavigator);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                rvTabTitle.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                TaskTypeListActivity.pagerCount = position;
                commonNavigatorAdapter.notifyDataSetChanged();
                rvTabTitle.onPageSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                rvTabTitle.onPageScrollStateChanged(state);
            }
        });

    }


    /**
     * 初始化各个页面并和viewpager绑定
     */
    private void initViewPager() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getChildFragmentManager());
        String type = "";
        if (!TextUtils.isEmpty(typeStr)) {
            if (getString(R.string.zonglanstr).equals(typeStr)) {
                type = "";
            } else if (getString(R.string.xunjianstr).equals(typeStr)) {
                type = "1";
            } else if (getString(R.string.weihustr).equals(typeStr)) {
                type = "2";
            } else if (getString(R.string.baojiestr).equals(typeStr)) {
                type = "3";
            }
        }
//        adapter.addFragment(TabTaskFragmentFactory.getInstance().getTaskListFragment1(0, type));
        adapter.addFragment(TabTaskFragmentFactory.getInstance().getTaskListFragment1(1, type));
        adapter.addFragment(TabTaskFragmentFactory.getInstance().getTaskListFragment1(2, type));
        adapter.addFragment(TabTaskFragmentFactory.getInstance().getTaskListFragment1(3, type));
        adapter.addFragment(TabTaskFragmentFactory.getInstance().getTaskListFragment1(4, type));

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public void taskSearchSuccess(List<TaskBean> data) {
        if (adapterTaskList == null) {
            rvSearchTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterTaskList = new AdapterTaskList(getContext(), R.layout.lv_task_list_item, data);
            rvSearchTaskList.setAdapter(adapterTaskList);
            adapterTaskList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    ARouter.getInstance().build(AppRoutePath.app_task_detail)
                            .withString("workOrderId", data.get(position).getWorkOrderId())
                            .withString("taskBean", new Gson().toJson(data.get(position)))
                            .navigation();
                }
            });
        } else {
            adapterTaskList.setNewData(data);
        }
        if (data == null || data.size() == 0) {
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            TextView tvEmptyViewText = view.findViewById(R.id.tv_empty_view_text);
            tvEmptyViewText.setText("暂无搜索到相关内容");
            adapterTaskList.setEmptyView(view);
        }
    }
}
