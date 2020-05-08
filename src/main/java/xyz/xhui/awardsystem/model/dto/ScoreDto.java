package xyz.xhui.awardsystem.model.dto;

public class ScoreDto {
    private Integer id;
    private String username;
    private String realName;
    private Integer score;
    private String scoreStr;
    private String remark;
    private String submitter;
    private Long createTime;

    public ScoreDto() {
    }

    public ScoreDto(Integer id, String username, String realName, Integer score, String remark, String submitter, Long createTime) {
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

    public String getScoreStr() {
        return scoreStr;
    }

    public void setScoreStr(String scoreStr) {
        this.scoreStr = scoreStr;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ScoreDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", score=" + score +
                ", scoreStr='" + scoreStr + '\'' +
                ", remark='" + remark + '\'' +
                ", submitter='" + submitter + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
