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
public class Rent {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private  String addressName;
    private String houseResource;
    private  Integer price;
    private String houseType;
    private String content;
    private  String people;
    private Timestamp releaseTime;
}
