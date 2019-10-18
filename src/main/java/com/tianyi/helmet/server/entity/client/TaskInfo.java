package com.tianyi.helmet.server.entity.client;

/**
 * Created by tianxujin on 2019/6/12 14:16
 */
public class TaskInfo implements java.io.Serializable{
    private String content;
    private String guide;
    private String hint;
    private String recordType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
