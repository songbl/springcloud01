package com.songbl.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.songbl.springcloud.service.PaymentFallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//指定这个方法为默认降级方法，当业务类灭有指定降级方法时候，就使用这个方法作为降级方法
//@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentFallbackService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //  一般除了个别核心业务有专属，其它普通的可以通过@DefaultProperties（defaultFallback=""）来统一跳转到处理结果页面
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
//    })
     @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        log.error("访问超时方法...");
      String result = paymentHystrixService.paymentInfo_Timeout(id);

        return  result;

    }



    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return  result;
    }

    //指定的备选方法，要和原方法参数个数，类型一致，否者会报错
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "/(ToT)/我是消费者80，调用8001支付系统繁忙，请10秒钟后重新尝试、\t";
    }

//    // 下面是全局fallback方法
//    public String paymentInfo_Global_FallbackMethod() {
//        return "Global异常处理信息，请稍后再试， /(ToT)/";
//    }
}
