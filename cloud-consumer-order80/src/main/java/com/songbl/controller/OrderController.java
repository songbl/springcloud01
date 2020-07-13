package com.songbl.controller;


import com.songbl.springcloud.entities.CommonResult;
import com.songbl.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    static final String PAYMENT_URL ="http://cloud-payment-service";


    // 用于http请求，远程的其它服务（这里是支付服务）
    //生成这个对象的时候，有负载均衡
    @Resource
    private RestTemplate restTemplate ;

    @Resource
    private DiscoveryClient discoveryClient ;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        // 通过容器中的 discoveryClient和服务名来获取服务集群
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        if(instances == null || instances.size() <= 0) {
//            return null;
//        }
        // 传入服务集群来计算出获取具体的服务实例
//        ServiceInstance serviceInstance = loadBalancer.instances(instances);
//        URI uri = serviceInstance.getUri();

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0) {
            return null;
        }
        URI uri =null;
        for (ServiceInstance instance: instances) {
            uri = instance.getUri();
            log.error(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        log.error(uri+"/payment/lb");

        //使用Ribbon之后，不能再用具体的url了
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/lb",String.class);
    }

}
