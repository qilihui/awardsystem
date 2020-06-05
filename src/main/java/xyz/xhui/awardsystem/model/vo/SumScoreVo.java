package xyz.xhui.awardsystem.model.vo;

import java.io.Serializable;

public class SumScoreVo implements Serializable {

    /**
     * termId : 6
     * examRate : 80
     * lessNo : on
     * awardMethod : rate
     * one : 2
     * two : 5
     * three : 10
     */

    private Integer termId;
    private Integer examRate;
    private Integer moralMaxScore;
    private String lessNo;
    private String awardMethod;
    private Integer one;
    private Integer two;
    private Integer three;

    public SumScoreVo() {
    }

    public SumScoreVo(Integer termId, Integer examRate, Integer moralMaxScore, String lessNo, String awardMethod, Integer one, Integer two, Integer three) {
        this.termId = termId;
        this.examRate = examRate;
        this.moralMaxScore = moralMaxScore;
        this.lessNo = lessNo;
        this.awardMethod = awardMethod;
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getExamRate() {
        return examRate;
    }

    public void setExamRate(Integer examRate) {
        this.examRate = examRate;
    }

    public Integer getMoralMaxScore() {
        return moralMaxScore;
    }

    public void setMoralMaxScore(Integer moralMaxScore) {
        this.moralMaxScore = moralMaxScore;
    }

    public String getLessNo() {
        return lessNo;
    }

    public void setLessNo(String lessNo) {
        this.lessNo = lessNo;
    }

    public String getAwardMethod() {
        return awardMethod;
    }

    public void setAwardMethod(String awardMethod) {
        this.awardMethod = awardMethod;
    }

    public Integer getOne() {
        return one;
    }

    public void setOne(Integer one) {
        this.one = one;
    }

    public Integer getTwo() {
        return two;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public Integer getThree() {
        return three;
    }

    public void setThree(Integer three) {
        this.three = three;
    }

    @Override
    public String toString() {
        return "SumScoreVo{" +
                "termId=" + termId +
                ", examRate=" + examRate +
                ", moralMaxScore=" + moralMaxScore +
                ", lessNo='" + lessNo + '\'' +
                ", awardMethod='" + awardMethod + '\'' +
                ", one=" + one +
                ", two=" + two +
                ", three=" + three +
                '}';
    }
}
