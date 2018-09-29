package com.tepia.main.model.dictmap;

import java.util.List;
import java.util.Map;

/**
 * Created by Joeshould on 2018/7/31.
 */

public class DictMapEntity {
    private ArrayBean array;
    private ObjectBean object;

    public ArrayBean getArray() {
        return array;
    }

    public void setArray(ArrayBean array) {
        this.array = array;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ArrayBean {
        private List<NameValueBean> sys_code;
        private List<NameValueBean> VIDEO_TYPE;
        private List<NameValueBean> planType;
        private List<NameValueBean> menu_type;
        private List<NameValueBean> executeStatus;
        private List<NameValueBean> dam_type;
        private List<NameValueBean> sttp;
        private List<NameValueBean> problemCofirmType;
        private List<NameValueBean> isAdmin;
        private List<NameValueBean> reservoir_type;
        private List<NameValueBean> is_show;
        private List<NameValueBean> superviseShowType;
        private List<NameValueBean> problemStatus;
        private List<NameValueBean> ACCESS_TYPE;
        private List<NameValueBean> login_scope;
        private List<NameValueBean> operationType;
        private List<NameValueBean> isSys;
        private List<NameValueBean> status;
        private List<NameValueBean> problem_source;
        private List<NameValueBean> procedure_type;
        private List<NameValueBean> report_type;

        public List<NameValueBean> getSys_code() {
            return sys_code;
        }

        public void setSys_code(List<NameValueBean> sys_code) {
            this.sys_code = sys_code;
        }

        public List<NameValueBean> getVIDEO_TYPE() {
            return VIDEO_TYPE;
        }

        public void setVIDEO_TYPE(List<NameValueBean> VIDEO_TYPE) {
            this.VIDEO_TYPE = VIDEO_TYPE;
        }

        public List<NameValueBean> getPlanType() {
            return planType;
        }

        public void setPlanType(List<NameValueBean> planType) {
            this.planType = planType;
        }

        public List<NameValueBean> getMenu_type() {
            return menu_type;
        }

        public void setMenu_type(List<NameValueBean> menu_type) {
            this.menu_type = menu_type;
        }

        public List<NameValueBean> getExecuteStatus() {
            return executeStatus;
        }

        public void setExecuteStatus(List<NameValueBean> executeStatus) {
            this.executeStatus = executeStatus;
        }

        public List<NameValueBean> getDam_type() {
            return dam_type;
        }

        public void setDam_type(List<NameValueBean> dam_type) {
            this.dam_type = dam_type;
        }

        public List<NameValueBean> getSttp() {
            return sttp;
        }

        public void setSttp(List<NameValueBean> sttp) {
            this.sttp = sttp;
        }

        public List<NameValueBean> getProblemCofirmType() {
            return problemCofirmType;
        }

        public void setProblemCofirmType(List<NameValueBean> problemCofirmType) {
            this.problemCofirmType = problemCofirmType;
        }

        public List<NameValueBean> getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(List<NameValueBean> isAdmin) {
            this.isAdmin = isAdmin;
        }

        public List<NameValueBean> getReservoir_type() {
            return reservoir_type;
        }

        public void setReservoir_type(List<NameValueBean> reservoir_type) {
            this.reservoir_type = reservoir_type;
        }

        public List<NameValueBean> getIs_show() {
            return is_show;
        }

        public void setIs_show(List<NameValueBean> is_show) {
            this.is_show = is_show;
        }

        public List<NameValueBean> getSuperviseShowType() {
            return superviseShowType;
        }

        public void setSuperviseShowType(List<NameValueBean> superviseShowType) {
            this.superviseShowType = superviseShowType;
        }

        public List<NameValueBean> getProblemStatus() {
            return problemStatus;
        }

        public void setProblemStatus(List<NameValueBean> problemStatus) {
            this.problemStatus = problemStatus;
        }

        public List<NameValueBean> getACCESS_TYPE() {
            return ACCESS_TYPE;
        }

        public void setACCESS_TYPE(List<NameValueBean> ACCESS_TYPE) {
            this.ACCESS_TYPE = ACCESS_TYPE;
        }

        public List<NameValueBean> getLogin_scope() {
            return login_scope;
        }

        public void setLogin_scope(List<NameValueBean> login_scope) {
            this.login_scope = login_scope;
        }

        public List<NameValueBean> getOperationType() {
            return operationType;
        }

        public void setOperationType(List<NameValueBean> operationType) {
            this.operationType = operationType;
        }

        public List<NameValueBean> getIsSys() {
            return isSys;
        }

        public void setIsSys(List<NameValueBean> isSys) {
            this.isSys = isSys;
        }

        public List<NameValueBean> getStatus() {
            return status;
        }

        public void setStatus(List<NameValueBean> status) {
            this.status = status;
        }

        public List<NameValueBean> getProblem_source() {
            return problem_source;
        }

        public void setProblem_source(List<NameValueBean> problem_source) {
            this.problem_source = problem_source;
        }

        public List<NameValueBean> getProcedure_type() {
            return procedure_type;
        }

        public void setProcedure_type(List<NameValueBean> procedure_type) {
            this.procedure_type = procedure_type;
        }

        public List<NameValueBean> getReport_type() {
            return report_type;
        }

        public void setReport_type(List<NameValueBean> report_type) {
            this.report_type = report_type;
        }

        public static class NameValueBean {

            /**
             * name : APP菜单
             * value : mobileApp
             */

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public class ObjectBean {
        /**
         * sys_code : {"default":"主导航菜单","mobileApp":"APP菜单"}
         * VIDEO_TYPE : {"0":"水库视频","1":"大坝视频","2":"其他视频"}
         * planType : {"1":"日常巡检","2":"定期巡检","3":"特别巡检"}
         * menu_type : {"1":"菜单","2":"功能","3":"链接"}
         * executeStatus : {"0":"未下发","1":"待执行","2":"执行中","3":"已提交","4":"已通过审核","5":"已生成报告"}
         * dam_type : {"0":"土石坝","1":"混凝土坝"}
         * sttp : {"PP":"雨量站","RR":"水库水文站","MM":"气象站","II":"图像站","ZQ":"流量站","WQ":"水质站"}
         * problemCofirmType : {"0":"不是问题","1":"一般问题","2":"严重问题"}
         * isAdmin : {"0":"否","1":"是"}
         * reservoir_type : {"1":"小（一）型","2":"小（二）型","3":"中型","4":"大（一）型","5":"大（二）型"}
         * is_show : {"0":"隐藏","1":"显示"}
         * superviseShowType : {"0":"0:正常,1:不正常","1":"0:有,1:无","2":"0:完成,1:未完成"}
         * problemStatus : {"0":"待确认","1":"已确认","2":"已审核","3":"业主已审核","4":"已完结（反馈操作）"}
         * ACCESS_TYPE : {"0":"IP","1":"SIM卡"}
         * login_scope : {"0":"PC","1":"APP","2":"公众号"}
         * operationType : {"1":"巡检","2":"维护","3":"保洁","4":"其他类型"}
         * isSys : {"0":"否","1":"是"}
         * status : {"0":"正常","2":"禁用"}
         */

        private Map<String,String> sys_code;
        private Map<String,String> VIDEO_TYPE;
        private Map<String,String> planType;
        private Map<String,String> menu_type;
        private Map<String,String> executeStatus;
        private Map<String,String> dam_type;
        private Map<String,String> sttp;
        private Map<String,String> problemCofirmType;
        private Map<String,String> isAdmin;
        private Map<String,String> reservoir_type;
        private Map<String,String> is_show;
        private Map<String,String> superviseShowType;
        private Map<String,String> problemStatus;
        private Map<String,String> ACCESS_TYPE;
        private Map<String,String> login_scope;
        private Map<String,String> operationType;
        private Map<String,String> isSys;
        private Map<String,String> status;
        private Map<String,String> report_type;

        public Map<String, String> getSys_code() {
            return sys_code;
        }

        public void setSys_code(Map<String, String> sys_code) {
            this.sys_code = sys_code;
        }

        public Map<String, String> getVIDEO_TYPE() {
            return VIDEO_TYPE;
        }

        public void setVIDEO_TYPE(Map<String, String> VIDEO_TYPE) {
            this.VIDEO_TYPE = VIDEO_TYPE;
        }

        public Map<String, String> getPlanType() {
            return planType;
        }

        public void setPlanType(Map<String, String> planType) {
            this.planType = planType;
        }

        public Map<String, String> getMenu_type() {
            return menu_type;
        }

        public void setMenu_type(Map<String, String> menu_type) {
            this.menu_type = menu_type;
        }

        public Map<String, String> getExecuteStatus() {
            return executeStatus;
        }

        public void setExecuteStatus(Map<String, String> executeStatus) {
            this.executeStatus = executeStatus;
        }

        public Map<String, String> getDam_type() {
            return dam_type;
        }

        public void setDam_type(Map<String, String> dam_type) {
            this.dam_type = dam_type;
        }

        public Map<String, String> getSttp() {
            return sttp;
        }

        public void setSttp(Map<String, String> sttp) {
            this.sttp = sttp;
        }

        public Map<String, String> getProblemCofirmType() {
            return problemCofirmType;
        }

        public void setProblemCofirmType(Map<String, String> problemCofirmType) {
            this.problemCofirmType = problemCofirmType;
        }

        public Map<String, String> getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(Map<String, String> isAdmin) {
            this.isAdmin = isAdmin;
        }

        public Map<String, String> getReservoir_type() {
            return reservoir_type;
        }

        public void setReservoir_type(Map<String, String> reservoir_type) {
            this.reservoir_type = reservoir_type;
        }

        public Map<String, String> getIs_show() {
            return is_show;
        }

        public void setIs_show(Map<String, String> is_show) {
            this.is_show = is_show;
        }

        public Map<String, String> getSuperviseShowType() {
            return superviseShowType;
        }

        public void setSuperviseShowType(Map<String, String> superviseShowType) {
            this.superviseShowType = superviseShowType;
        }

        public Map<String, String> getProblemStatus() {
            return problemStatus;
        }

        public void setProblemStatus(Map<String, String> problemStatus) {
            this.problemStatus = problemStatus;
        }

        public Map<String, String> getACCESS_TYPE() {
            return ACCESS_TYPE;
        }

        public void setACCESS_TYPE(Map<String, String> ACCESS_TYPE) {
            this.ACCESS_TYPE = ACCESS_TYPE;
        }

        public Map<String, String> getLogin_scope() {
            return login_scope;
        }

        public void setLogin_scope(Map<String, String> login_scope) {
            this.login_scope = login_scope;
        }

        public Map<String, String> getOperationType() {
            return operationType;
        }

        public void setOperationType(Map<String, String> operationType) {
            this.operationType = operationType;
        }

        public Map<String, String> getIsSys() {
            return isSys;
        }

        public void setIsSys(Map<String, String> isSys) {
            this.isSys = isSys;
        }

        public Map<String, String> getStatus() {
            return status;
        }

        public void setStatus(Map<String, String> status) {
            this.status = status;
        }

        public Map<String, String> getReport_type() {
            return report_type;
        }

        public void setReport_type(Map<String, String> report_type) {
            this.report_type = report_type;
        }
    }


}
