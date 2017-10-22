package com.mapper.user.persistent;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Integer userid;

    private String loginname;

    private String password;

    private Integer role;

    private String username;

    private String dep;

    private Date jointime;

    private String tellphone;

    private String email;

    private Date birthday;

    private String remark;

    private Integer status;

}