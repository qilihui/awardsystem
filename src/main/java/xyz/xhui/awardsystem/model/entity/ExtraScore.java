package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class ExtraScore implements Serializable {
    private Integer id;
    private double score;
    private String remark;
    private String path;
    private Integer timeId;
    private Integer termId;
    private Integer stuId;
    private Integer deptId;
    private Integer gradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "ExtraScore{" +
                "id=" + id +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", path='" + path + '\'' +
                ", timeId=" + timeId +
                ", termId=" + termId +
                ", stuId=" + stuId +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                '}';
    }
}
