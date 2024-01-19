package com.tiagosansao.loadbalancer.services;

import org.springframework.web.client.RestTemplate;

public class ReverseProxyServiceFactory {
    public static ReverseProxyService createService(RestTemplate restTemplate) {
        return new ReverseProxyServiceImpl(restTemplate);
    }
}