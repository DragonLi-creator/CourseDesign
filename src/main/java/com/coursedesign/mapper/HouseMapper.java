package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.House;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HouseMapper extends BaseMapper<House> {
    @Select("select * from house where id = #{id};")
    House getHouseById(int id);


    @Insert("insert into house (address,tel,address_name,host_name,house_resource,house_type,price,pay_type,area,content,release_time)" +
            "values(#{address},#{tel},#{address_name},#{host_name},#{house_resource},#{house_type}" +
            ",#{price},#{pay_type},#{area},#{content},#{release_time});")
    boolean insertHouse(String address,String tel,String address_name,String host_name,
                        String house_resource,String house_type,int price,String pay_type,
                        int area,String content,Timestamp release_time);

    @Select("select * from house where address_name = #{address_name};")
    List<House> getHouseByAddressName(String address_name);

    @Select("select * from house where house_resource = #{house_resource};")
    List<House> getHouseByHouseResource(String house_resource);

    @Select("select * from house where house_type = #{house_type};")
    List<House> getHouseByHouseType(String house_type);

    @Select("select * from house;")
    List<House> getAllHouse();

    @Update("update house set address = #{address}, tel = #{tel},address_name = #{address_name}," +
            "host_name = #{host_name},house_resource = #{house_resource},house_type = #{house_type}," +
            "price = #{price},pay_type = #{pay_type},area = #{area},content = #{content}," +
            "release_time = #{release_time} where id = #{id};")
    boolean updateHouse(String address,String tel,String address_name,String host_name,String house_resource,String house_type,
                        int price,String pay_type,int area,String content,Timestamp release_time,int id);

    @Delete("delete from house where id = #{id};")
    boolean deleteHouse(int id);
    @Select("select count(*) from house;")
    int getHouseCount();

    @Select("select host_name from house where id = #{id}")
    String getHostNameById(int id);

    // 分页查询
    @Select("select * from house limit #{limit1},#{limit2};")
    List<House> getHousePages(int limit1,int limit2);
}
