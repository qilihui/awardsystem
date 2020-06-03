package xyz.xhui.awardsystem.model.vo;

import java.io.Serializable;
import java.util.List;

public class ExamScoreVo implements Serializable {

    /**
     * data : [{"username":"学号","scoreStr":"加权平均分","count":"不及格科目数","remark":"备注"},{"username":1730000001,"scoreStr":80.15,"count":0,"remark":""},{"username":1730000002,"scoreStr":80.15,"count":1,"remark":""}]
     * termId : 1
     */

    private int termId;
    private List<DataBean> data;

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : 学号
         * scoreStr : 加权平均分
         * count : 不及格科目数
         * remark : 备注
         */

        private String username;
        private String scoreStr;
        private String count;
        private String remark;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getScoreStr() {
            return scoreStr;
        }

        public void setScoreStr(String scoreStr) {
            this.scoreStr = scoreStr;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "username='" + username + '\'' +
                    ", scoreStr='" + scoreStr + '\'' +
                    ", count='" + count + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExamScoreVo{" +
                "termId=" + termId +
                ", data=" + data +
                '}';
    }
}
