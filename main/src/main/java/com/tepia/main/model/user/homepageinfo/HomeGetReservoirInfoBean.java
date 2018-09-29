package com.tepia.main.model.user.homepageinfo;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-21
 * Time            :       14:52
 * Version         :       1.0
 * 功能描述        :
 **/
public class HomeGetReservoirInfoBean {
    /**
     * executeFrequency : {"maintain":{"noFlood":1,"flood":5},"clean":{"noFlood":2,"flood":6},"inspection":{"noFlood":1,"flood":5}}
     * material : [{"id":"ab432a971b87488091fea949e2c0633e","meName":"器材1","meType":"1","meTotals":"1套","position":"绿竹坝仓库"}]
     * personDuty : [{"jobName":"行政负责人","mobile":"","userName":"播州区管理科长"}]
     * reservoirWaterLevel : {"floodSeasonStartDate":"05-01","floodSeasonWaterLevel":973,"checkFloodWaterLevel":985.37,"dead_waterLevel":954,"isFlood":false,"designFloodWaterLevel":984.63,"reservoirId":"66fb3d579d084daf8a7d35d9d9612213","normalImpoundedLevel":983,"damCrestElevation":986,"floodSeasonEndDate":"05-31","stcd":"asdfasdf","tm":"2018-01-02 00:00:00","rz":"1000","w":"20"}
     * storageCapacity : [{"waterLevel":0,"storageCapacity":0}]
     * OAMStatistics : {"maintain":{"problemCount":0,"processedProblem":0,"workOrderCount":1,"notProcessedProblem":0},"clean":{"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0},"inspection":{"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0}}
     */

    private ExecuteFrequencyBean executeFrequency;
    private ReservoirWaterLevelBean reservoirWaterLevel;
    private OAMStatisticsBean OAMStatistics;
    private List<MaterialBean> material;
    private List<PersonDutyBean> personDuty;
    private List<StorageCapacityBean> storageCapacity;

    public ExecuteFrequencyBean getExecuteFrequency() {
        return executeFrequency;
    }

    public void setExecuteFrequency(ExecuteFrequencyBean executeFrequency) {
        this.executeFrequency = executeFrequency;
    }

    public ReservoirWaterLevelBean getReservoirWaterLevel() {
        return reservoirWaterLevel;
    }

    public void setReservoirWaterLevel(ReservoirWaterLevelBean reservoirWaterLevel) {
        this.reservoirWaterLevel = reservoirWaterLevel;
    }

    public OAMStatisticsBean getOAMStatistics() {
        return OAMStatistics;
    }

    public void setOAMStatistics(OAMStatisticsBean OAMStatistics) {
        this.OAMStatistics = OAMStatistics;
    }

    public List<MaterialBean> getMaterial() {
        return material;
    }

    public void setMaterial(List<MaterialBean> material) {
        this.material = material;
    }

    public List<PersonDutyBean> getPersonDuty() {
        return personDuty;
    }

    public void setPersonDuty(List<PersonDutyBean> personDuty) {
        this.personDuty = personDuty;
    }

