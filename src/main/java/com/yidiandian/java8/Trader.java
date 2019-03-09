package com.yidiandian.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: 一点点
 * @Date: 2019/3/3 17:05
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Trader {
    private  String name;
    private  String city;
}
