package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysUserStu implements Serializable {
    private Integer id;

    private SysUser user;

    private Integer deptId;

    private Integer gradeId;

    private Integer apartmentId;

    private Integer room;

    private Integer bed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
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

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
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
        return "SysUserStu{" +
                "id=" + id +
                ", user=" + user +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                ", apartmentId=" + apartmentId +
                ", room=" + room +
                ", bed=" + bed +
                '}';
    }
}
