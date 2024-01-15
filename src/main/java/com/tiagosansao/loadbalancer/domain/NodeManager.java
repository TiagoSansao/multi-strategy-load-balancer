package com.tiagosansao.loadbalancer.domain;

import java.util.ArrayList;
import java.util.List;

public class NodeManager {
    public static List<String> nodes = new ArrayList<String>();

    public static List<String> getNodes() {
        return nodes;
    }

    public static void addNode(String node) {
        if (nodes.contains(node)) throw new IllegalStateException("The node" + node + " already was registered.");

        nodes.add(node);
        System.out.println("The node " + node + " was registered successfully.");
    }

    public static void removeNode(String node) {
        if (!nodes.contains(node)) throw new IllegalStateException("The node" + node + " was not registered.");

        nodes.remove(node);
        System.out.println("The node " + node + " was reomved successfully.");
    }
}
