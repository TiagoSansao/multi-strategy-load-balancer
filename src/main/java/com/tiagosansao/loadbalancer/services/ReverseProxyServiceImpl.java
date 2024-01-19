package com.tiagosansao.loadbalancer.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

public class ReverseProxyServiceImpl implements ReverseProxyService {
    private final RestTemplate restTemplate;

    public ReverseProxyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> forwardRequest(String body, String serverURL, HttpServletRequest request) throws URISyntaxException {
        String requestURI = request.getRequestURI();
        URI nodeTargetURI = new URI("http://" + serverURL + requestURI);
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        Enumeration<String> headersList = request.getHeaderNames();
        HttpHeaders newHeaders = new HttpHeaders();
        String originalIncomingAddress = request.getRemoteAddr();

        while(headersList.hasMoreElements()) {
            String headerKey = headersList.nextElement();
            String headerValue = request.getHeader(headerKey);

            newHeaders.set(headerKey, headerValue);
        }

        newHeaders.set("X-Forwarded-For", originalIncomingAddress);

        HttpEntity<String> httpEntity = new HttpEntity<>(body, newHeaders);

        try {
            System.out.println(nodeTargetURI);
            ResponseEntity<String> response =
                    restTemplate.exchange(nodeTargetURI, method, httpEntity, String.class);

            System.out.println("Successfully forwarded the request to " + nodeTargetURI);

            return response;
        } catch(HttpStatusCodeException error) {
            System.err.println(error.getMessage());

            return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

