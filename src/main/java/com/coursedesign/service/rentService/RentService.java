package com.coursedesign.service.rentService;

import com.coursedesign.entity.Rent;
import com.coursedesign.mapper.RentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RentService {
    @Autowired
    RentMapper rentMapper;
    public boolean insertRent(String addressName, String houseResource, int price, String houseType, String content, String people,
                              Timestamp releaseTime){
        return rentMapper.insertRent(addressName,houseResource,price, houseType, content,  people,
                releaseTime);

    }
    public boolean deleteRent(int id){
        return rentMapper.deleteRent(id);
    }

    public List<Rent> getRentList(){
        return rentMapper.getRentList();
    }

    public Rent getRentByPeople(String people){
        return rentMapper.getRentByPeople(people);
    }

    public boolean updateRent(String address_name,String house_resource, int price,String house_type,String content,
                              String people,Timestamp timestamp,int id){
        return rentMapper.updateRent(address_name,house_resource,price,house_type,content,
                people,timestamp,id);
    }

    public Rent getRentById(int id){
        return rentMapper.getRentById(id);
    }

    public int getRentCount(){
        return rentMapper.getRentCount();
    }

    public String getPeopleById(int id){
        return rentMapper.getPeopleById(id);
    }
}
