package lk.ijse.gdse.springboot.springposapi.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.springboot.springposapi.dto.OrderDto;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.exception.ItemNotFoundException;
import lk.ijse.gdse.springboot.springposapi.response.OrderResponse;
import lk.ijse.gdse.springboot.springposapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;
    static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@Valid @RequestBody OrderDto orderDto) {
        try {
            orderService.saveOrder(orderDto);
            logger.info("Order with ID: {} saved successfully.", orderDto.getId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataPersistFailedException | CustomerNotFoundException | ItemNotFoundException e) {
            logger.warn("Failed to save order with ID: {}. Error: {}", orderDto.getId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            logger.error("Internal server error while saving order with ID: {}.", orderDto.getId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse getOrder(@PathVariable("orderId") Long orderId){
        return orderService.getOrder(orderId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

}

