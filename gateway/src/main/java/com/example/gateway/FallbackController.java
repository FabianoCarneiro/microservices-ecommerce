package com.example.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> fallback() {
        return buildFallbackResponse("Serviço temporariamente indisponível. Por favor, tente novamente mais tarde.");
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> fallbackPost() {
        return buildFallbackResponse("Serviço de criação temporariamente indisponível.");
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> fallbackPut() {
        return buildFallbackResponse("Serviço de atualização temporariamente indisponível.");
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> fallbackDelete() {
        return buildFallbackResponse("Serviço de exclusão temporariamente indisponível.");
    }

    private ResponseEntity<Map<String, Object>> buildFallbackResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
        response.put("error", "Service Unavailable");
        response.put("message", message);
        response.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
}
