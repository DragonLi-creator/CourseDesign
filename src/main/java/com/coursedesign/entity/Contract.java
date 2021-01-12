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
public class Contract {
    @TableId(type = IdType.AUTO)
    private int id;
    private String hostName;
    private String hostCard;
    private String rentName;
    private String rentCard;
    private int houseId;
    private String content;
    private Timestamp startTime;
    private Timestamp endTime;
}
