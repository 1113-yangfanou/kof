package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skinstatus {
    private double health;
    private double stain;
    private double acne;
    private double dark_circle;
}
