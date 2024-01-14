package com.tiagosansao.loadbalancer.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinStrategy implements LoadBalancingStrategy {
    private List<String> nodes = null;
    private final AtomicInteger curIndex;

    public RoundRobinStrategy() {
        this.curIndex = new AtomicInteger();
    }

    @Override
    public String chooseNode() {
        final int nodeIndex = curIndex.getAndIncrement() % nodes.size();

        return nodes.get(nodeIndex);
    }

    @Override
    public void addNode(String node) {
        if (!nodes.contains(node)) throw new IllegalStateException("The node" + node + "already was registered.");

        this.nodes.add(node);
    }

    @Override
    public void removeNode(String node) {
        if (!nodes.contains(node)) throw new IllegalStateException("The node" + node + "was not registered.");

        this.nodes.remove(node);
    }
}
