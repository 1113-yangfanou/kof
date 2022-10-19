package com.yang.dao;

import com.yang.pojo.Record;

import java.util.List;

public interface RecordMapper {
    int addRecord(Record record);
    int deleteRecord(int id);
    List<Record> getAllRecordByPage(int page);
    List<Record> getAllRecord();
    List<Record> getByName(String username);
}
