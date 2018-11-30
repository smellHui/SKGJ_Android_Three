package com.tepia.main.view.main.map.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 44822 on 2018/4/14.
 */

public class SectionData {

    //数据类型 监测信息
    public static String[] dataType ={"巡河事件","公众上报","报警信息","水质站","排污口","雨量站","水位站","流量站","视频站","图像站"};
    //数据类型 基础信息
    public static String[] dataType2 ={"河流信息","河段信息","湖泊信息","河长信息","水库信息","电站信息","泵站信息","灌区信息","机电井信息","桥梁信息","拦河工程"};
    //数据类型 水资源监测
    public static String[] dataType3 ={"取水口","河段信息","湖泊信息","河长信息","水库信息","电站信息","泵站信息","灌区信息","机电井信息","桥梁信息","拦河工程"};
    //数据类型 水域岸线监测
    public static String[] dataType4 ={"保护区","保留区","控制利用区","开发利用区","侵占河道","围垦湖泊","违规临河建筑物","违规跨河建筑物","违规穿河建筑物","违规水上运动旅游项目","非法河湖采沙"};
    //数据类型 水污染监测
    public static String[] dataType5 ={"入河湖排污口","环河湖工矿企业","环河湖村镇","环河湖学校","环河湖医院","环河湖酒店","环河湖小区","其它环河湖建筑物","排水管网(雨水)","排水管网(污水)","污水处理场","饲养场"};
    //数据类型 水环境监测
    public static String[] dataType6 ={"水源地保护区","水源地保护区排污口","水源地保护区污染源","水源地保护区违法违规建筑物","饮水水源地隔离防护设施","饮水水源地警示牌","饮水水源地标示牌","垃圾填埋场","城市及农村垃圾堆积点","排水管网(污水)","污水处理场","饲养场"};
    //数据类型 水生态监测
    public static String[] dataType7 ={"河湖断面","河湖治理工程","河湖水系连通性","河湖水生生物资源","河湖淤泥含量","自然保护区","水源涵养区","江河源头区","生态敏感区","河湖生态基流水位"};
    //数据分类
    public static String[] dataMapTitle ={"监测信息","基础信息","水资源监测","水域岸线监测","水污染监测","水环境监测","水生态监测"};


    public static HashMap<String, String[]> setDataTypeHashMap(HashMap<String, String[]> dataTypeHashMap){
            dataTypeHashMap = new HashMap<>();
            ArrayList<String[]> arrayList = new ArrayList<>();
            arrayList.add(dataType);
            arrayList.add(dataType2);
            arrayList.add(dataType3);
            arrayList.add(dataType4);
            arrayList.add(dataType5);
            arrayList.add(dataType6);
            arrayList.add(dataType7);
            for (int i = 0; i < 7; i++) {
                dataTypeHashMap.put(dataMapTitle[i],arrayList.get(i));
            }
            return dataTypeHashMap;
    }

    public static void initSectionDatas(ArrayList<ArrayList> lists , List<String> group, List<LTntity> datas){
//        group = new ArrayList<String>();
        group.add("综合监控");
//        group.add("日志");

//        lists = new ArrayList<>();
        ArrayList<String> dataType = new ArrayList<>();
        dataType.add("水库");
        dataType.add("流量站");
        dataType.add("水质站");
        dataType.add("雨量站");
        dataType.add("水位站");
        dataType.add("图像站");
        dataType.add("视频站");
        ArrayList<String> dataType2 = new ArrayList<>();
//        dataType2.add("巡河日志");

        lists.add(dataType);
        lists.add(dataType2);

        for (int i = 0; i < group.size(); i++) {
            LTntity lTntity = new LTntity();
            lTntity.tagsName=group.get(i);
            lTntity.list=lists.get(i);
            datas.add(lTntity);
        }
    }

