package lk.ijse.gdse.springboot.springposapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private String id;
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
