package xyz.xhui.awardsystem.model.entity;

import xyz.xhui.awardsystem.config.utils.MyTimeUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "apartment_score")
public class ApartmentScore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private SysApartment apartment;

    @Column(name = "room", nullable = false)
    private Integer room;

    @Column(name = "bed", nullable = false)
    private Integer bed;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "remark")
    private String remark;

    @Column(name = "crate_time", nullable = false)
    private Integer createTime;

    public ApartmentScore() {
        this.createTime = MyTimeUtils.getTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysApartment getApartment() {
        return apartment;
    }

    public void setApartment(SysApartment apartment) {
        this.apartment = apartment;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer crateTime) {
        this.createTime = crateTime;
    }

    @Override
    public String toString() {
        return "ApartmentScore{" +
                "id=" + id +
                ", apartment=" + apartment +
                ", room=" + room +
                ", bed=" + bed +
                ", score=" + score +
                ", crateTime=" + createTime +
                '}';
    }
}