    public List<StorageCapacityBean> getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(List<StorageCapacityBean> storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public static class ExecuteFrequencyBean {
        /**
         * maintain : {"noFlood":1,"flood":5}
         * clean : {"noFlood":2,"flood":6}
         * inspection : {"noFlood":1,"flood":5}
         */

        private MaintainBean maintain;
        private CleanBean clean;
        private InspectionBean inspection;

        public MaintainBean getMaintain() {
            return maintain;
        }

        public void setMaintain(MaintainBean maintain) {
            this.maintain = maintain;
        }

        public CleanBean getClean() {
            return clean;
        }

        public void setClean(CleanBean clean) {
            this.clean = clean;
        }

        public InspectionBean getInspection() {
            return inspection;
        }

        public void setInspection(InspectionBean inspection) {
            this.inspection = inspection;
        }

        public static class MaintainBean {
            /**
             * noFlood : 1
             * flood : 5
             */

            private int noFlood;
            private int flood;

            public int getNoFlood() {
                return noFlood;
            }

            public void setNoFlood(int noFlood) {
                this.noFlood = noFlood;
            }

            public int getFlood() {
                return flood;
            }

            public void setFlood(int flood) {
                this.flood = flood;
            }
        }

        public static class CleanBean {
            /**
             * noFlood : 2
             * flood : 6
             */

            private int noFlood;
            private int flood;

            public int getNoFlood() {
                return noFlood;
            }

            public void setNoFlood(int noFlood) {
                this.noFlood = noFlood;
            }

            public int getFlood() {
                return flood;
            }

            public void setFlood(int flood) {
                this.flood = flood;
            }
        }

        public static class InspectionBean {
            /**
             * noFlood : 1
             * flood : 5
             */

            private int noFlood;
            private int flood;

            public int getNoFlood() {
                return noFlood;
            }

            public void setNoFlood(int noFlood) {
                this.noFlood = noFlood;
            }

            public int getFlood() {
                return flood;
            }

            public void setFlood(int flood) {
                this.flood = flood;
            }
        }
    }

    public static class ReservoirWaterLevelBean {
        /**
         * floodSeasonStartDate : 05-01
         * floodSeasonWaterLevel : 973
         * checkFloodWaterLevel : 985.37
         * dead_waterLevel : 954
         * isFlood : false
         * designFloodWaterLevel : 984.63
         * reservoirId : 66fb3d579d084daf8a7d35d9d9612213
         * normalImpoundedLevel : 983
         * damCrestElevation : 986
         * floodSeasonEndDate : 05-31
         * stcd : asdfasdf
         * tm : 2018-01-02 00:00:00
         * rz : 1000
         * w : 20
         */

        private String floodSeasonStartDate;
        private Float floodSeasonWaterLevel;
        private Double checkFloodWaterLevel;
        private Float dead_waterLevel;
        private Boolean isFlood;
        private Double designFloodWaterLevel;
        private String reservoirId;
        private Float normalImpoundedLevel;
        private Float damCrestElevation;
        private String floodSeasonEndDate;
        private String stcd;
        private String tm;
        private String rz;
        private String w;

        public String getFloodSeasonStartDate() {
            return floodSeasonStartDate;
        }

        public void setFloodSeasonStartDate(String floodSeasonStartDate) {
            this.floodSeasonStartDate = floodSeasonStartDate;
        }

        public Float getFloodSeasonWaterLevel() {
            return floodSeasonWaterLevel;
        }

        public void setFloodSeasonWaterLevel(Float floodSeasonWaterLevel) {
            this.floodSeasonWaterLevel = floodSeasonWaterLevel;
        }

        public Double getCheckFloodWaterLevel() {
            return checkFloodWaterLevel;
        }

        public void setCheckFloodWaterLevel(Double checkFloodWaterLevel) {
            this.checkFloodWaterLevel = checkFloodWaterLevel;
        }

        public Float getDead_waterLevel() {
            return dead_waterLevel;
        }

        public void setDead_waterLevel(Float dead_waterLevel) {
            this.dead_waterLevel = dead_waterLevel;
        }

        public Boolean getFlood() {
            return isFlood;
        }

        public void setFlood(Boolean flood) {
            isFlood = flood;
        }

        public Double getDesignFloodWaterLevel() {
            return designFloodWaterLevel;
        }

        public void setDesignFloodWaterLevel(Double designFloodWaterLevel) {
            this.designFloodWaterLevel = designFloodWaterLevel;
        }

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public Float getNormalImpoundedLevel() {
            return normalImpoundedLevel;
        }

        public void setNormalImpoundedLevel(Float normalImpoundedLevel) {
            this.normalImpoundedLevel = normalImpoundedLevel;
        }

        public Float getDamCrestElevation() {
            return damCrestElevation;
        }

        public void setDamCrestElevation(Float damCrestElevation) {
            this.damCrestElevation = damCrestElevation;
        }

        public String getFloodSeasonEndDate() {
            return floodSeasonEndDate;
        }

        public void setFloodSeasonEndDate(String floodSeasonEndDate) {
            this.floodSeasonEndDate = floodSeasonEndDate;
        }

        public String getStcd() {
            return stcd;
        }

        public void setStcd(String stcd) {
            this.stcd = stcd;
        }

        public String getTm() {
            return tm;
        }

        public void setTm(String tm) {
            this.tm = tm;
        }

        public String getRz() {
            return rz;
        }

        public void setRz(String rz) {
            this.rz = rz;
        }

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }
    }

