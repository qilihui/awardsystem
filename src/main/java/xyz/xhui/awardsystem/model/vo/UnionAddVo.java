package xyz.xhui.awardsystem.model.vo;

import java.io.Serializable;

public class UnionAddVo implements Serializable {
    private String username;
    private String realName;
    private String email;
    private String deptName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "TutorAddVo{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
