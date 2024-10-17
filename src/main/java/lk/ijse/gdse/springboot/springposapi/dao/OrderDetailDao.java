package lk.ijse.gdse.springboot.springposapi.dao;

import lk.ijse.gdse.springboot.springposapi.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetailEntity, Long> {
    @Query()
    List<OrderDetailEntity> getOrderDetailEntitiesByOrderEntityId(Long orderId);
}
