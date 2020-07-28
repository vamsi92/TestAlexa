package com.commercecloud.getOrderService.resource;

import com.commercecloud.getOrderService.models.OrderDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/getOrderDetails")
public class GetOrderResource {
    //String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyZDg5NTNjNS1kN2U0LTQxMTYtOWNiNC03YWFkNzZiZmUxNmQiLCJpc3MiOiJhcHBsaWNhdGlvbkF1dGgiLCJleHAiOjE2MjczNzI5OTQsImlhdCI6MTU5NTgzNjk5NH0=.QJFAXHyIL5qcu40KJSLKPDSdL86lYthdpm8iIMQhiD8=";
    String url="http://cc-demo-3.ad3.fusionappsdephx.oraclevcn.com:9080/ccadminui/v1";

    @RequestMapping("/{orderId}")
    public String getOrderDetails(@PathVariable("orderId") String orderId){

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
    public static void main(String[] args){

        GetOrderResource o=new GetOrderResource();
        o.getOrderDetails("o10001");
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
}

