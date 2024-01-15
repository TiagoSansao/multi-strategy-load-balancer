package com.tiagosansao.loadbalancer.services;

import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategy;
import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategyFactory;
import com.tiagosansao.loadbalancer.enums.LoadBalancingStrategyType;

public class LoadBalancerService {
    final private LoadBalancingStrategy currentStrategy;

    public LoadBalancerService(LoadBalancingStrategyType strategyType) {
        this.currentStrategy = LoadBalancingStrategyFactory.create(strategyType);
    }
    public String getAServerURL() {
        return currentStrategy.chooseNode();
    }
}
