package com.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlantInfo {
	
 	private Integer plantid;

    private Integer categoryid;

    private String plantname;

    private Date ceratetime;

    private Integer status;

    private String descript;
    
    private Integer picId;

    private String picture;
    
    private String categoryName;

}
