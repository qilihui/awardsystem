package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class SysUserAdmin implements Serializable {
    private Integer id;

    private SysUser user;

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

    @Override
    public String toString() {
        return "SysUserAdmin{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
