package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class UserInfoDto implements Serializable {
    private Integer apartmentId;
    private Integer deptId;
    private Integer gradeId;
    private Integer room;
    private Integer bed;
    private Integer userInfoId;

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "apartmentId=" + apartmentId +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                ", room=" + room +
                ", bed=" + bed +
                ", userInfoId=" + userInfoId +
                '}';
    }
}
