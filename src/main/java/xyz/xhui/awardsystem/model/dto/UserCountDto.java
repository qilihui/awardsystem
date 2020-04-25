package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class UserCountDto implements Serializable {
    private Integer allCount;
    private Integer adminCount;
    private Integer tutorCount;
    private Integer houseparentCount;
    private Integer unionCount;
    private Integer stuCount;

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }

    public Integer getTutorCount() {
        return tutorCount;
    }

    public void setTutorCount(Integer tutorCount) {
        this.tutorCount = tutorCount;
    }

    public Integer getHouseparentCount() {
        return houseparentCount;
    }

    public void setHouseparentCount(Integer houseparentCount) {
        this.houseparentCount = houseparentCount;
    }

    public Integer getUnionCount() {
        return unionCount;
    }

    public void setUnionCount(Integer unionCount) {
        this.unionCount = unionCount;
    }

    public Integer getStuCount() {
        return stuCount;
    }

    public void setStuCount(Integer stuCount) {
        this.stuCount = stuCount;
    }

    @Override
    public String toString() {
        return "UserCountDto{" +
                "allCount=" + allCount +
                ", adminCount=" + adminCount +
                ", tutorCount=" + tutorCount +
                ", houseparentCount=" + houseparentCount +
                ", unionCount=" + unionCount +
                ", stuCount=" + stuCount +
                '}';
    }
}
