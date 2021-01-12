package com.coursedesign.controller;

import com.coursedesign.entity.Address;
import com.coursedesign.entity.Member;
import com.coursedesign.service.houseService.AddressService;
import com.coursedesign.service.memberService.MemberService;
import com.coursedesign.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    AddressService addressService;

    @Autowired
    MemberService memberService;


    @RequestMapping(value = {"/index","/index.html"})
    public String index(Model model) {
        model.addAttribute("currentUsername", CurrentUser.username);
        return "index";
    }

    /**
     * @return 去往完善个人信息页面
     */
    @RequestMapping("/information.html")
    public String information(Model model) {
        List<Address> allAddress = addressService.getAllAddress();
        model.addAttribute("allAddress",allAddress);
        Member member;
        if (CurrentUser.username.isEmpty()){
            member = new Member();
        }else {
            member = memberService.getMemberByName(CurrentUser.username);
//            System.out.println(CurrentUser.username);
            String sex = member.getSex()==1?"男":"女";

            model.addAttribute("sex",sex);
        }
        model.addAttribute("member",member);
        model.addAttribute("currentUsername", CurrentUser.username);
        return "information";

    }

    /**
     * @return 去往修改密码页面
     */
    @RequestMapping("/password.html")
    public String password(Model model) {
        model.addAttribute("currentUsername", CurrentUser.username);
        return "password";
    }
    /**
     * @return 退出登录
     */
    @RequestMapping("/login.html")
    public String signOut(){
        return "redirect:/";
    }

    /**从导航栏接收的请求重定向到响应controller，以获得参数*/
    @RequestMapping("/releaseHousePage.html")
    public String releaseHousePage(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "redirect:/releaseHousePage";
    }

    /**
     * @return 查看房源
     */
    @RequestMapping("/house.html")
    public String house(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "house";
    }

    /**
     * @return 查看求租信息
     */
    @RequestMapping("/rent.html")
    public String rent(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "rent";
    }

    @RequestMapping("/releaseRentPage.html")
    public String releaseRentPage(){
        return "redirect:/releaseRentPage";
    }

    @RequestMapping("/contract.html")
    public String contract(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "contract";
    }

    @RequestMapping("/releaseContractPage.html")
    public String releaseContractPage(){
        return "redirect:/releaseContractPage";
    }


    @RequestMapping("/404")
    public String str404(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "404";
    }

}
