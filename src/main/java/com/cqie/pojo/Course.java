package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Integer id;
    private String cname;
    private String tcode;
    private String tname;
    private int state;  //0:关闭   1：开启
    private String img;
    private String video;
}
