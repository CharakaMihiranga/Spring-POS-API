package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.OrderDto;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
}
