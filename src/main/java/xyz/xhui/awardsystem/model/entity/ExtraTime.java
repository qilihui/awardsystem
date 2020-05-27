package xyz.xhui.awardsystem.model.entity;

import java.io.Serializable;

public class ExtraTime implements Serializable {
    private Integer id;
    private String name;
    private Integer termId;
    private Long beginTime;
    private Long endTime;
    private Integer deptId;
    private Integer gradeId;
    private Integer tutorId;

    public ExtraTime() {
    }

    public ExtraTime(String name, Integer termId, Long beginTime, Long endTime) {
        this.name = name;
        this.termId = termId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    @Override
    public String toString() {
        return "ExtraTime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", termId=" + termId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", deptId=" + deptId +
                ", gradeId=" + gradeId +
                ", tutorId=" + tutorId +
                '}';
    }
}
