package com.coursedesign.controller;

import com.coursedesign.entity.Notice;
import com.coursedesign.service.memberService.SignUpService;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class SignUpController {

    @Autowired
    SignUpService signUpService;
    @Autowired
    NoticeService noticeService;

    /**
     * 注册请求
     */
    @RequestMapping(value = "/loginMember",params = "action=signUp")
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password,
                         Model model){
        boolean flag = signUpService.signUp(username, password);
        if (flag) {
            CurrentUser.username = username;
            CurrentUser.id = signUpService.getIdByName(username);
            CurrentUser.password = password;
            // 增加新用户的时候，notice更新
//            Notice notice = noticeService.getNotice();
//            model.addAttribute("notice",notice);
            return "index";
        }else {
            model.addAttribute("msg","注册有误");
            return "login";
        }
    }

}
