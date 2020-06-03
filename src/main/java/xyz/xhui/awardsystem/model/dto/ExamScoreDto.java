package xyz.xhui.awardsystem.model.dto;

import xyz.xhui.awardsystem.model.entity.ExamScore;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.io.Serializable;

public class ExamScoreDto implements Serializable {
    private Integer id;
    private Integer stuId;
    private Double score;
    private String remark;
    private Integer count;
    private Integer termId;
    private String username;
    private String realName;
    private String deptName;
    private String gradeName;

    public ExamScoreDto() {
    }

    public ExamScoreDto(ExamScore examScore, SysUserStu stu) {
        this.id = examScore.getId();
        this.stuId = examScore.getStuId();
        this.score = examScore.getScore();
        this.remark = examScore.getRemark();
        this.count = examScore.getCount();
        this.termId = examScore.getTermId();
        this.username = stu.getUser().getUsername();
        this.realName = stu.getUser().getRealName();
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
        return "ExamScoreDto{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", count=" + count +
                ", termId=" + termId +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
