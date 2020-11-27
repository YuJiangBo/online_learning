package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer sid;
    private String sname;
    private String tel;
    private String email;
    private Integer sex;
    private String password;
    private String scode;
    private Integer collegeId;
    private Integer classId;

}
