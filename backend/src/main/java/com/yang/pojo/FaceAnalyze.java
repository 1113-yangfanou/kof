package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceAnalyze {
    private String RequestId;
    private String ErrorMessage;
    private Integer FaceNum;
    private List<Faces> faces;
}
