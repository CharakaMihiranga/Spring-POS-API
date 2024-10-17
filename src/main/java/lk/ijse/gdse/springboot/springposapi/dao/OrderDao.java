package lk.ijse.gdse.springboot.springposapi.dao;

import lk.ijse.gdse.springboot.springposapi.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Long> {
}
