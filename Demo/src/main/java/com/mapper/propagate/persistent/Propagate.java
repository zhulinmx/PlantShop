package com.mapper.propagate.persistent;

import java.util.Date;

public class Propagate {
    private Integer propagateid;

    private String title;

    private String content;

    private Integer userid;

    private Date ceratetime;

    private Date deadlinetime;

    private Integer status;

    public Integer getPropagateid() {
        return propagateid;
    }

    public void setPropagateid(Integer propagateid) {
        this.propagateid = propagateid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCeratetime() {
        return ceratetime;
    }

    public void setCeratetime(Date ceratetime) {
        this.ceratetime = ceratetime;
    }

    public Date getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(Date deadlinetime) {
        this.deadlinetime = deadlinetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}