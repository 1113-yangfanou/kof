package com.yang.service;

import com.alibaba.fastjson.JSONObject;
import com.yang.pojo.Record;

import java.util.List;

public interface RecordService {
    int addRecord(Record record);
    int deleteRecord(int id);
    JSONObject getAllRecord(int page);
    List<Record> getByName(String username);
}
