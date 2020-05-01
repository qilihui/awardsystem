package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysUserHouseparent implements Serializable {
    private Integer id;

    private SysUser user;

    private Integer apartmentId;

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

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    @Override
    public String toString() {
        return "SysUserHouseparent{" +
                "id=" + id +
                ", user=" + user +
                ", apartmentId=" + apartmentId +
                '}';
    }
}
