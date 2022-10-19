package com.yang.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.yang.dao.RecordMapper;
import com.yang.pojo.Record;
import com.yang.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public int deleteRecord(int id) {
        return recordMapper.deleteRecord(id);
    }

    @Override
    public JSONObject getAllRecord(int page) {
        PageHelper.startPage(page, 5);
        List<Record> records = recordMapper.getAllRecordByPage(page);
        List<Record> count = recordMapper.getAllRecord();
        JSONObject resp = new JSONObject();
        resp.put("record", records);
        resp.put("count", count.size());
        return resp;
    }

    @Override
    public List<Record> getByName(String username) {
        return recordMapper.getByName(username);
    }
}
