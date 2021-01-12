package com.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private int sex;
    private String password;
    private int age;
    private String tel;
    private String address;
    private String mail;
    private String card;
}
