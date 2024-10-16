package lk.ijse.gdse.springboot.springposapi.dao;

import lk.ijse.gdse.springboot.springposapi.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, String> {
}
