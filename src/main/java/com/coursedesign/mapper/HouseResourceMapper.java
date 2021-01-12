package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.HouseResourceType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseResourceMapper extends BaseMapper<HouseResourceType> {
    @Select("select * from house_resource_type;")
    List<HouseResourceType> getAllHouseResourceType();
}
