package xyz.xhui.awardsystem.model.entity;

import xyz.xhui.awardsystem.config.utils.MyTimeUtils;

import java.io.Serializable;

public class UnionScore implements Serializable {
    private Integer id;

    private Integer stuId;

    private Double score;

    private String remark;

    private Integer unionId;

    private Integer deptId;

    private Long createTime;

    public UnionScore() {
        this.createTime = MyTimeUtils.currentTimeMillis();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
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

    public Integer getUnionId() {
        return unionId;
    }

    public void setUnionId(Integer unionId) {
        this.unionId = unionId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UnionScore{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", unionId=" + unionId +
                ", deptId=" + deptId +
                ", createTime=" + createTime +
                '}';
    }
}
