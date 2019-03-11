package com.tepia.main.model.task.bean;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.tepia.main.model.image.ImageInfoBean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 任务项实体
 * Created by Joeshould on 2018/5/31.
 */

public class TaskItemBean extends DataSupport implements Serializable {

    /**
     * itemId : 8c5fa5f84035460db6245146f7efeb39
     * positionId : 089b678b89e046f0a86cd85dd2fecdba
     * superviseItemCode : SK-QSB-1-1
     * reservoirSuperviseSequence : 1
     * executeId : a623db0bdb92434ebe6553561aaf2eef
     * executeResultType : 0
     * executeResultDescription : 发生的JFK时间d
     * excuteLongitude : 123
     * excuteLatitude : 123
     * excuteDate : 2018-07-19 00:00:00
     * status : 0
     * createBy : 播州test1
     * createDate : 2018-07-19 15:44:11
     * updateBy : 播州test1
     * updateDate : 2018-07-19 15:44:13
     * treeNames : 土石坝/坝体/坝坡
     * superviseItemName : 消防器材
     * superviseShowType : 1
     * dicName : 有,无
     * iSysFileUploads : [{"id":"b9c7fe90da2940eca62ec65a23af9ad9","fileId":"b0a0d83381f64191bde9762ba142d9ea","fileName":"u=2936254456,1602353344&fm=27&gp=0.jpg","fileType":"jpg","bizKey":"8c5fa5f84035460db6245146f7efeb39","bizType":"start","status":"0","createBy":"播州test1","createDate":"2018-07-19 15:44:11","updateBy":"播州test1","updateDate":"2018-07-19 15:44:13","filePath":"http://tepia-test-2018.oss-cn-beijing.aliyuncs.com/APP/Cleaning/2018-07/19/b0a0d83381f64191bde9762ba142d9ea.jpg","fileContentType":"jpg","fileExtension":"jpg","fileSize":20930}]
     */


    @Column(unique = true,nullable = false)
    private String itemId;
    private String reservoirSuperviseId;
    private boolean isSelected;
    private String reservoirId;
    private String planId;
    private String workOrderId;
    private String positionId;
    private String positionTreeNames;
    private String positionName;
    private String positionLongitude;
    private String positionLatitude;
    private String superviseItemCode;
    private String superviseItemName;
    private String content;
    private String resultType;
    private int reservoirSuperviseSequence;
    private String executeId;
    private String completeStatus;
    private String executeResultType;
    private String executeResultDescription;
    private String excuteLongitude;
    private String excuteLatitude;
    private String excuteDate;

    private String examinedId;
    private String examinedResultType;
    private String examinedResultDescription;
    private String examinedDate;

    private String status;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private String remarks;
    private String superviseShowType;
    private String dicName;
    private List<ImageInfoBean> startImages;
    private List<ImageInfoBean> endImages;

    public String getReservoirSuperviseId() {
        return reservoirSuperviseId;
    }

    public void setReservoirSuperviseId(String reservoirSuperviseId) {
        this.reservoirSuperviseId = reservoirSuperviseId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(String reservoirId) {
        this.reservoirId = reservoirId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionTreeNames() {
        return positionTreeNames;
    }

    public void setPositionTreeNames(String positionTreeNames) {
        this.positionTreeNames = positionTreeNames;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionLongitude() {
        return positionLongitude;
    }

    public void setPositionLongitude(String positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public String getPositionLatitude() {
        return positionLatitude;
    }

    public void setPositionLatitude(String positionLatitude) {
        this.positionLatitude = positionLatitude;
    }

    public String getSuperviseItemCode() {
        return superviseItemCode;
    }

    public void setSuperviseItemCode(String superviseItemCode) {
        this.superviseItemCode = superviseItemCode;
    }

    public String getSuperviseItemName() {
        return superviseItemName;
    }

    public void setSuperviseItemName(String superviseItemName) {
        this.superviseItemName = superviseItemName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getReservoirSuperviseSequence() {
        return reservoirSuperviseSequence;
    }

    public void setReservoirSuperviseSequence(int reservoirSuperviseSequence) {
        this.reservoirSuperviseSequence = reservoirSuperviseSequence;
    }

    public String getExecuteId() {
        return executeId;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getExecuteResultType() {
        return executeResultType;
    }

    public void setExecuteResultType(String executeResultType) {
        this.executeResultType = executeResultType;
    }

    public String getExecuteResultDescription() {
        return executeResultDescription;
    }

    public void setExecuteResultDescription(String executeResultDescription) {
        this.executeResultDescription = executeResultDescription;
    }

    public String getExcuteLongitude() {
        return excuteLongitude;
    }

    public void setExcuteLongitude(String excuteLongitude) {
        this.excuteLongitude = excuteLongitude;
    }

    public String getExcuteLatitude() {
        return excuteLatitude;
    }

    public void setExcuteLatitude(String excuteLatitude) {
        this.excuteLatitude = excuteLatitude;
    }

    public String getExcuteDate() {
        return excuteDate;
    }

    public void setExcuteDate(String excuteDate) {
        this.excuteDate = excuteDate;
    }

    public String getExaminedId() {
        return examinedId;
    }

    public void setExaminedId(String examinedId) {
        this.examinedId = examinedId;
    }

    public String getExaminedResultType() {
        return examinedResultType;
    }

    public void setExaminedResultType(String examinedResultType) {
        this.examinedResultType = examinedResultType;
    }

    public String getExaminedResultDescription() {
        return examinedResultDescription;
    }

    public void setExaminedResultDescription(String examinedResultDescription) {
        this.examinedResultDescription = examinedResultDescription;
    }

    public String getExaminedDate() {
        return examinedDate;
    }

    public void setExaminedDate(String examinedDate) {
        this.examinedDate = examinedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSuperviseShowType() {
        return superviseShowType;
    }

    public void setSuperviseShowType(String superviseShowType) {
        this.superviseShowType = superviseShowType;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public List<ImageInfoBean> getStartImages() {
        return startImages;
    }

    public void setStartImages(List<ImageInfoBean> startImages) {
        this.startImages = startImages;
    }

    public List<ImageInfoBean> getEndImages() {
        return endImages;
    }

    public void setEndImages(List<ImageInfoBean> endImages) {
        this.endImages = endImages;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskItemBean that = (TaskItemBean) o;
        if (TextUtils.isEmpty(itemId)){
            return false;
        }
        return itemId.equals(that.itemId);
//        return Objects.equals(itemId, that.itemId);


    }

}
