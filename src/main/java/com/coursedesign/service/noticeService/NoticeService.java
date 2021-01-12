package com.coursedesign.service.noticeService;

import com.coursedesign.entity.Notice;
import com.coursedesign.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    public boolean updateNotice(int house_count, int rent_count, int member_count, int contract_count) {
        return noticeMapper.updateNotice(house_count, rent_count, member_count, contract_count);
    }

    public boolean updateHouseCount(){
        return noticeMapper.updateHouseCount();
    }

    public boolean updateRentCount(){
        return noticeMapper.updateRentCount();
    }

    public boolean updateMemberCount(){
        return noticeMapper.updateMemberCount();
    }

    public boolean updateContractCount(){
        return noticeMapper.updateContractCount();
    }

    public Notice getNotice(){
        return noticeMapper.getNotice();
    }
}
