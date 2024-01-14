package com.tiagosansao.loadbalancer.domain;

import enums.LoadBalancingStrategyType;

public class LoadBalancingStrategyFactory {
    public static LoadBalancingStrategy create(LoadBalancingStrategyType strategyName) {
        switch (strategyName) {
            case ROUND_ROBING:
                return new RoundRobinStrategy();
            default:
                throw new IllegalStateException("The strategy name " + strategyName + " doesn't exist.");
        }
    }
}