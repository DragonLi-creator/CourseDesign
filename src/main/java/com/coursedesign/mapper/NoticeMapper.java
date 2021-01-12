package com.coursedesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coursedesign.entity.Notice;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
    @Update("update notice set house_count = #{house_count},set rent_count = #{rent_count}," +
            "set member_count = #{member_count},set contract_count = #{contract_count} where id = 1")
    boolean updateNotice(int house_count,int rent_count,int member_count,int contract_count);

    @Update("update notice set rent_count = rent_count + 1 where id = 1")
    boolean updateRentCount();

    @Update("update notice set member_count = member_count + 1 where id = 1")
    boolean updateMemberCount();

    @Update("update notice set house_count = house_count + 1 where id = 1")
    boolean updateHouseCount();

    @Update("update notice set contract_count = contract_count + 1 where id = 1")
    boolean updateContractCount();

    @Select("select * from notice where id = 1")
    Notice getNotice();
}
