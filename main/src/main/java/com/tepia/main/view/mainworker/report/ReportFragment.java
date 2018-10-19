package com.tepia.main.view.mainworker.report;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.maincommon.setting.ChoiceReservoirActivity;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
import com.tepia.main.view.mainworker.yunwei.startyunwei.StartYunWeiFragment;
import com.tepia.main.view.mainworker.yunwei.yunweilist.YunWeiListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页三 上报页 巡查责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_shangbao_xuncha)
public class ReportFragment extends BaseCommonFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments ;
    private ArrayList<String> titles ;

    private LinearLayout root_dialog_shangbao;

    private String oldReserviorId = "";
    private TextView switchTv;
    private TextView tv_reservoir_name;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_shangbao));
        getRightTianqi().setVisibility(View.VISIBLE);
        tabLayout = findView(R.id.tab);
        viewPager =findView(R.id.viewpager);
        root_dialog_shangbao =findView(R.id.root_dialog_shangbao);

        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        initViewpager();
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        if(reservoirBean != null) {
            oldReserviorId = reservoirBean.getReservoirId();
        }

        switchTv = findView(R.id.switchTv);
        tv_reservoir_name = findView(R.id.tv_reservoir_name);
        switchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showSelectReservoir();
                startActivityForResult(new Intent(getBaseActivity(),ChoiceReservoirActivity.class),ChoiceReservoirActivity.resultCode);

            }
        });

    }

    /**
     * 切换水库
     */
    private void showSelectReservoir() {
        ArrayList<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        if (reservoirBeans != null) {
            String[] stringItems = new String[reservoirBeans.size()];
            for (int i = 0; i < reservoirBeans.size(); i++) {
                stringItems[i] = reservoirBeans.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ReservoirBean selectedResrvoir = reservoirBeans.get(position);
                    com.tepia.main.model.user.UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
//                    tv_reservoir_name.setText(selectedResrvoir.getReservoir());
                    dialog.dismiss();
                    refreshPage();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ChoiceReservoirActivity.resultCode:
                refreshPage();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initRequestData() {
        refreshPage();

    }

    private boolean haschangeReserviors;
    private void refreshPage(){
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        String reservoirId =  reservoirBean.getReservoirId();
        tv_reservoir_name.setText(reservoirBean.getReservoir());
        LogUtil.e("当前默认水库id--------------"+reservoirBean.getReservoirId());
        if(!reservoirId.equals(oldReserviorId)){
            haschangeReserviors = true;
            oldReserviorId = reservoirId;
            if(viewPager.getCurrentItem() == 0){
                LogUtil.e("刷新水位------------");
                waterLevelFragment.refresh(true);
            }else{
                LogUtil.e("刷新应急------------");
                emergencyFragment.refresh(true);
            }

        }
    }

    private void clearData(){
        if (fragments != null) {
            fragments.clear();
        }
        if (titles != null) {
            titles.clear();
        }
        if (tabLayout != null) {
            tabLayout.removeAllTabs();
        }

    }

    private WaterLevelFragment waterLevelFragment;
    private EmergencyFragment emergencyFragment;

    private void initViewpager(){
        waterLevelFragment = TabReportFragmentFactory.getInstance().getWaterLevelFragment();
        emergencyFragment = TabReportFragmentFactory.getInstance().getEmergencyFragment();
        initViewPager();
        new_tab();

    }

    private void initViewPager() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(waterLevelFragment);
        adapter.addFragment(emergencyFragment);
        viewPager.setAdapter(adapter);

    }

    private void new_tab(){
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_icon("水位",R.drawable.bg_report_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_icon("应急情况",R.drawable.bg_report_emergence_tab)));

        //Tablayout自定义view绑定ViewPager 自定义view时使用 tabLayout.setupWithViewPager(viewPager);方法关联无效，通过以下方法进行viewpager和tablayout的关联
        TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);

                if (haschangeReserviors) {
                    haschangeReserviors = false;
                    if(viewPager.getCurrentItem() == 0){
                        LogUtil.e("刷新水位------------");
                        waterLevelFragment.refresh(true);
                    }else{
                        LogUtil.e("刷新应急------------");
                        emergencyFragment.refresh(true);
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private View tab_icon(String name,int iconID){
        View newtab =  LayoutInflater.from(getBaseActivity()).inflate(R.layout.operation_tab_custom_view2,null);
        TextView tv = (TextView) newtab.findViewById(R.id.tab_tv);
        tv.setText(name);
        ImageView im = (ImageView)newtab.findViewById(R.id.tab_iv);
        im.setImageResource(iconID);
        return newtab;
    }



    public class MyPagerAdapter extends FragmentPagerAdapter {
        private Context context;
        private List<Fragment> fragmentList;
        private List<String> list_Title;

        public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title) {
            super(fm);
            this.context = context;
            this.fragmentList = fragmentList;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * //此方法用来显示tab上的名字
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }
    }





}
