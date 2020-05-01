package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysUserUnion implements Serializable {
    private Integer id;

    private SysUser user;

    private Integer deptId;

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

    @Override
    public String toString() {
        return "SysUserUnion{" +
                "id=" + id +
                ", user=" + user +
                ", deptId=" + deptId +
                '}';
    }
}
