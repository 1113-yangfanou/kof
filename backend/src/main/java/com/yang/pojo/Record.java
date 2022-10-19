package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer id;
    private String username;
    private String photo;
    private Integer rating;
    private String description;
    private double score;
}
