package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.OrderDto;
import lk.ijse.gdse.springboot.springposapi.response.OrderResponse;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderResponse getOrder(Long orderId);
}
