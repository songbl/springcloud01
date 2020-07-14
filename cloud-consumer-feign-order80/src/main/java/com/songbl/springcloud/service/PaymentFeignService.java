package com.songbl.springcloud.service;


import com.songbl.springcloud.entities.CommonResult;
import com.songbl.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//这个是客户端，调用服务名为CLOUD-PAYMENT-SERVICE下的主机，再拼接上下面方法的url
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")// 通过Fegin客户端调用远程的服务
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
     CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
     String paymentFeignTimeout();
}

