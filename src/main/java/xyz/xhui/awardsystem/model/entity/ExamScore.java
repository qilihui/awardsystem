package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class ExamScore implements Serializable {
    private Integer id;
    private Integer stuId;
    private Double score;
    private String remark;
    private Integer count;
    private Integer termId;
    private Integer deptId;
    private Integer gradeId;

    public ExamScore() {
    }

    public ExamScore(Integer id, Integer stuId, Double score, String remark, Integer count, Integer termId, Integer deptId, Integer gradeId) {
        this.id = id;
        this.stuId = stuId;
        this.score = score;
        this.remark = remark;
        this.count = count;
        this.termId = termId;
        this.deptId = deptId;
        this.gradeId = gradeId;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
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
        return "ExamScore{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", count=" + count +
                ", termId=" + termId +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                '}';
    }
}
