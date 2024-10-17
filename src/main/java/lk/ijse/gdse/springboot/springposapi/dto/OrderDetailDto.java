package lk.ijse.gdse.springboot.springposapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private Long id;
    @JsonIgnore
    private Long orderId;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;
}
