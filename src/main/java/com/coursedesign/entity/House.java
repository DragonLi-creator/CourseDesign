package com.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @TableId(type = IdType.AUTO)
    private int id;
    private String address;
    private String addressName;
    private String hostName;
    private String houseResource;
    private String houseType;
    private int price;
    private String payType;
    private String tel;
    private int area;
    private String content;
    private Timestamp releaseTime;
}
