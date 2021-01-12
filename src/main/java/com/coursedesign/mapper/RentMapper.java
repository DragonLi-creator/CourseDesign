package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.House;
import com.coursedesign.entity.Rent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentMapper extends BaseMapper<Rent> {
    @Select("select * from rent where id = #{id};")
    Rent getRentById(int id);

    @Insert("insert into rent(address_name,house_resource,price,house_type,content,people,release_time)"+
            "values(#{addressName},#{houseResource},#{price},#{houseType},#{content},#{people},#{releaseTime});")
    boolean insertRent(String addressName, String houseResource, int price, String houseType, String content, String people,
                       Timestamp releaseTime);

    @Delete("delete from rent where id=#{id};")
    boolean deleteRent(int id);

    @Select("select * from rent;")
    List <Rent> getRentList();

    @Select("select * from rent where people=#{people};")
    Rent getRentByPeople(String people);

    @Update("update rent set address_name = #{address_name},house_resource = #{house_resource},price = #{price}," +
            "house_type = #{house_type},content = #{content},people = #{people},release_time = #{release_time}" +
            "where id = #{id};")
    boolean updateRent(String address_name,String house_resource, int price,String house_type,String content,
                       String people,Timestamp release_time,int id);

    @Select("select count(*) from rent")
    int getRentCount();

    @Select("select people from rent where id = #{id}")
    String getPeopleById(int id);
    // 分页查询
    @Select("select * from rent limit #{limit1},#{limit2};")
    List<House> getRentPages(int limit1, int limit2);
}
