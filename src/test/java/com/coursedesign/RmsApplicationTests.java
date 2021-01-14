package com.coursedesign;

import com.coursedesign.mapper.HouseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RmsApplicationTests {

    @Autowired
    HouseMapper houseMapper;
    @Test
    void contextLoads() {
    }


}
