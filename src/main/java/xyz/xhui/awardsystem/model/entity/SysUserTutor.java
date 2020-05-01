package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysUserTutor implements Serializable {
    private Integer id;

    private SysUser user;

    private Integer deptId;

    private Integer gradeId;

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

    @Override
    public String toString() {
        return "SysUserTutor{" +
                "id=" + id +
                ", user=" + user +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                '}';
    }
}
