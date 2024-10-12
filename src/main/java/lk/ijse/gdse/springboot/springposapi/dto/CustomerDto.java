package lk.ijse.gdse.springboot.springposapi.dto;

import lk.ijse.gdse.springboot.springposapi.response.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements SuperDto, CustomerResponse {
    private String id;
    private String name;
    private String email;
    private String address;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
