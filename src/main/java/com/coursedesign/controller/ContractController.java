package com.coursedesign.controller;

import com.coursedesign.entity.Contract;
import com.coursedesign.service.contractService.ContractService;
import com.coursedesign.utils.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/jsonContract")
    @ResponseBody
    public String jsonContract(){
        List<Contract> list = contractService.getAllContract();
        int count = contractService.getContractCount();
        return JsonFormat.getContractJson(list,count);
    }

    @RequestMapping("/releaseContractPage")
    public String releaseContractPage(){
        return "releaseContractPage";
    }
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
        }
        return "";
    }
}
