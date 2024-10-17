package lk.ijse.gdse.springboot.springposapi.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.CustomerDao;
import lk.ijse.gdse.springboot.springposapi.dao.ItemDao;
import lk.ijse.gdse.springboot.springposapi.dao.OrderDao;
import lk.ijse.gdse.springboot.springposapi.dao.OrderDetailDao;
import lk.ijse.gdse.springboot.springposapi.dto.OrderDetailDto;
import lk.ijse.gdse.springboot.springposapi.dto.OrderDto;
import lk.ijse.gdse.springboot.springposapi.entity.CustomerEntity;
import lk.ijse.gdse.springboot.springposapi.entity.ItemEntity;
import lk.ijse.gdse.springboot.springposapi.entity.OrderDetailEntity;
import lk.ijse.gdse.springboot.springposapi.entity.OrderEntity;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.exception.ItemNotFoundException;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
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
