package xyz.xhui.awardsystem.model.vo;

import java.io.Serializable;

public class ExtraScoreVo implements Serializable {
    private Integer id;
    private String username;
    private String realName;
    private Double score;
    private String remark;
    private Integer status;
    private Long createTime;
    private String path;

    public ExtraScoreVo() {
    }

    public ExtraScoreVo(Integer id, String username, String realName, Double score, String remark, Integer status, Long createTime, String path) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.score = score;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
        this.path = path;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ExtraScoreVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", path='" + path + '\'' +
                '}';
    }
}
