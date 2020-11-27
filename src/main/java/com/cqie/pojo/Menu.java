package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Integer id;
    private String item;
    private String addr;
    private Integer level;
    private Integer belong;

}
