package com.tiagosansao.loadbalancer.services;

import com.tiagosansao.loadbalancer.enums.LoadBalancingStrategyType;

public class LoadBalancerServiceFactory {
    public static LoadBalancerService createService(LoadBalancingStrategyType strategyType) {
        return new LoadBalancerServiceImpl(strategyType);
    }
}
