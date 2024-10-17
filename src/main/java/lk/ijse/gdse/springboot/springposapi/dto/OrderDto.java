package lk.ijse.gdse.springboot.springposapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String customerId;
    private List<OrderDetailDto> orderDetailDtos;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal discount;
    private BigDecimal balance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

}
