package com.tiagosansao.loadbalancer.services;

import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategy;
import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategyFactory;
import enums.LoadBalancingStrategyType;
import org.springframework.stereotype.Service;

@Service
public class LoadBalancerService {
    final private LoadBalancingStrategy currentStrategy;

    public LoadBalancerService(LoadBalancingStrategyType strategyType) {
        this.currentStrategy = LoadBalancingStrategyFactory.create(strategyType);
    }
    public void forwardRequest() {
        String node = currentStrategy.chooseNode();
    }
}
