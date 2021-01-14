package com.coursedesign.controller;

import com.coursedesign.entity.Address;
import com.coursedesign.entity.Member;
import com.coursedesign.entity.Notice;
import com.coursedesign.service.contractService.ContractService;
import com.coursedesign.service.houseService.AddressService;
import com.coursedesign.service.houseService.HouseService;
import com.coursedesign.service.memberService.MemberService;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.service.rentService.RentService;
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
    @Autowired
    NoticeService noticeService;
    @Autowired
    HouseService houseService;
    @Autowired
    RentService rentService;
    @Autowired
    ContractService contractService;


    /**
     * 去往首页 并携带一些值
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index","/index.html"})
    public String index(Model model) {
        // 得到当前数据库中的房源数、求租数，合同数以及用户数,并更新到notice表
        int houseCount = houseService.getHouseCount();
        int rentCount = rentService.getRentCount();
        int contractCount = contractService.getContractCount();
        int memberCount = memberService.getMemberCount();
        noticeService.updateNotice(houseCount,rentCount,memberCount,contractCount);
        Notice notice = noticeService.getNotice();
        model.addAttribute("notice",notice);
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

    /**
     * 跳往发布求租信息的页面
     * @return
     */
    @RequestMapping("/releaseRentPage.html")
    public String releaseRentPage(){
        return "redirect:/releaseRentPage";
    }

    /**
     * 跳往查看合同的页面
     * @param model
     * @return
     */
    @RequestMapping("/contract.html")
    public String contract(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "contract";
    }

    /**
     * 跳往签订合同的页面
     * @return
     */
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
