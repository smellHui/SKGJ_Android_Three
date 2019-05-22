package com.tepia.main.view.mainworker.report;

import com.tepia.main.view.TabMainFragmentFactory;
import com.tepia.main.view.mainworker.homepage.HomeXunChaFragment;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-29
 * Time            :       上午10:30
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class TabReportFragmentFactory {
    private static TabReportFragmentFactory mInstance;

    public static TabReportFragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (TabReportFragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new TabReportFragmentFactory();
                }
            }
        }
        return mInstance;
    }

    /**
     * 水位模块
     */
    public WaterLevelFragment waterLevelFragment;

    public WaterLevelFragment getWaterLevelFragment() {
       /* if (waterLevelFragment == null) {
            synchronized (WaterLevelFragment.class) {
                if (waterLevelFragment == null) {
                    waterLevelFragment = new WaterLevelFragment();
                }
            }
        }*/
        waterLevelFragment = new WaterLevelFragment();

        return waterLevelFragment;
    }

    public EmergencyFragment emergencyFragment;

    public EmergencyFragment getEmergencyFragment() {
       /* if (emergencyFragment == null) {
            synchronized (EmergencyFragment.class) {
                if (emergencyFragment == null) {
                    emergencyFragment = new EmergencyFragment();
                }
            }
        }*/
        emergencyFragment = new EmergencyFragment();

        return emergencyFragment;
    }
}

