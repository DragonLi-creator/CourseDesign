package com.coursedesign.utils;

import com.alibaba.fastjson.JSON;
import com.coursedesign.entity.Contract;
import com.coursedesign.entity.House;
import com.coursedesign.entity.Rent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取响应JSON工具类
 */
public class JsonFormat {
    public static String getHouseJson(List<House> list,int count){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code","0");
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return JSON.toJSONString(map);
    }

    public static String getRentJson(List<Rent> list, int count){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code","0");
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return JSON.toJSONString(map);
    }

    public static String getContractJson(List<Contract> list, int count){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code","0");
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return JSON.toJSONString(map);
    }
}
