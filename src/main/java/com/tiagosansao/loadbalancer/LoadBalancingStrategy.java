package com.tiagosansao.loadbalancer;

import java.util.List;

public interface LoadBalancingStrategy {
    public String chooseNode();
    public void addNode(String node);
    public void removeNode(String node);
}
