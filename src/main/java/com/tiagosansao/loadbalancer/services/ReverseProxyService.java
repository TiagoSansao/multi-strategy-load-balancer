package com.tiagosansao.loadbalancer.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface ReverseProxyService {
    ResponseEntity<String>  forwardRequest(String body,
                                                  String serverURL,
                                                  HttpServletRequest request)
            throws URISyntaxException;
}
