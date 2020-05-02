package xyz.xhui.awardsystem.model.dto;

public class StuDto {
    private String username;
    private String roleName;
    private String email;
    private Integer room;
    private Integer bed;
    private String apartmentName;
    private String deptName;
    private String gradeName;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", roleName='" + roleName + '\'' +
                ", email='" + email + '\'' +
                ", room=" + room +
                ", bed=" + bed +
                ", apartmentName='" + apartmentName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
