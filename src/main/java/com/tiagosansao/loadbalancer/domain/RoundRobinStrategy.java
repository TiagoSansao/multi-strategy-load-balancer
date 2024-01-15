package com.tiagosansao.loadbalancer.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import com.tiagosansao.loadbalancer.domain.NodeManager;

public class RoundRobinStrategy implements LoadBalancingStrategy {
    private final AtomicInteger curIndex;

    public RoundRobinStrategy() {
        this.curIndex = new AtomicInteger();
    }

    @Override
    public String chooseNode(List<String> nodes) {
        final int nodeIndex = curIndex.getAndIncrement() % nodes.size();
        System.out.print(this.curIndex);

        return nodes.get(nodeIndex);
    }
}
