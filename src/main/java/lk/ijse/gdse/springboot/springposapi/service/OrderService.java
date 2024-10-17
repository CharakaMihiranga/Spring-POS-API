package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.impl.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void saveOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrder(Long orderId);
}
