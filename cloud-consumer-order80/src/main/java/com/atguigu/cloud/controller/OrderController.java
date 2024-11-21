package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    // 硬编码格式
    //private static final String PaymentSrv_URL="http://localhost:8001";
    //使用服务注册中心上的微服务名称
    private static final String PaymentSrv_URL="http://cloud-payment-service";
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForEntity(PaymentSrv_URL+"/pay/add",payDTO, ResultData.class).getBody();
//        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO, ResultData.class);
    }
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id, ResultData.class);
    }
}
