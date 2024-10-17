package lk.ijse.gdse.springboot.springposapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.CustomerDao;
import lk.ijse.gdse.springboot.springposapi.dao.ItemDao;
import lk.ijse.gdse.springboot.springposapi.dao.OrderDao;
import lk.ijse.gdse.springboot.springposapi.dao.OrderDetailDao;
import lk.ijse.gdse.springboot.springposapi.dto.impl.OrderDetailDto;
import lk.ijse.gdse.springboot.springposapi.dto.impl.OrderDto;
import lk.ijse.gdse.springboot.springposapi.entity.impl.CustomerEntity;
import lk.ijse.gdse.springboot.springposapi.entity.impl.ItemEntity;
import lk.ijse.gdse.springboot.springposapi.entity.impl.OrderDetailEntity;
import lk.ijse.gdse.springboot.springposapi.entity.impl.OrderEntity;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.exception.ItemNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.OrderNotFoundException;
import lk.ijse.gdse.springboot.springposapi.service.OrderService;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerDao customerDao;
    private final ItemDao itemDao;
    private final OrderDao orderDao;
    private final OrderDetailDao orderDetailDao;
    private final Mapping mapping;
    @Override
    public void saveOrder(OrderDto orderDto) {
        // Map order dto to OrderEntity
        OrderEntity order = mapping.map(orderDto, OrderEntity.class);
        // Validate and assign customer
        CustomerEntity customerEntity = customerDao.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        order.setCustomerEntity(customerEntity);
        // Save order
        OrderEntity savedOrder = orderDao.save(order);
        // process order details and update items
        List<OrderDetailEntity> orderEntities = orderDto.getOrderDetailDtos().stream()
                .map(orderDetailDto -> {
                    ItemEntity itemEntity = itemDao.findById(orderDetailDto.getItemCode())
                            .orElseThrow(() -> new ItemNotFoundException("Item not found: "+ orderDetailDto.getItemCode()));
                    // update item qty
                    int updatedQty = itemEntity.getQtyOnHand() - orderDetailDto.getQty();
                    if (updatedQty < 0) {
                        throw new DataPersistFailedException("Insufficient stock for item: " + orderDetailDto.getItemCode());
                    }
                    itemEntity.setQtyOnHand(updatedQty);
                    itemDao.save(itemEntity); // save updated item qty
                    // Create order detail entity
                    return createOrderDetailEntity(orderDetailDto, savedOrder, itemEntity);
                })
                .collect(Collectors.toList());
        // Save order details
        orderDetailDao.saveAll(orderEntities);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        OrderEntity orderEntity = orderDao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        OrderDto orderDto = mapping.map(orderEntity, OrderDto.class);
        List<OrderDetailDto> orderDetailDtos = orderEntity.getOrderDetailEntities().stream()
                .map(orderDetailEntity -> mapping.map(orderDetailEntity, OrderDetailDto.class))
                .collect(Collectors.toList());
        orderDto.setOrderDetailDtos(orderDetailDtos);
        return orderDto;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return mapping.mapList(orderDao.findAll(), OrderDto.class); // map order entities to order dtos and return
    }

    private OrderDetailEntity createOrderDetailEntity(OrderDetailDto dto, OrderEntity order, ItemEntity item) {
        return OrderDetailEntity.builder()
                .orderEntity(order)
                .itemEntity(item)
                .qty(dto.getQty())
                .unitPrice(dto.getUnitPrice())
                .build();
    }

}
