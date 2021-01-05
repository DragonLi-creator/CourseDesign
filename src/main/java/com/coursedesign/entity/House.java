package com.coursedesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    private int id;
    private String address;
    private String addressName;
    private String hostName;
    private String houseResource;
    private String houseType;
    private int price;
    private String payType;
    private int floor;
    private int area;
    private int renovation;
    private String image;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
}
