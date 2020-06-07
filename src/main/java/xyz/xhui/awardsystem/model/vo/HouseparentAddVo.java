package xyz.xhui.awardsystem.model.vo;

import java.io.Serializable;

public class HouseparentAddVo implements Serializable {
    private String username;
    private String realName;
    private String email;
    private String apartmentName;

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

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    @Override
    public String toString() {
        return "HouseparentAddVo{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                '}';
    }
}
