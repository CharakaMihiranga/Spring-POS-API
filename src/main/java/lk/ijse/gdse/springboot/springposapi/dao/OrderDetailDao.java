package lk.ijse.gdse.springboot.springposapi.dao;

import lk.ijse.gdse.springboot.springposapi.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetailEntity, Long> {
}
