package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper extends BaseMapper<Member> {
    @Select("select password from member where name = #{name};")
    String getPasswordByName(String name);

    @Insert("insert into " +
            "member(name,sex,password,age,tel,address,mail,card) " +
            "values (#{name},#{sex},#{password},#{age},#{tel},#{address},#{mail},#{card});")
    boolean insertMember(String name,int sex,String password,int age,
                     String tel,String address,String mail,String card);

    @Update("update member set name = #{name},sex = #{sex},age = #{age},tel = #{tel},address = #{address}" +
            ",mail = #{mail},card = #{card} where name = #{username};")
    boolean updateMember(String name, int sex,int age, String tel,
                         String address,String mail,String card,String username);

    @Select("select id from member where name = #{name};")
    int getIdByName(String name);

    @Update("update member set password = #{password} where name = #{name};")
    boolean updateMemberPassword(String password,String name);

    @Select("select count(*) from member")
    int getMemberCount();

    @Select("select * from member where name = #{name}")
    Member getMemberByName(String name);

}
