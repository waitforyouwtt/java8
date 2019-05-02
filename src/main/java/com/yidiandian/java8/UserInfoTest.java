package com.yidiandian.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 一点点
 * @Date: 2019/3/10 12:55
 * @Version 1.0
 */
public class UserInfoTest {

    public static void main(String[] args) {
        String str= "i love china";
        List<String> list = Arrays.asList(str.split(" "));
        Collections.reverse(list);
        System.out.println("得到的结果："+list);
    }
}
