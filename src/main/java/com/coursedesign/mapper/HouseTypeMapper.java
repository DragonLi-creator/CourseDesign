package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.HouseType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseTypeMapper extends BaseMapper<HouseTypeMapper> {
    @Select("select * from house_type")
    List<HouseType> getAllHouseType();
}
