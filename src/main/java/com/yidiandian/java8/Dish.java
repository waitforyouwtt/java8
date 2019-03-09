package com.yidiandian.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 一点点
 * @Date: 2019/3/2 16:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Dish {
    private  String name;
    private  boolean vegetarian;
    private  int calories;
    private  Type type;

    public enum Type {
        MEAT, FISH, OTHER
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
