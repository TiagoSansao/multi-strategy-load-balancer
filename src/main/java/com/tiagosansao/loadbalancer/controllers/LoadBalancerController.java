package com.tiagosansao.loadbalancer.controllers;

import com.tiagosansao.loadbalancer.enums.LoadBalancingStrategyType;
import com.tiagosansao.loadbalancer.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URISyntaxException;

@RestController
class LoadBalancerController {
    private final LoadBalancerService loadBalancerService;
    private final ReverseProxyService reverseProxyService;

    public LoadBalancerController() {
        LoadBalancingStrategyType strategy = LoadBalancingStrategyType.ROUND_ROBING;
        RestTemplate restTemplate = new RestTemplate();

        this.reverseProxyService = ReverseProxyServiceFactory.createService(restTemplate);
        this.loadBalancerService = LoadBalancerServiceFactory.createService(strategy);
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.TRACE})
    public ResponseEntity<?> loadBalance(@RequestBody(required = false) String body, HttpServletRequest request) throws URISyntaxException {
        System.out.println("Received a request from " + request.getRemoteAddr());

        String serverURL = loadBalancerService.getAServerURL();
        ResponseEntity<String> responseEntity = reverseProxyService.forwardRequest(body, serverURL, request);

        return responseEntity;
    }
}