    public static class OAMStatisticsBean {
        /**
         * maintain : {"problemCount":0,"processedProblem":0,"workOrderCount":1,"notProcessedProblem":0}
         * clean : {"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0}
         * inspection : {"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0}
         */

        private MaintainBeanX maintain;
        private CleanBeanX clean;
        private InspectionBeanX inspection;

        public MaintainBeanX getMaintain() {
            return maintain;
        }

        public void setMaintain(MaintainBeanX maintain) {
            this.maintain = maintain;
        }

        public CleanBeanX getClean() {
            return clean;
        }

        public void setClean(CleanBeanX clean) {
            this.clean = clean;
        }

        public InspectionBeanX getInspection() {
            return inspection;
        }

        public void setInspection(InspectionBeanX inspection) {
            this.inspection = inspection;
        }

        public static class MaintainBeanX {
            /**
             * problemCount : 0
             * processedProblem : 0
             * workOrderCount : 1
             * notProcessedProblem : 0
             */

            private int problemCount;
            private int processedProblem;
            private int workOrderCount;
            private int notProcessedProblem;

            public int getProblemCount() {
                return problemCount;
            }

            public void setProblemCount(int problemCount) {
                this.problemCount = problemCount;
            }

            public int getProcessedProblem() {
                return processedProblem;
            }

            public void setProcessedProblem(int processedProblem) {
                this.processedProblem = processedProblem;
            }

            public int getWorkOrderCount() {
                return workOrderCount;
            }

            public void setWorkOrderCount(int workOrderCount) {
                this.workOrderCount = workOrderCount;
            }

            public int getNotProcessedProblem() {
                return notProcessedProblem;
            }

            public void setNotProcessedProblem(int notProcessedProblem) {
                this.notProcessedProblem = notProcessedProblem;
            }
        }

        public static class CleanBeanX {
            /**
             * problemCount : 0
             * processedProblem : 0
             * workOrderCount : 0
             * notProcessedProblem : 0
             */

            private int problemCount;
            private int processedProblem;
            private int workOrderCount;
            private int notProcessedProblem;

            public int getProblemCount() {
                return problemCount;
            }

            public void setProblemCount(int problemCount) {
                this.problemCount = problemCount;
            }

            public int getProcessedProblem() {
                return processedProblem;
            }

            public void setProcessedProblem(int processedProblem) {
                this.processedProblem = processedProblem;
            }

            public int getWorkOrderCount() {
                return workOrderCount;
            }

            public void setWorkOrderCount(int workOrderCount) {
                this.workOrderCount = workOrderCount;
            }

            public int getNotProcessedProblem() {
                return notProcessedProblem;
            }

            public void setNotProcessedProblem(int notProcessedProblem) {
                this.notProcessedProblem = notProcessedProblem;
            }
        }

        public static class InspectionBeanX {
            /**
             * problemCount : 0
             * processedProblem : 0
             * workOrderCount : 0
             * notProcessedProblem : 0
             */

            private int problemCount;
            private int processedProblem;
            private int workOrderCount;
            private int notProcessedProblem;

            public int getProblemCount() {
                return problemCount;
            }

            public void setProblemCount(int problemCount) {
                this.problemCount = problemCount;
            }

            public int getProcessedProblem() {
                return processedProblem;
            }

            public void setProcessedProblem(int processedProblem) {
                this.processedProblem = processedProblem;
            }

            public int getWorkOrderCount() {
                return workOrderCount;
            }

            public void setWorkOrderCount(int workOrderCount) {
                this.workOrderCount = workOrderCount;
            }

            public int getNotProcessedProblem() {
                return notProcessedProblem;
            }

            public void setNotProcessedProblem(int notProcessedProblem) {
                this.notProcessedProblem = notProcessedProblem;
            }
        }
    }


    public static class StorageCapacityBean {
        /**
         * waterLevel : 0
         * storageCapacity : 0
         */

        private float waterLevel;
        private float storageCapacity;

        public float getWaterLevel() {
            return waterLevel;
        }

        public void setWaterLevel(float waterLevel) {
            this.waterLevel = waterLevel;
        }

        public float getStorageCapacity() {
            return storageCapacity;
        }

        public void setStorageCapacity(float storageCapacity) {
            this.storageCapacity = storageCapacity;
        }
    }
}
