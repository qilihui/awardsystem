package xyz.xhui.awardsystem.model.entity;

import xyz.xhui.awardsystem.config.utils.MyTimeUtils;

import java.io.Serializable;

public class ApartmentScore implements Serializable {
    private Integer id;

    private Integer apartmentId;

    private Integer room;

    private Integer bed;

    private Double score;

    private String remark;

    private Long createTime;

    public ApartmentScore() {
        this.createTime = MyTimeUtils.currentTimeMillis();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ApartmentScore{" +
                "id=" + id +
                ", apartmentId=" + apartmentId +
                ", room=" + room +
                ", bed=" + bed +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
