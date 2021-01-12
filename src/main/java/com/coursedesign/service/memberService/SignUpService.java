package com.coursedesign.service.memberService;

import com.coursedesign.entity.Notice;
import com.coursedesign.mapper.MemberMapper;
import com.coursedesign.service.noticeService.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Value("${sex}")
    private int sex;
    @Value("${age}")
    private int age;
    @Value("${tel}")
    private String tel;
    @Value("${address}")
    private String address;
    @Value("${mail}")
    private String mail;
    @Value("${card}")
    private String card;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    NoticeService noticeService;

    // 注册时只填写用户名和密码，其他默认
    public boolean signUp(String userName,String password){
        return memberMapper.insertMember(userName,sex,password,age,tel,address,
                mail,card);
    }

    // 调用此方法时，新用户已入数据库，返回当前用户ID
    public int getIdByName(String name) {
        return memberMapper.getIdByName(name);
    }

    public Notice getNotice(){
        noticeService.updateMemberCount();
        return noticeService.getNotice();
    }
}
