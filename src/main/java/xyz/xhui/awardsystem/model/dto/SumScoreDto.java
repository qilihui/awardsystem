package xyz.xhui.awardsystem.model.dto;

import xyz.xhui.awardsystem.model.entity.ExamScore;

import java.io.Serializable;

public class SumScoreDto implements Serializable {
    private ExamScore examScore;
    private String username;
    private String realName;
    private Double apartmentScore;
    private Double moralScore;
    private Double sumScore;
    private Integer rank;
    private String awardRank;

    public ExamScore getExamScore() {
        return examScore;
    }

    public void setExamScore(ExamScore examScore) {
        this.examScore = examScore;
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

    public Double getApartmentScore() {
        return apartmentScore;
    }

    public void setApartmentScore(Double apartmentScore) {
        this.apartmentScore = apartmentScore;
    }

    public Double getMoralScore() {
        return moralScore;
    }

    public void setMoralScore(Double moralScore) {
        this.moralScore = moralScore;
    }

    public Double getSumScore() {
        return sumScore;
    }

    public void setSumScore(Double sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAwardRank() {
        return awardRank;
    }

    public void setAwardRank(String awardRank) {
        this.awardRank = awardRank;
    }

    @Override
    public String toString() {
        return "SumScoreDto{" +
                "examScore=" + examScore +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", apartmentScore=" + apartmentScore +
                ", moralScore=" + moralScore +
                ", sumScore=" + sumScore +
                ", rank=" + rank +
                ", awardRank='" + awardRank + '\'' +
                '}';
    }
}
