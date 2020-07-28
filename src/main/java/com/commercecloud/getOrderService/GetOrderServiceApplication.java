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

    @GetMapping("/message/{data}")
    public String getMessage(@PathVariable String data){

        return data;
    }

    @GetMapping("/orderDetails/{orderId}")
    public String getOrderDetails(@PathVariable String orderId){

        String accessToken=userLogin("admin","admin","123456");
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer "+accessToken);
        HttpEntity entity=new HttpEntity(headers);
        ResponseEntity<String> response=restTemplate.exchange(url+"/orders/"+orderId, HttpMethod.GET,entity,String.class);
        JSONObject orderDetailsResponse=new JSONObject(response.getBody());
        JSONObject jsObj=(JSONObject) ((JSONArray) orderDetailsResponse.get("commerceItems")).get(0);
        return jsObj.get("productDisplayName").toString();


    }
    public String userLogin(String email, String password,String otp) {
        String url = new StringBuilder()
                .append("http://cc-demo-3.ad3.fusionappsdephx.oraclevcn.com:9080/ccadminui/v1/mfalogin")
                .toString();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", email);
        map.add("password", password);
        map.add("totp_code",otp);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class);
        JSONObject responseObj=new JSONObject(response.getBody());
        return responseObj.get("access_token").toString();
    }

public static void main(String args[]){

        SpringApplication.run(GetOrderServiceApplication.class,args);
}
}
