package xyz.xhui.awardsystem.model.entity;

import xyz.xhui.awardsystem.config.sysenum.RoleEnum;

import java.io.Serializable;

public class SysUser implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private RoleEnum role = RoleEnum.ROLE_ADMIN;

    private String realName;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
