package com.yang.controller;

import com.alibaba.fastjson.JSON;
import com.yang.pojo.*;
import com.yang.service.RecordService;
import com.yang.service.UserService;
import com.yang.utils.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class FaceAnalyzeController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    private Map<String, String> analyze(String face_token, String username, String photo) {
        Map<String, String> map = new HashMap<>();
        String result = ApiUtil.result(face_token);
        FaceAnalyze faceAnalyze = JSON.parseObject(result, FaceAnalyze.class);
        String gender;
        if(faceAnalyze.getFaces().get(0).getAttributes().get(0).getGender().equals("Male")) {
            gender = "男性";
        } else {
            gender = "女性";
        }
        Emotion emotion = faceAnalyze.getFaces().get(0).getAttributes().get(0).getEmotion().get(0);
        Skinstatus skinstatus = faceAnalyze.getFaces().get(0).getAttributes().get(0).getSkinstatus().get(0);
        String description = "首先欢迎您来到颜值pk系统！根据您提供的图片，我们有如下几个结论：<br>1.我们推测您是一位"+gender+"; <br>2.您的年龄是"+faceAnalyze.getFaces().get(0).getAttributes().get(0).getAge().get(0).getValue()
                + ";<br>3.男性认为该人脸的颜值分数为：" +  faceAnalyze.getFaces().get(0).getAttributes().get(0).getBeauty().get(0).getMale_score() + ", 女性认为该人脸的颜值分数为："+
                faceAnalyze.getFaces().get(0).getAttributes().get(0).getBeauty().get(0).getFemale_score() +
                "; <br>4.您的情绪包含如下：" +
                            "愤怒: " + emotion.getAnger()+"%、"+
                            "厌恶: " + emotion.getDisgust()+"%、"+
                            "恐惧: " + emotion.getFear()+"%、"+
                            "高兴: " + emotion.getHappiness()+"%、"+
                            "平静: " + emotion.getNeutral()+"%、"+
                            "伤心: " + emotion.getSadness()+"%、" +
                            "惊讶: " + emotion.getSurprise()+"%;"
                + ";<br> 5. 您的面部特征状态: " +
                    "健康状态： " + skinstatus.getHealth() + "%、"+
                    "色斑: " + skinstatus.getStain() + "%、"+
                    "青春痘: " + skinstatus.getAcne() + "%、" +
                    "黑眼圈: " + skinstatus.getDark_circle() + "%、"
                + "<br>以上结果仅供参考！！！"
                ;
        User user = userService.getUserByName(username);
        int rating = user.getRating();
        double beauty_score = faceAnalyze.getFaces().get(0).getAttributes().get(0).getBeauty().get(0).getFemale_score() + faceAnalyze.getFaces().get(0).getAttributes().get(0).getBeauty().get(0).getMale_score();
        beauty_score /= 2;
        if(beauty_score >= 90) {
            beauty_score += 80;
        } else if(beauty_score >= 80) {
            beauty_score += 60;
        } else if(beauty_score >= 60) {
            beauty_score += 50;
        } else {
            beauty_score -= 5;
        }

        Record record = new Record(
                null,
                username,
                photo,
                rating,
                description
        );
        recordService.addRecord(record);
        map.put("msg", "评测成功");
        map.put("description", description);
        return map;
    }
}
