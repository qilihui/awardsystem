package xyz.xhui.awardsystem.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UnionScoreDto implements Serializable {
    @NotNull(message = "username不能为空")
    private String username;

    @NotNull(message = "score不能为空")
    private Integer score;

    @NotNull(message = "remark不能为空")
    private String remark;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UnionScoreDto{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                '}';
    }
}
