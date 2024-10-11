package lk.ijse.gdse.springboot.springposapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthCheck")
public class HealthCheckController {
    @GetMapping  // http://localhost:8080/springpos/api/v1/healthCheck
    public String healthCheck() {
        return "Spring POS API is running";
    }
}
