package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.impl.ItemDto;
import lk.ijse.gdse.springboot.springposapi.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    void saveItem(ItemDto itemDto);

    void deleteItem(String itemId);

    ItemResponse getItem(String itemId);

    void updateItem(ItemDto itemDto);

    List<ItemDto> getAllItems();
}
