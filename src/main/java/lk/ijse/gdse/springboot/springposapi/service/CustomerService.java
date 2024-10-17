package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.impl.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    void deleteCustomer(String customerId);

    CustomerResponse getSelectedCustomer(String customerId);

    List<CustomerDto> getAllCustomers();

    void updateCustomer(String customerId, CustomerDto customerDto);
}
