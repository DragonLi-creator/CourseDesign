package com.coursedesign.service.houseService;

import com.coursedesign.entity.House;
import com.coursedesign.mapper.HouseMapper;
import com.coursedesign.service.noticeService.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class HouseService {
    @Autowired
    HouseMapper houseMapper;

    public House getHouseById(int id){
        return houseMapper.getHouseById(id);
    }

    public boolean insertHouse(String address, String tel, String address_name, String host_name,
                        String house_resource, String house_type, int price, String pay_type,
                        int area,String content, Timestamp release_time) {
        return houseMapper.insertHouse(address, tel, address_name, host_name,
                house_resource, house_type, price, pay_type, area, content, release_time);
    }

    public boolean insertHouse(House house){
        return insertHouse(house.getAddress(),house.getTel(),house.getAddressName(),house.getHostName(),
                house.getHouseResource(),house.getHouseType(),house.getPrice(),house.getPayType(),
                house.getArea(),house.getContent(),house.getReleaseTime());
    }

    public List<House> getHouseByAddressName(String addressName){
        return houseMapper.getHouseByAddressName(addressName);
    }

    public List<House> getHouseByHouseResource(String houseResource){
        return houseMapper.getHouseByHouseResource(houseResource);
    }

    public List<House> getHouseByHouseType(String houseType){
        return houseMapper.getHouseByHouseType(houseType);
    }

    public List<House> getAllHouse(){
        return houseMapper.getAllHouse();
    }

    public boolean updateHouse(String address,String tel,String address_name,String host_name,String house_resource,String house_type,
                               int price,String pay_type,int area,String content,Timestamp timestamp,int id){
        return houseMapper.updateHouse(address, tel, address_name,host_name, house_resource, house_type,price,
                pay_type,area,content,timestamp,id);
    }

    public boolean deleteHouse(int id){
        return houseMapper.deleteHouse(id);
    }

    public int getHouseCount(){
        return houseMapper.getHouseCount();
    }

    public String getHostNameById(int id){
        return houseMapper.getHostNameById(id);
    }

}
