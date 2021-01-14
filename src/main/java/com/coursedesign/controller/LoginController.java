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
    @Autowired
    LoginService loginService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    MemberService memberService;

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = {"/login","/"})
    public String login(){
        return "login";
    }

    /**
     * 正常登录
     * @param username
     * @param password
     * @param model
     * @return
     */
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
                return "redirect:/index";
            }else {
                // 登录失败
                model.addAttribute("msg","注册有误");
                return "login";
            }
        }
    }
}
