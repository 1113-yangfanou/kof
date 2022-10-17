package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    private String gender;
    private List<Age> age;
    private List<Beauty> beauty;
    private List<Emotion> emotion;
    private List<Skinstatus> skinstatus;
}
