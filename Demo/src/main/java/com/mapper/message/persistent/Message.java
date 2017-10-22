package com.mapper.message.persistent;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
    private Integer id;

    private String title;

    private String message;

    private Integer userid;

    private String ip;

    private Date ceratetime;

    private Integer status;

    private Integer parentid;
    
    private String userName;
    
}