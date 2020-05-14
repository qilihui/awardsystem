package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class UnionScoreByTutorDto implements Serializable {
    private Integer id;
    private Integer score;
    private Integer unionId;
    private String remark;
    private Long createTime;
    private String username;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getUnionId() {
        return unionId;
    }

    public void setUnionId(Integer unionId) {
        this.unionId = unionId;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "UnionScoreByTutorDto{" +
                "id=" + id +
                ", score=" + score +
                ", unionId=" + unionId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
