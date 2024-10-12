package lk.ijse.gdse.springboot.springposapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String id;
    private String customerId;
    private double totalAmount;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<OrderDetailDto> orderDetails;
}
