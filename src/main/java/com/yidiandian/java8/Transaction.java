package com.yidiandian.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: 一点点
 * @Date: 2019/3/3 17:06
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private  Trader trader;
    private  int year;
    private  int value;
}
