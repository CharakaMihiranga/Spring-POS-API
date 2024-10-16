package lk.ijse.gdse.springboot.springposapi.controller;

import lk.ijse.gdse.springboot.springposapi.dto.ItemDto;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.exception.ItemNotFoundException;
import lk.ijse.gdse.springboot.springposapi.response.ItemResponse;
import lk.ijse.gdse.springboot.springposapi.service.ItemService;
import lk.ijse.gdse.springboot.springposapi.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    //Save item
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveItem(
            @RequestPart("itemName") String itemName,
            @RequestPart("desc") String description,
            @RequestPart("price") String price,
            @RequestPart("qtyOnHand") String qtyOnHand,
            @RequestPart("itemPic") MultipartFile itemPic
    ){
        if (itemName == null || description == null || price == null || qtyOnHand == null || itemPic == null){
            logger.warn("Invalid request: Item object is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                String base64ItemPic = AppUtil.toBase64Pic(itemPic);
                ItemDto itemDto = new ItemDto();
                itemDto.setName(itemName);
                itemDto.setItemPic(base64ItemPic);
                itemDto.setDescription(description);
                itemDto.setPrice(Double.parseDouble(price));
                itemDto.setQtyOnHand(Integer.parseInt(qtyOnHand));
                //Send item to  service layer
                itemService.saveItem(itemDto);
                logger.info("Item with name: {} saved successfully", itemDto.getName());
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to save item: {}", itemName, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Internal server error while saving item: {}", itemName, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    // delete item
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String itemId){
        try {
            itemService.deleteItem(itemId);
            logger.info("Item with ID: {} deleted successfully", itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            logger.warn("Item with ID: {} not found for deletion", itemId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error while deleting item with ID: {}", itemId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get item by code
    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemResponse getItem(@PathVariable("itemId")String itemId){
        return itemService.getItem(itemId);
    }
    // update item
    @PatchMapping(value = "/{itemCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateItem(
            @PathVariable("itemCode") String itemCode,
            @RequestPart("itemName") String itemName,
            @RequestPart("desc") String description,
            @RequestPart("price") String price,
            @RequestPart("qtyOnHand") String qtyOnHand,
            @RequestPart("itemPic") MultipartFile itemPic
    ){
            try {
                String base64ItemPic = AppUtil.toBase64Pic(itemPic);
                ItemDto itemDto = new ItemDto();
                itemDto.setCode(itemCode);
                itemDto.setName(itemName);
                itemDto.setItemPic(base64ItemPic);
                itemDto.setDescription(description);
                itemDto.setPrice(Double.parseDouble(price));
                itemDto.setQtyOnHand(Integer.parseInt(qtyOnHand));
                //Send item to  service layer
                itemService.updateItem(itemDto);
                logger.info("Item with code: {} updated successfully", itemDto.getCode());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (ItemNotFoundException e) {
                logger.error("Item with CODE: {} not found for update", itemCode, e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                logger.error("Internal server error while updating item: {}", itemCode, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    // get all items
    @GetMapping(value = "allitems", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

}
