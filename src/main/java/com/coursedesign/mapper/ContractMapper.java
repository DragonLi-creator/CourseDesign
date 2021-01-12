package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.Contract;
import com.coursedesign.entity.House;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ContractMapper extends BaseMapper<Contract> {
    @Select("select * from contract;")
    List<Contract> getAllContract();

    @Insert("insert into contract (host_name,host_card,rent_name,rent_card,house_id,content,start_time,end_time)" +
            "values(#{host_name},#{host_card},#{rent_name},#{rent_card},#{house_id},#{content},#{start_time},#{end_time});")
    boolean insertContract(String host_name, String host_card, String rent_name, String rent_card, int house_id, String content, Timestamp start_time, Timestamp end_time);

    @Delete("delete from contract where id = #{id};")
    boolean deleteContract(int id);

    @Select("select * from contract where id = #{id}")
    Contract getContractById(int id);

    @Select("select count(*) from contract")
    int getContractCount();

    // 分页查询
    @Select("select * from contract limit #{limit1},#{limit2};")
    List<House> getContractPages(int limit1, int limit2);
}
