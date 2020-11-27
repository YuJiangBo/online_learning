package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private Integer tid;
    private String tname;
    private String tel;
    private String email;
    private Integer sex;
    private String password;
    private String tcode;
    private Integer collegeId;

}
