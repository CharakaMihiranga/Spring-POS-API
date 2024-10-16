package lk.ijse.gdse.springboot.springposapi.dto;

import lk.ijse.gdse.springboot.springposapi.response.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto implements SuperDto, ItemResponse {
    private String code;
    private String name;
    private String itemPic;
    private String description;
    private double price;
    private int qtyOnHand;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
