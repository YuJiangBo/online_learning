package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String code;
    private String name;
    private String password;
    private String perms;  //0:学生  1：教师  2：管理员


}
