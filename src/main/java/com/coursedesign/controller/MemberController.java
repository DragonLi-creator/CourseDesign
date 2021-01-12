package com.coursedesign.controller;

import com.coursedesign.service.memberService.MemberService;
import com.coursedesign.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    // 修改个人密码
    @RequestMapping("/updateMemberPassword")
    public String updateMemberPassword(@RequestParam("oldPassword")String oldPassword,
                                       @RequestParam("password1")String password1,
                                       @RequestParam("password2")String password2,Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        //首先比较旧的密码和两次新密码是否相同
        if (oldPassword.equals(CurrentUser.password)&&password1.equals(password2)){
            memberService.updateMemberPassword(password1,CurrentUser.username);
        }else {
            model.addAttribute("msg","密码错误，或两次新密码不同");
            return "password";
        }
        return "index";
    }

    // 修改个人信息
    @RequestMapping("/updateMember")
    public String updateMember(@RequestParam("name")String name,
                               @RequestParam("sex")String sex,
                               @RequestParam("tel")String tel,
                               @RequestParam("age")int age,
                               @RequestParam("mail")String mail,
                               @RequestParam("address")String address,
                               @RequestParam("card")String card, Model model){
        String currentUsername = CurrentUser.username;
        if (currentUsername.isEmpty()){
            model.addAttribute("msg","当前无用户登录");
            return "redirect:/information.html";
        }else {
            int userSex = sex.equals("男")?1:0;
            boolean flag = memberService.updateMember(name,userSex,age,tel,address,mail,card,currentUsername);
            if (flag) {
                return "redirect:/index";
            }else {
                return "404";
            }
        }


    }

}
