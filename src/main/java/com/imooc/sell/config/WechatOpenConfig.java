package com.imooc.sell.config;


import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxOpenService;
    }


    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setAppId(wechatAccountConfig.getMpAppId());
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setSecret(wechatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;


    }
}
