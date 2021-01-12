package com.coursedesign.service.houseService;

import com.coursedesign.entity.PayType;
import com.coursedesign.mapper.PayTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayTypeService {
    @Autowired
    PayTypeMapper payTypeMapper;

    public List<PayType> getAllPayType(){
        return payTypeMapper.getAllPayType();
    }
}
