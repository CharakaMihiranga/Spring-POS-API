package lk.ijse.gdse.springboot.springposapi.util;

import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    // to convert customer dto to customer entity
    public Customer convertToEntity(CustomerDto customerDto ) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
