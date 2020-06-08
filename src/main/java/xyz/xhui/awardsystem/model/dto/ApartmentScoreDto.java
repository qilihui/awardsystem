package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class ApartmentScoreDto implements Serializable {
    private String room;

    private String s1;

    private String s2;

    private String s3;

    private String s4;

    private String s5;

    private String s6;

    private String remark;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getS(Integer id) {
        switch (id) {
            case 1:
                return s1;
            case 2:
                return s2;
            case 3:
                return s3;
            case 4:
                return s4;
            case 5:
                return s5;
            case 6:
                return s6;
            default:
                return null;
        }
    }

    public void setS(Integer id, String value) {
        switch (id) {
            case 1:
                s1 = value;
                break;
            case 2:
                s2 = value;
                break;
            case 3:
                s3 = value;
                break;
            case 4:
                s4 = value;
                break;
            case 5:
                s5 = value;
                break;
            case 6:
                s6 = value;
                break;
        }
    }

    @Override
    public String toString() {
        return "ApartmentScoreDto{" +
                "room='" + room + '\'' +
                ", s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", s5='" + s5 + '\'' +
                ", s6='" + s6 + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
