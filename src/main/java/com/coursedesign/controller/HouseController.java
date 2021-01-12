package com.coursedesign.controller;

import com.coursedesign.entity.*;
import com.coursedesign.service.houseService.*;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.utils.CurrentUser;
import com.coursedesign.utils.FileNameUtils;
import com.coursedesign.utils.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class HouseController {
    @Autowired
    HouseService houseService;
    @Autowired
    NoticeService noticeService;

    @Autowired
    PayTypeService payTypeService;

    @Autowired
    AddressService addressService;
    @Autowired
    HouseTypeService houseTypeService;
    @Autowired
    HouseResourceService houseResourceService;
    @Value("${image}")
    String path;


    @RequestMapping("/releaseHousePage")
    public String index(Model model){
        List<PayType> allPayType = payTypeService.getAllPayType();
        List<Address> allAddress = addressService.getAllAddress();
        List<HouseType> allHouseType = houseTypeService.getAllHouseType();
        List<HouseResourceType> allResourceType = houseResourceService.getAllHouseResourceType();
        model.addAttribute("payTypes",allPayType);
        model.addAttribute("allAddress",allAddress);
        model.addAttribute("allHouseType",allHouseType);
        model.addAttribute("allResourceType",allResourceType);
        model.addAttribute("currentUsername", CurrentUser.username);
        return "releaseHousePage";
    }


    // 发布房源
    @RequestMapping("/releaseHouse")
    public String releaseHouse(@RequestParam("hostName") String hostName, @RequestParam("tel") String tel,
                        @RequestParam("payType") String payType, @RequestParam("addressName") String addressName,
                        @RequestParam("address") String address, @RequestParam("area") int area,
                        @RequestParam("price") int price, @RequestParam("houseResourceType") String houseResourceType,
                        @RequestParam("houseType")String houseType,@RequestParam("content")String content) {
        House house = new House();
        house.setHostName(hostName);
        house.setTel(tel);
        house.setPayType(payType);
        house.setAddressName(addressName);
        house.setAddress(address);
        house.setArea(area);
        house.setPrice(price);
        house.setHouseResource(houseResourceType);
        house.setHouseType(houseType);
        house.setContent(content);
        house.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        houseService.insertHouse(house);
        // 发布新房源时更新notice
        noticeService.updateHouseCount();
        return "redirect:/house.html";
    }


    // 获取所有房源信息
    @RequestMapping("/getAllHouse")
    public String getAllHouse(Model model){
        List<House> allHouse = houseService.getAllHouse();
        model.addAttribute("allHouse",allHouse);
        return "";
    }

    // 通过区域名筛选房源信息
    @RequestMapping("/getHouseByAddressName")
    public String getHouseByAddress(@RequestParam("addressName")String addressName,
                                    Model model){
        List<House> houseList = houseService.getHouseByAddressName(addressName);
        model.addAttribute("getHouseByAddressName",houseList);
        return "";
    }

    // 通过房型筛选
    @RequestMapping("/getHouseByHouseResource")
    public String getHouseByHouseResource(@RequestParam("houseResource")String houseResource,
                                    Model model){
        List<House> houseList = houseService.getHouseByHouseResource(houseResource);
        model.addAttribute("getHouseByResource",houseList);
        return "";
    }

    // 通过户型筛选
    @RequestMapping("/getHouseByHouseType")
    public String getHouseByHouseType(@RequestParam("houseType")String houseType,
                                    Model model){
        List<House> houseList = houseService.getHouseByHouseType(houseType);
        model.addAttribute("getHouseByAddressName",houseList);
        return "";
    }

    // 修改房源信息
    @RequestMapping("/updateHousePage.html")
    public String updateHouse(Model model){
        List<PayType> allPayType = payTypeService.getAllPayType();
        List<Address> allAddress = addressService.getAllAddress();
        List<HouseType> allHouseType = houseTypeService.getAllHouseType();
        List<HouseResourceType> allResourceType = houseResourceService.getAllHouseResourceType();
        model.addAttribute("payTypes",allPayType);
        model.addAttribute("allAddress",allAddress);
        model.addAttribute("allHouseType",allHouseType);
        model.addAttribute("allResourceType",allResourceType);
        model.addAttribute("currentUsername", CurrentUser.username);
        return "updateHousePage";
    }

    // 修改房源页面
    @RequestMapping("/updateHouse")
    public String updateHousePage(@RequestParam("hostName") String hostName,@RequestParam("tel") String tel,
                                  @RequestParam("payType") String payType, @RequestParam("addressName") String addressName,
                                  @RequestParam("address") String address, @RequestParam("area") int area,
                                  @RequestParam("price") int price, @RequestParam("houseResourceType") String houseResourceType,
                                  @RequestParam("houseType")String houseType,@RequestParam("content")String content,
                                  @RequestParam("id") int id,Model model){

        String host_name = houseService.getHostNameById(id);
        if (host_name.equals(CurrentUser.username)){
            Timestamp time = new Timestamp(System.currentTimeMillis());
            boolean flag = houseService.updateHouse(address,tel,addressName,hostName,houseResourceType,houseType,
                    price,payType,area,content,time,id);
            if (flag){
                return "redirect:/house.html";
            }else {
                return "redirect:/404";
            }
        }else {
            List<PayType> allPayType = payTypeService.getAllPayType();
            List<Address> allAddress = addressService.getAllAddress();
            List<HouseType> allHouseType = houseTypeService.getAllHouseType();
            List<HouseResourceType> allResourceType = houseResourceService.getAllHouseResourceType();
            model.addAttribute("payTypes",allPayType);
            model.addAttribute("allAddress",allAddress);
            model.addAttribute("allHouseType",allHouseType);
            model.addAttribute("allResourceType",allResourceType);
            model.addAttribute("msg","您不是该房源的发布者，不能修改！");
            model.addAttribute("currentUsername", CurrentUser.username);
            return "updateHousePage";
        }

    }

    // 删除房源
    @RequestMapping("/deleteHousePage.html")
    public String deleteHousePage(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "deleteHousePage";
    }

    @RequestMapping("/deleteHouse")
    public String deleteHouse(@RequestParam("id")int id,@RequestParam("hostName")String hostName,
                              @RequestParam("password")String password,Model model){
        String host_name = houseService.getHostNameById(id);
        if (host_name.equals(CurrentUser.username)&&password.equals(CurrentUser.password)){
            houseService.deleteHouse(id);
            return "redirect:/house";
        }else {
            model.addAttribute("msg","您不是该房源的发布者，或者密码错误！");
            model.addAttribute("currentUsername", CurrentUser.username);
            return "deleteHousePage";
        }
    }
    @RequestMapping("/search")
    public String search(@RequestParam("param")String param,Model model){
        model.addAttribute("param",param);
        return "house";
    }

    @RequestMapping("/jsonHouseByHouseType/{param}")
    @ResponseBody
    public String jsonHouseByHouseType(@PathVariable("param")String param) {
        List<House> list = houseService.getHouseByHouseType(param);
        return JsonFormat.getHouseJson(list,3);
    }

    @RequestMapping("/jsonHouse")
    @ResponseBody
    public String jsonHouse() {
//        String param = model.getAttribute("param").toString();
        List<House> list  = houseService.getAllHouse();;
//        if (param.equals("")){
//            list = houseService.getAllHouse();
//        }else {
//            list = houseService.getHouseByHouseType(param);
//        }

        return JsonFormat.getHouseJson(list,3);
    }

}
