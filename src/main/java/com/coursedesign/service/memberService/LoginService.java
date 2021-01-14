package com.coursedesign.service.memberService;

import com.coursedesign.entity.Notice;
import com.coursedesign.mapper.MemberMapper;
import com.coursedesign.service.contractService.ContractService;
import com.coursedesign.service.houseService.HouseService;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.service.rentService.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    MemberMapper memberMapper;
    public boolean login(String userName,String userPassword){
        String password = memberMapper.getPasswordByName(userName);
        // 如果密码为空
        if (password == null) {
            return false;
        }
        return password.equals(userPassword);
    }

    public int getIdByName(String userName) {
        return memberMapper.getIdByName(userName);
    }

    // 从各个表中获取记录数存入notice中

    @Autowired
    NoticeService noticeService;
    @Autowired
    HouseService houseService;
    @Autowired
    RentService rentService;
    @Autowired
    MemberService memberService;
    @Autowired
    ContractService contractService;
    public Notice getNotice(){
        int houseCount = houseService.getHouseCount();
        int rentCount = rentService.getRentCount();
        int memberCount = memberService.getMemberCount();
        int contractCount = contractService.getContractCount();
        noticeService.updateNotice(houseCount,rentCount,memberCount,contractCount);
        return noticeService.getNotice();
    }
}
