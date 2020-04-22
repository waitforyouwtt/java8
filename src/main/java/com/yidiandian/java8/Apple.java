package com.yidiandian.java8;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: 一点点
 * @Date: 2019/2/17 9:10
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Apple implements Serializable {

    /**
     * 颜色
     */
    private String color;
    /**
     * 重量
     */
    private int weight;

}
