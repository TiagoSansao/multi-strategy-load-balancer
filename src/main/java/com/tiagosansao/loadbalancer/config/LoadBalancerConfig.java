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
            NodeManager.addNode("127.0.0.1");
            NodeManager.addNode("127.0.0.2");
            NodeManager.addNode("127.0.0.3");

            System.out.println(NodeManager.getNodes());
        };
    }
}