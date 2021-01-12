package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.PayType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayTypeMapper extends BaseMapper<PayType> {
    @Select("select * from pay_type")
    List<PayType> getAllPayType();
}
