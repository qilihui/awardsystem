package xyz.xhui.awardsystem.model.dto;

import xyz.xhui.awardsystem.model.entity.SysUser;

import java.io.Serializable;

public class SysUserDto implements Serializable {
    private Integer id;
    private String username;
    private String roleName;
    private String role;
    private String realName;
    private String email;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SysUserDto() {
    }

    public SysUserDto(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roleName = user.getRole().getName();
        this.role = user.getRole().toString();
        this.realName = user.getRealName();
        this.email = user.getEmail();
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        return "SysUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                ", role='" + role + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
