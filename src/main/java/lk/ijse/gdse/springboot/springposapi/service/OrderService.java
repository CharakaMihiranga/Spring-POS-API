package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrder(Long orderId);
}
