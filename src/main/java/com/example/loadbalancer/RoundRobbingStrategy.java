package com.example.loadbalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobbingStrategy implements LoadBalancingStrategy {
    private List<String> nodes = null;
    private final AtomicInteger curIndex;

    public RoundRobbingStrategy() {
        this.curIndex = new AtomicInteger();
    }

    @Override
    public String chooseNode() {
        return "";
    }

    @Override
    public void addNode(String node) {}

    @Override
    public void removeNode(String Node) {}
}
