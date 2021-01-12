package com.coursedesign.service.houseService;

import com.coursedesign.entity.HouseType;
import com.coursedesign.mapper.HouseTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseTypeService {
    @Autowired
    HouseTypeMapper houseTypeMapper;

    public List<HouseType> getAllHouseType(){
        return houseTypeMapper.getAllHouseType();
    }
}
