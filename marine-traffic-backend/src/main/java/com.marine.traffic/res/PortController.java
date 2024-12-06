package com.marine.traffic.res;

import com.marine.traffic.service.PortService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/port")
public class PortController {
    private final PortService portService;

    public PortController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping("/vietnam")
    public ResponseEntity<Object> getVietnamPorts() {
        return ResponseEntity.ok(portService.getPortsByCountry("Viet Nam"));
    }
}
