package com.imooc.sell.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequestMapping("/weixin")
@RestController
public class WeixinController {


    @GetMapping("/auth")
    public  void wexin(@RequestParam("code") String code){

        log.info("进入方法，code=【{}】",code);

        //客户端访问url2，由微信进行跳转，再返回访问、/weixin/auth的接口，拿到参数code
        //https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
//        String url2 = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5758cfe25d52c2c5&redirect_uri=http://m.kmdiary.com/weixin/auth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";



        //拼接访问的url
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);
    }
}
