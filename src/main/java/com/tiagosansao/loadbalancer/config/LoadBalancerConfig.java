package com.tiagosansao.loadbalancer.config;

import com.tiagosansao.loadbalancer.domain.NodeManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfig {
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            NodeManager nodeManager = NodeManager.getInstance();

            nodeManager.addNode("google.com");
            nodeManager.addNode("yahoo.com");

//            nodeManager.addNode("localhost:4321");
//            nodeManager.addNode("localhost:4322");
//            nodeManager.addNode("localhost:4323");

            System.out.println(nodeManager.getNodes());
        };
    }
}