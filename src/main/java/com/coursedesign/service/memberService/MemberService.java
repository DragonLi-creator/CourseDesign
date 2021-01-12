package com.coursedesign.service.memberService;

import com.coursedesign.entity.Member;
import com.coursedesign.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    public boolean updateMemberPassword(String password,String name){
        return memberMapper.updateMemberPassword(password,name);
    }

    public boolean updateMember(String name, int sex,int age, String tel,
                                String address,String mail,String card,String username){
        return memberMapper.updateMember(name, sex,age, tel,address,mail,card,username);
    }

    public int getMemberCount(){
        return memberMapper.getMemberCount();
    }

    public String getPasswordByName(String name){
        return memberMapper.getPasswordByName(name);
    }

    public Member getMemberByName(String name){
        return memberMapper.getMemberByName(name);
    }
}
