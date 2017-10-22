package com.mapper.plant.persistent;

import java.util.Date;

public class Plant {
    private Integer plantid;

    private Integer categoryid;

    private String plantname;

    private Date ceratetime;

    private Integer status;

    private String descript;

    public Integer getPlantid() {
        return plantid;
    }

    public void setPlantid(Integer plantid) {
        this.plantid = plantid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname == null ? null : plantname.trim();
    }

    public Date getCeratetime() {
        return ceratetime;
    }

    public void setCeratetime(Date ceratetime) {
        this.ceratetime = ceratetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }
}