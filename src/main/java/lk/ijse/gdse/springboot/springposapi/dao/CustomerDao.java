package lk.ijse.gdse.springboot.springposapi.dao;

import lk.ijse.gdse.springboot.springposapi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity, String> {
}
