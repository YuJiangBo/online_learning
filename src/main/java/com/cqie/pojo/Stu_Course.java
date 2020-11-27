package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu_Course {

    private Integer Id;
    private Integer Sid;
    private String Sname;
    private Integer Cid;
    private String Cname;
}
