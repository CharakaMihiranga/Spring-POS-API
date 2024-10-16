package lk.ijse.gdse.springboot.springposapi.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.ItemDao;
import lk.ijse.gdse.springboot.springposapi.dto.ItemDto;
import lk.ijse.gdse.springboot.springposapi.entity.ItemEntity;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.util.AppUtil;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemDao itemDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        itemDto.setCode(AppUtil.generateId("ITEM"));
        ItemEntity savedItem = itemDao.save(mapping.map(itemDto, ItemEntity.class));
        if (savedItem == null && savedItem.getCode() == null) {
            throw new DataPersistFailedException("Can't save the item");
        }
    }
}
