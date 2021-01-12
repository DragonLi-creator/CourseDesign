package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.Address;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper extends BaseMapper<Address> {
    @Select("select * from address")
    List<Address> getALLAddress();
}
