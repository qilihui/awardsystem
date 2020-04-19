package xyz.xhui.awardsystem.model.entity;

import xyz.xhui.awardsystem.config.utils.MyTimeUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "union_score")
public class UnionScore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private SysUserStu userStu;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "remark")
    private String remark;

    @Column(name = "submitter")
    private String submitter;

    @Column(name = "crate_time", nullable = false)
    private Integer createTime;

    public UnionScore() {
        this.createTime = MyTimeUtils.getTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysUserStu getUserStu() {
        return userStu;
    }

    public void setUserStu(SysUserStu sysUserStu) {
        this.userStu = sysUserStu;
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

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UnionScore{" +
                "id=" + id +
                ", sysUserStu=" + userStu +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", submitter='" + submitter + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