    public static void initSectionData(ArrayList<ArrayList> lists , List<String> group, List<LTntity> datas){
//        group = new ArrayList<String>();
        group.add("监测信息");
        group.add("基础信息");
        group.add("水资源监测");
        group.add("水域岸线监测");
        group.add("水污染监测");
        group.add("水环境监测");
        group.add("水生态监测");


//        lists = new ArrayList<>();
        ArrayList<String> dataType = new ArrayList<>();
       dataType.add("巡河事件");
       dataType.add("公众上报");
       dataType.add("报警信息");
       dataType.add("水质站");
       dataType.add("排污口");
       dataType.add("雨量站");
       dataType.add("水位站");
       dataType.add("流量站");
       dataType.add("视频站");
       dataType.add("图像站");
        ArrayList<String> dataType2 = new ArrayList<>();
        dataType2.add("河流信息");
        dataType2.add("河段信息");
        dataType2.add("湖泊信息");
        dataType2.add("河长信息");
        dataType2.add("水库信息");
        dataType2.add("电站信息");
        dataType2.add("泵站信息");
        dataType2.add("灌区信息");
        dataType2.add("机电井信息");
        dataType2.add("桥梁信息");
        dataType2.add("拦河工程");
        ArrayList<String> dataType3 = new ArrayList<>();
       dataType3.add("取水口");
       dataType3.add("水功能区");
       dataType3.add("供水电网");
        ArrayList<String> dataType4 = new ArrayList<>();
       dataType4.add("保护区");
       dataType4.add("保留区");
       dataType4.add("控制利用区");
       dataType4.add("开发利用区");
       dataType4.add("侵占河道");
       dataType4.add("围垦湖泊");
       dataType4.add("违规临河建筑物");
       dataType4.add("违规跨河建筑物");
       dataType4.add("违规穿河建筑物");
       dataType4.add("违规水上运动旅游项目");
       dataType4.add("非法河湖采沙");
        ArrayList<String> dataType5 = new ArrayList<>();
        dataType5.add("入河湖排污口");
        dataType5.add("环河湖工矿企业");
        dataType5.add("环河湖村镇");
        dataType5.add("环河湖学校");
        dataType5.add("环河湖医院");
        dataType5.add("环河湖酒店");
        dataType5.add("环河湖小区");
        dataType5.add("其它环河湖建筑物");
        dataType5.add("排水管网(雨水)");
        dataType5.add("排水管网(污水)");
        dataType5.add("污水处理场");
        dataType5.add("饲养场");
        ArrayList<String> dataType6 = new ArrayList<>();
      dataType6.add("水源地保护区");
      dataType6.add("水源地保护区排污口");
      dataType6.add("水源地保护区污染源");
      dataType6.add("水源地保护区违法违规建筑物");
      dataType6.add("饮水水源地隔离防护设施");
      dataType6.add("饮水水源地警示牌");
      dataType6.add("饮水水源地标示牌");
      dataType6.add("垃圾填埋场");
      dataType6.add("城市及农村垃圾堆积点");
      dataType6.add("城市黑臭水体");
      dataType6.add("水域岸线环境景观");
        ArrayList<String> dataType7 = new ArrayList<>();
        dataType7.add("河湖断面");
        dataType7.add("河湖治理工程");
        dataType7.add("河湖水系连通性");
        dataType7.add("河湖水生生物资源");
        dataType7.add("河湖淤泥含量");
        dataType7.add("自然保护区");
        dataType7.add("水源涵养区");
        dataType7.add("江河源头区");
        dataType7.add("生态敏感区");
        dataType7.add("河湖生态基流水位");

        lists.add(dataType);
        lists.add(dataType2);
        lists.add(dataType3);
        lists.add(dataType4);
        lists.add(dataType5);
        lists.add(dataType6);
        lists.add(dataType7);

        for (int i = 0; i < group.size(); i++) {
            LTntity lTntity = new LTntity();
            lTntity.tagsName=group.get(i);
            lTntity.list=lists.get(i);
            datas.add(lTntity);
        }
    }
}
