package com.coursedesign.controller;

import com.coursedesign.service.memberService.LoginService;
import com.coursedesign.service.memberService.MemberService;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // 登录
    @RequestMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }

//    @RequestMapping("/rent")
//    public String rent(){
//        return "rental";
//    }

    @RequestMapping("/inde")
    public String index(){
        return "index";
    }
    @Autowired
    LoginService loginService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    MemberService memberService;
    @RequestMapping(value = "/loginMember",params = "action=login")
    public String loginMember(@RequestParam("username") String username, @RequestParam("password") String password,
                              Model model) {
        String pwd = memberService.getPasswordByName(username);
        if (pwd.isEmpty()){
            model.addAttribute("msg","已有当前用户名");
            return "login";
        }else {
            // 判断是否登陆成功
            boolean flag = loginService.login(username, password);
            if (flag) {
                //记录当前用户的姓名和ID
                CurrentUser.username = username;
                CurrentUser.id = loginService.getIdByName(username);
                CurrentUser.password = password;
                //去往首页时需初始化数据
                // 获取notice信息
//            Notice notice = loginService.getNotice();
//            model.addAttribute("notice",notice);
                return "redirect:/index";
            }else {
                // 登录失败
                model.addAttribute("msg","注册有误");
                return "login";
            }
        }


    }

    @RequestMapping("/loginAdmin")
    public String loginAdmin(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean flag = loginService.loginAdmin(username, password);
        if (flag) {
            return "test";
        }else {
            return "login";
        }
    }
//
//    // 退出登录
//    @RequestMapping("/signOut")
//    public String signOut(){
//        return "";
//    }
}
