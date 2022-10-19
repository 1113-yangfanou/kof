package com.yang.controller;

import com.alibaba.fastjson.JSON;
import com.yang.pojo.*;
import com.yang.service.RecordService;
import com.yang.service.UserService;
import com.yang.utils.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/get/token", method = RequestMethod.POST)
    private Map<String, String> getToken(@RequestParam Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        String url = data.get("url");
        String result = ApiUtil.getToken(url);
        System.out.println(result);
        FaceAnalyze face = JSON.parseObject(result, FaceAnalyze.class);
        String errorMessage = face.getErrorMessage();
        System.out.println("error_message = "+errorMessage);
        if(errorMessage != null) {
            if(errorMessage.contains("IMAGE_ERROR_UNSUPPORTED_FORMAT")) {
                map.put("msg", "图片解析错误！有可能不是一个图像文件、或有数据破损、或图片文件格式不符合要求。请重试！");
            } else if(errorMessage.contains("INVALID_IMAGE_SIZE")) {
                map.put("msg", "图片太大或者图中人脸太小或不存在");
            }
            return map;
        } else if(face.getFaceNum() != 1) {
            map.put("msg","图片解析成功！但是没有找到人脸或者人脸数量大于1");
            return map;
        } else {
            map.put("face_token" ,face.getFaces().get(0).getFaceToken());
            return map;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    private Map<String, String> analyze(@RequestParam Map<String, String> data) {
        String face_token = data.get("face_token");
        String username = data.get("username");
        String photo = data.get("photo");
        Map<String, String> map = new HashMap<>();
        String result = ApiUtil.result(face_token);
        FaceAnalyze faceAnalyze = JSON.parseObject(result, FaceAnalyze.class);
        System.out.println(faceAnalyze);
        String gender;
        if(faceAnalyze.getFaces().get(0).getAttributes().get(0).getGender().get(0).getValue().equals("Male")) {
            gender = "男性";
        } else {
            gender = "女性";
        }
        Emotion emotion = faceAnalyze.getFaces().get(0).getAttributes().get(0).getEmotion().get(0);
        Skinstatus skinstatus = faceAnalyze.getFaces().get(0).getAttributes().get(0).getSkinstatus().get(0);
        String description = "根据您提供的图片，我们有如下几个结论：<br>1.我们推测您是一位"+gender+"; <br>2.您的年龄是"+faceAnalyze.getFaces().get(0).getAttributes().get(0).getAge().get(0).getValue()
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
            rating += 80;
        } else if(beauty_score >= 80) {
            rating += 60;
        } else if(beauty_score >= 60) {
            rating += 50;
        } else {
            rating -= 5;
        }
        map.put("score", String.valueOf(beauty_score));

        Record record = new Record(
                null,
                username,
                photo,
                rating,
                description,
                beauty_score
        );
        recordService.addRecord(record);
        map.put("msg", "评测成功");
        map.put("description", description);
        return map;
    }
}
