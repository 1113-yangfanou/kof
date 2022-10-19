package com.yang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.pojo.User;
import com.yang.service.RecordService;
import com.yang.service.UserService;
import com.yang.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestParam Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        String username = data.get("username");
        String password = data.get("password");
        System.out.println("username = " + username + ", password = " + password);
        User user = userService.getUserByName(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                map.put("msg", "登录成功");
                map.put("user", JSON.toJSONString(user));
            } else {
                map.put("msg", "用户名密码错误");
            }
        } else {
            map.put("msg", "不存在该用户");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> register(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        User _user = userService.getUserByName(username);
        Map<String, String> map = new HashMap<>();
        if(_user != null) {
            map.put("msg", "该用户已存在");
            return map;
        }
        User user = new User(
                null,
                username,
                password,
                "https://tse3-mm.cn.bing.net/th/id/OIP-C.bSxjWFuOoVlnfbGD6W4FfgAAAA?w=204&h=204&c=7&r=0&o=5&pid=1.7",
                1500
        );
        int i = userService.addUser(user);
        if(i > 0) {
            map.put("msg", "注册成功");
        } else {
            map.put("msg", "注册失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, String> uploadFile(MultipartFile file) throws IOException {
        String url = OssUtil.upload(Objects.requireNonNull(file.getOriginalFilename()), file.getInputStream());
        Map<String, String> map = new HashMap<>();
        map.put("msg", "上传成功");
        map.put("url", url);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/get/ranklist", method = RequestMethod.GET)
    public JSONObject getRankList(@RequestParam Map<String, String> data) {
        return recordService.getAllRecord(Integer.parseInt(data.get("page")));
    }

}
