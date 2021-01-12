package com.coursedesign.service.houseService;

import com.coursedesign.entity.HouseResourceType;
import com.coursedesign.mapper.HouseResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseResourceService {
    @Autowired
    HouseResourceMapper houseResourceMapper;

    public List<HouseResourceType> getAllHouseResourceType(){
        return houseResourceMapper.getAllHouseResourceType();
    }
}
