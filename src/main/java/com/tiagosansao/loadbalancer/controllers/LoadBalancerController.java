package com.tiagosansao.loadbalancer.controllers;

import com.tiagosansao.loadbalancer.enums.LoadBalancingStrategyType;
import com.tiagosansao.loadbalancer.services.LoadBalancerServiceFactory;
import com.tiagosansao.loadbalancer.services.LoadBalancerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
class LoadBalancerController {
    private final LoadBalancerService loadBalancerService;

    public LoadBalancerController() {
        LoadBalancingStrategyType strategy = LoadBalancingStrategyType.ROUND_ROBING;
        this.loadBalancerService = LoadBalancerServiceFactory.createService(strategy);
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.TRACE})
    public RedirectView loadBalance(RedirectAttributes attributes, HttpServletRequest request) {

        attributes.addAttribute("X-FORWARDED-FOR", request.getRemoteAddr());

        String serverURL = loadBalancerService.getAServerURL();

        return new RedirectView(serverURL);
    }
}