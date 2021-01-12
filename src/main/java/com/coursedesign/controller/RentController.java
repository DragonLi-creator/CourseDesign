package com.coursedesign.controller;

import com.coursedesign.entity.Address;
import com.coursedesign.entity.HouseResourceType;
import com.coursedesign.entity.HouseType;
import com.coursedesign.entity.Rent;
import com.coursedesign.service.houseService.AddressService;
import com.coursedesign.service.houseService.HouseResourceService;
import com.coursedesign.service.houseService.HouseTypeService;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.service.rentService.RentService;
import com.coursedesign.utils.CurrentUser;
import com.coursedesign.utils.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class RentController {
    @Autowired
    RentService rentService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AddressService addressService;
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    HouseResourceService houseResourceService;



    @RequestMapping("/jsonRent")
    @ResponseBody
    public String jsonRent(){
        List<Rent> list = rentService.getRentList();
        int count = rentService.getRentCount();
        return JsonFormat.getRentJson(list,count);
    }

    /**
     * 修改rent
     * @param model
     * @return
     */
    @RequestMapping("/updateRentPage.html")
    public String updateRentPage(Model model) {
        List<Address> allAddress = addressService.getAllAddress();
        List<HouseType> allHouseType = houseTypeService.getAllHouseType();
        List<HouseResourceType> allResourceType = houseResourceService.getAllHouseResourceType();
        model.addAttribute("allAddress",allAddress);
        model.addAttribute("allHouseType",allHouseType);
        model.addAttribute("allResourceType",allResourceType);
        model.addAttribute("currentUsername", CurrentUser.username);
        return "updateRentPage";
    }

    // 修改求租页面
    @RequestMapping("/updateRent")
    public String updateRentPage(@RequestParam("addressName") String addressName,
                                 @RequestParam("price") int price, @RequestParam("houseResource") String houseResource,
                                 @RequestParam("houseType")String houseType, @RequestParam("content")String content,
                                 @RequestParam("people")String people, @RequestParam("id") int id,Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        String rentPeople = rentService.getPeopleById(id);
        if (rentPeople.equals(CurrentUser.username)){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            boolean flag = rentService.updateRent(addressName,houseResource,price,houseType,content,
                    people,timestamp,id);
            if (flag) {
                return "rent";
            }else {
                return "404";
            }
        }else {
            model.addAttribute("msg","您不是该求租信息的发布者，不能修改！");
            return "updateHousePage";

        }


    }

    // 删除rent
    @RequestMapping("/deleteRentPage.html")
    public String deleteRentPage(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "deleteRentPage";
    }
    @RequestMapping("/deleteRent")
    public String deleteRent(@RequestParam("id")int id,@RequestParam("people")String people,
                             @RequestParam("password")String password,Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        String rentPeople = rentService.getPeopleById(id);
        if (rentPeople.equals(CurrentUser.username)&&password.equals(CurrentUser.password)){
            rentService.deleteRent(id);
            return "rent";
        }else {
            model.addAttribute("msg","您不是该房源的发布者，或者密码错误！");
            return "deleteRentPage";
        }

    }

    // 发布新的求租信息
    @RequestMapping("/releaseRentPage")
    public String rentHousePage(Model model){
        List<Address> allAddress = addressService.getAllAddress();
        List<HouseType> allHouseType = houseTypeService.getAllHouseType();
        List<HouseResourceType> allResourceType = houseResourceService.getAllHouseResourceType();
        model.addAttribute("allAddress",allAddress);
        model.addAttribute("allHouseType",allHouseType);
        model.addAttribute("allResourceType",allResourceType);
        model.addAttribute("currentUsername", CurrentUser.username);
        return "releaseRentPage";
    }

    @RequestMapping("/releaseRent")
    public String insertRent(@RequestParam("addressName")String addressName,@RequestParam("houseResource")String houseResource,
                             @RequestParam("price")int price,@RequestParam("houseType")String houseType,
                             @RequestParam("content")String content, @RequestParam("people")String people,
                             Model model){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        boolean flag = rentService.insertRent(addressName,houseResource,price,houseType,content,people,timestamp);
        if (flag) {
            // 发布新求租信息时更新notice
//            noticeService.updateRentCount();
            return "redirect:/rent.html";
        }else {
            return "404";
        }

    }




}
