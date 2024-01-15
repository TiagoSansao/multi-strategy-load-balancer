package com.tiagosansao.loadbalancer.services;

import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategy;
import com.tiagosansao.loadbalancer.domain.LoadBalancingStrategyFactory;
import com.tiagosansao.loadbalancer.domain.NodeManager;
import com.tiagosansao.loadbalancer.enums.LoadBalancingStrategyType;

import java.util.List;

class LoadBalancerServiceImpl implements LoadBalancerService {
    final private LoadBalancingStrategy currentStrategy;

    public LoadBalancerServiceImpl(LoadBalancingStrategyType strategyType) {
        this.currentStrategy = LoadBalancingStrategyFactory.create(strategyType);
    }
    public String getAServerURL() {
        List<String> nodes = NodeManager.getNodes();
        String chosenNode = currentStrategy.chooseNode(nodes);

        System.out.println(chosenNode + " was chosen to receive traffic.");

        return chosenNode;
    }
}
