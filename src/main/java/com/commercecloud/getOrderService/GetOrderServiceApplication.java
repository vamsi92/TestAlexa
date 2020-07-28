package com.commercecloud.getOrderService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class GetOrderServiceApplication {
    String url="http://cc-demo-3.ad3.fusionappsdephx.oraclevcn.com:9080/ccadminui/v1";

@GetMapping("/")
public String sayHi(){

    return "Hello Spring";
}

    @GetMapping("/message/{input}")
    public String getMessage(@PathVariable String input){

        return url+ input;
    }


public static void main(String args[]){

        SpringApplication.run(GetOrderServiceApplication.class,args);
}
}
