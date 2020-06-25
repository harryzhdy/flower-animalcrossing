package com.springboot.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.mybatis.entity.User;
import com.springboot.mybatis.mapper.UserMapper;
import com.springboot.mybatis.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@EnableTransactionManagement
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /*@Autowired
    private FlowerService flowerService;*/

    @RequestMapping("/all")
    public JSONObject findAll(){
        List<User> userList = userMapper.findAll();
        JSONObject json =new JSONObject();
        json.put("data",userList);
        return json;
    }

    @RequestMapping("/add")
    public void addOne(User user){
        userMapper.addOne(user);
    }

    @RequestMapping("/update")
    public JSONObject update(User user){
        userMapper.update(user);
        JSONObject json =new JSONObject();
        json.put("data",user);
        return json;
    }

    @RequestMapping("/find")
    public JSONObject findOne(int id){
        User user = userMapper.findOne(id);
        JSONObject json =new JSONObject();
        json.put("data",user);
        return json;
    }

    @RequestMapping("/del")
    public void delOne(int id){
        userMapper.delOne(id);
    }

    @RequestMapping("/color")
    public JSONObject getColor(String f1, String f2) {
        FlowerService flowerService = new FlowerService(f1, f2);
        flowerService.init();

        Map<String, Double> resultMap = flowerService.getResult();

        JSONObject json =new JSONObject();
        int i = 1;
        for (String gene : resultMap.keySet()) {
            String resultOne = gene + ", " + resultMap.get(gene) + ", " + userMapper.getColor(gene);
            json.put("data" + i, resultOne);
            i++;
        }

        return json;
    }

}
