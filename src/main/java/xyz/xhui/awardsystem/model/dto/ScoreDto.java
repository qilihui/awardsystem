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
    private Integer week;
    private Integer apartmentId;
    private Integer room;
    private Integer bed;

    public ScoreDto() {
    }

    public ScoreDto(Integer id, String username, String realName, String scoreStr, String remark, String submitter, Long createTime, Integer week) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.scoreStr = scoreStr;
        this.remark = remark;
        this.submitter = submitter;
        this.createTime = createTime;
        this.week = week;
    }

    public ScoreDto(Integer id, String scoreStr, String remark, Long createTime, Integer week, Integer room, Integer bed) {
        this.id = id;
        this.scoreStr = scoreStr;
        this.remark = remark;
        this.createTime = createTime;
        this.week = week;
        this.room = room;
        this.bed = bed;
    }

    public ScoreDto(Integer id, String username, String realName, String scoreStr, String remark, Long createTime, Integer week) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.scoreStr = scoreStr;
        this.remark = remark;
        this.createTime = createTime;
        this.week = week;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
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
                ", week=" + week +
                ", apartmentId=" + apartmentId +
                ", room=" + room +
                ", bed=" + bed +
                '}';
    }
}
