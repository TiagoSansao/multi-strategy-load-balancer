package com.tiagosansao.loadbalancer.domain;

import java.util.List;

public interface LoadBalancingStrategy {
    public String chooseNode(List<String> nodes);
}
