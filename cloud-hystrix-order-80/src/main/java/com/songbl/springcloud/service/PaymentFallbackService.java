package com.songbl.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {


    public String paymentInfo_OK(Integer id) {
        return "哈哈哈 局----PaymentFallbackService fall back paymentInfo_OK,o(╥﹏╥)o";
    }


    //重写这个方法，直接讲重写的方法作为降级方法，这样刚好一个方法对应一个降级方法
    public String paymentInfo_Timeout(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_Timeout,o(╥﹏╥)o";
    }
}
