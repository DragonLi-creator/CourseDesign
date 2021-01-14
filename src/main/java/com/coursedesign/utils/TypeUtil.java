package com.coursedesign.utils;

import com.coursedesign.entity.Address;
import com.coursedesign.entity.HouseResourceType;
import com.coursedesign.entity.HouseType;
import com.coursedesign.service.houseService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUtil {
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    HouseResourceService houseResourceService;
    @Autowired
    AddressService addressService;

    public String[] getType(String param){
        List<HouseType> houseTypeList = houseTypeService.getAllHouseType();
        List<HouseResourceType> houseResourceTypeList = houseResourceService.getAllHouseResourceType();
        List<Address> addressList = addressService.getAllAddress();
        String[] res = new String[2];
        for (HouseType houseType: houseTypeList) {
            if (houseType.getHouseType().equals(param)) {
                res[0] = "houseType";
                res[1] = "param";
                return res;
            }
        }
        for (HouseResourceType houseResourceType: houseResourceTypeList) {
            if (houseResourceType.getResourceType().equals(param)) {
                res[0] = "houseResourceType";
                res[1] = "param";
                return res;
            }
        }
        for (Address address: addressList) {
            if (address.getName().equals(param)) {
                res[0] = "address";
                res[1] = "param";
                return res;
            }
        }
        res[0] = "default";
        return res;
    }
}
