package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class ApartmentScoreDto implements Serializable {
    private String room;

    private String bed;

    private String score;

    private String remark;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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
        return "ApartmentScoreDto{" +
                "room='" + room + '\'' +
                ", bed='" + bed + '\'' +
                ", score='" + score + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
