package xyz.xhui.awardsystem.model.dto;

public class ScoreDto {
    private Integer id;
    private String username;
    private String realName;
    private Integer score;
    private String remark;
    private String submitter;
    private Integer createTime;

    public ScoreDto() {
    }

    public ScoreDto(Integer id, String username, String realName, Integer score, String remark, String submitter, Integer createTime) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.score = score;
        this.remark = remark;
        this.submitter = submitter;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "ScoreDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", submitter='" + submitter + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
