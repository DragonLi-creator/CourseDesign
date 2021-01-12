package com.coursedesign.service.houseService;

import com.coursedesign.entity.Address;
import com.coursedesign.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;
    public List<Address> getAllAddress(){
        return addressMapper.getALLAddress();
    }
}
