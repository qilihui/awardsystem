package xyz.xhui.awardsystem.model.dto;

import java.io.Serializable;

public class NoticeDto implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String submitter;
    private Long createTime;
    private String type;

    public NoticeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoticeDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", submitter='" + submitter + '\'' +
                ", createTime=" + createTime +
                ", type='" + type + '\'' +
                '}';
    }
}
