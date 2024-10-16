package lk.ijse.gdse.springboot.springposapi.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.ItemDao;
import lk.ijse.gdse.springboot.springposapi.dto.ItemDto;
import lk.ijse.gdse.springboot.springposapi.entity.ItemEntity;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.exception.ItemNotFoundException;
import lk.ijse.gdse.springboot.springposapi.response.ItemErrorResponse;
import lk.ijse.gdse.springboot.springposapi.response.ItemResponse;
import lk.ijse.gdse.springboot.springposapi.util.AppUtil;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (savedItem.getCode() == null) {
            throw new DataPersistFailedException("Can't save the item");
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> selectedItem = itemDao.findById(itemId);
        if (!selectedItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemDao.deleteById(itemId);
        }
    }

    @Override
    public ItemResponse getItem(String itemId) {
        if (itemDao.existsById(itemId)) {
            return mapping.map(itemDao.getById(itemId), ItemDto.class);
        } else {
            return new ItemErrorResponse(0, "Item not found");
        }
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Optional<ItemEntity> tmpItem = itemDao.findById(itemDto.getCode());
        if (!tmpItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            tmpItem.get().setName(itemDto.getName());
            tmpItem.get().setItemPic(itemDto.getItemPic());
            tmpItem.get().setDescription(itemDto.getDescription());
            tmpItem.get().setPrice(itemDto.getPrice());
            tmpItem.get().setQtyOnHand(itemDto.getQtyOnHand());
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        return mapping.mapList(itemDao.findAll(), ItemDto.class);
    }
}
