package com.yang.service;

import com.yang.pojo.Record;

import java.util.List;

public interface RecordService {
    int addRecord(Record record);
    int deleteRecord(int id);
    List<Record> getAllRecord();
    List<Record> getByName(String username);
}
