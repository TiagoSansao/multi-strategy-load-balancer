package com.tiagosansao.loadbalancer.domain;

import java.util.ArrayList;
import java.util.List;

// Singleton
public class NodeManager {
    private List<String> nodes = new ArrayList<String>();
    private static NodeManager instance;

    private NodeManager() {}

    public static NodeManager getInstance() {
        if (instance == null) {
            instance = new NodeManager();
        }

        return instance;
    }

    public List<String> getNodes() {
        return nodes;
    }


    public  void addNode(String node) {
        if (nodes.contains(node)) throw new IllegalStateException("The node" + node + " already was registered.");

        nodes.add(node);
        System.out.println("The node " + node + " was registered successfully.");
    }

    public void removeNode(String node) {
        if (!nodes.contains(node)) throw new IllegalStateException("The node" + node + " was not registered.");

        nodes.remove(node);
        System.out.println("The node " + node + " was reomved successfully.");
    }
}
