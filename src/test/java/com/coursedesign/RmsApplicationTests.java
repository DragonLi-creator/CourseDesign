package com.coursedesign;

import com.alibaba.fastjson.JSON;
import com.coursedesign.entity.House;
import com.coursedesign.mapper.HouseMapper;
import com.coursedesign.service.houseService.HouseService;
import com.coursedesign.utils.FileNameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class RmsApplicationTests {

    @Autowired
    HouseMapper houseMapper;
    @Test
    void contextLoads() {
//        House house = houseMapper.getHouseById(1);
//        House house = houseMapper.getHouseByAddressName("尖草坪区");
//        System.out.println(house);
//        // 对象转成json格式
//        String houseJson = JSON.toJSONString(house);
//        System.out.println(houseJson);
//        // json格式转成字符串
//        House jsonHouse = JSON.parseObject(houseJson, House.class);
//        System.out.println(jsonHouse);
    }

    @Test
    void test1(){
        String realPath = FileNameUtils.getFileName("1.jpg");
        System.out.println(realPath);
    }
    @Value("${image}")
    private String str;
    @Test
    void test2(){
        System.out.println(str);
    }

    @Autowired
    HouseService houseService;

}
