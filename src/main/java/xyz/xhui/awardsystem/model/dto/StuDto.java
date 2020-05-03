package xyz.xhui.awardsystem.model.dto;

public class StuDto {
    private String username;
    private String realName;
    private String email;
    private String room;
    private String bed;
    private String apartmentName;
    private String deptName;
    private String gradeName;

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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "StuDto{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", room=" + room +
                ", bed=" + bed +
                ", apartmentName='" + apartmentName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", gradeName=" + gradeName +
                '}';
    }
}
