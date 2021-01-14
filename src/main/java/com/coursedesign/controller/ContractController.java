package com.coursedesign.controller;

import com.coursedesign.entity.Contract;
import com.coursedesign.service.contractService.ContractService;
import com.coursedesign.service.memberService.MemberService;
import com.coursedesign.utils.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ContractController {
    @Autowired
    ContractService contractService;

    @Autowired
    MemberService memberService;

    /**
     * 返回contract实体类的json格式
     * @return
     */
    @RequestMapping("/jsonContract")
    @ResponseBody
    public String jsonContract(){
        List<Contract> list = contractService.getAllContract();
        int count = contractService.getContractCount();
        return JsonFormat.getContractJson(list,count);
    }

    /**
     * 去往签订合同的页面
     * @return
     */
    @RequestMapping("/releaseContractPage")
    public String releaseContractPage(){
        return "releaseContractPage";
    }

    /**
     * 提交请求
     * @param hostName
     * @param rentName
     * @param houseId
     * @param content
     * @param hostCard
     * @param rentCard
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("/releaseContract")
    public String insertContract(@RequestParam("hostName")String hostName, @RequestParam("rentName")String rentName,
                                 @RequestParam("houseId")int houseId, @RequestParam("content")String content,
                                 @RequestParam("hostCard")String hostCard, @RequestParam("rentCard")String rentCard,
                                 @RequestParam("startTime") Date startTime, @RequestParam("endTime") Date endTime){
        Timestamp start = new Timestamp(startTime.getTime());
        Timestamp end = new Timestamp(endTime.getTime());
        boolean flag = contractService.inertContract(hostName,hostCard,rentName,rentCard,houseId,content,start,end);
        if (flag) {
            return "contract";
        }else {
            return "404";
        }
    }

    @RequestMapping("/deleteContractPage.html")
    public String deleteContractPage(){
        return "deleteContractPage";
    }

    @RequestMapping("/deleteContract")
    public String deleteContract(@RequestParam("id")int id, @RequestParam("hostName")String hostName,
                                 @RequestParam("rentName")String rentName, @RequestParam("hostPassword")String hostPassword,
                                 @RequestParam("rentPassword")String rentPassword, Model model){
        String host = contractService.getContractHostNameById(id);
        String rent = contractService.getContractRentNameById(id);
        String hostPwd = memberService.getPasswordByName(hostName);
        String rentPwd = memberService.getPasswordByName(rentName);
        if (host.equals(hostName)&&rent.equals(rentName)&&hostPassword.equals(hostPwd)&&rentPassword.equals(rentPwd)){
            contractService.deleteContractById(id);
            return "redirect:/contract.html";
        }else{
            model.addAttribute("msg","信息有误");
            return "deleteContractPage";
        }
    }
}
