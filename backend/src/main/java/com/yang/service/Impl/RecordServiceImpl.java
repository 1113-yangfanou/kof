package com.yang.service.Impl;

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
    public List<Record> getAllRecord() {
        return recordMapper.getAllRecord();
    }

    @Override
    public List<Record> getByName(String username) {
        return recordMapper.getByName(username);
    }
}
