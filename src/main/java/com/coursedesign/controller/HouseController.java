package com.coursedesign.controller;

import com.coursedesign.entity.*;
import com.coursedesign.service.houseService.*;
import com.coursedesign.service.noticeService.NoticeService;
import com.coursedesign.utils.CurrentUser;
import com.coursedesign.utils.JsonFormat;
import com.coursedesign.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    TypeUtil typeUtil;
    @Value("${image}")
    String path;


    /**
     * 去往发布房源信息的页面，并动态更新选项参数
     * @param model
     * @return
     */
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


    /**
     * 发布新的房源，以下是form变淡携带的参数
     * @param hostName
     * @param tel
     * @param payType
     * @param addressName
     * @param address
     * @param area
     * @param price
     * @param houseResourceType
     * @param houseType
     * @param content
     * @return
     */
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

    /**
     * 去往修改房源信息的页面，并动态更新选项参数
     * @param model
     * @return
     */
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

    /**
     * 更新房源信息
     * @param hostName
     * @param tel
     * @param payType
     * @param addressName
     * @param address
     * @param area
     * @param price
     * @param houseResourceType
     * @param houseType
     * @param content
     * @param id
     * @param model
     * @return
     */
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

    /**
     * 删除房源
     * @param model
     * @return
     */
    @RequestMapping("/deleteHousePage.html")
    public String deleteHousePage(Model model){
        model.addAttribute("currentUsername", CurrentUser.username);
        return "deleteHousePage";
    }

    /**
     * 提交删除房源的请求
     * @param id
     * @param hostName
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/deleteHouse")
    public String deleteHouse(@RequestParam("id")int id,@RequestParam("hostName")String hostName,
                              @RequestParam("password")String password,Model model){
        String host_name = houseService.getHostNameById(id);
        if (host_name.equals(CurrentUser.username)&&password.equals(CurrentUser.password)){
            houseService.deleteHouse(id);
            return "redirect:/house.html";
        }else {
            model.addAttribute("msg","您不是该房源的发布者，或者密码错误！");
            model.addAttribute("currentUsername", CurrentUser.username);
            return "deleteHousePage";
        }
    }
//    @RequestMapping("/search")
//    public String search(@RequestParam("param")String param,Model model){
//        return "redirect:/jsonHouse/param";
//        model.addAttribute("param",param);
//        return "redirect:/jsonHouse/param";
//    }

    @RequestMapping("/jsonHouseByHouseType/{param}")
    @ResponseBody
    public String jsonHouseByHouseType(@PathVariable("param")String param) {
        List<House> list = houseService.getHouseByHouseType(param);
        return JsonFormat.getHouseJson(list,3);
    }

//
//    /**
//     * 返回House实体类的json格式
//     * @return
//     */
//    @RequestMapping("/jsonHouse/{param}")
//    @ResponseBody
//    public String jsonHouse(@PathVariable("param")String param) {
//        List<House> list;
//
//        String[] res = typeUtil.getType(param);
//        if ("houseType".equals(res[0])) {
//            list = houseService.getHouseByHouseType(res[1]);
//            return JsonFormat.getHouseJson(list, 3);
//        }
//        list = houseService.getAllHouse();
//        return JsonFormat.getHouseJson(list,3);
//    }



    /**
     * 返回House实体类的json格式
     * @return
     */
    @RequestMapping("/jsonHouse")
    @ResponseBody
    public String jsonHouse() {
        List<House> list;
        list = houseService.getAllHouse();
        int count = houseService.getHouseCount();
        return JsonFormat.getHouseJson(list,count);
    }

}
