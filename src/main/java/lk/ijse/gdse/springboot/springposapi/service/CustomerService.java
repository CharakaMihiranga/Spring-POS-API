package lk.ijse.gdse.springboot.springposapi.service;

import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    void deleteCustomer(String customerId);
}
