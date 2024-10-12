package lk.ijse.gdse.springboot.springposapi.util;

import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    // to convert customer dto to customer entity
    public CustomerEntity convertToCustomerEntity(CustomerDto customerDto ) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
    // to convert customer entity to customer dto
    public CustomerDto convertToCustomerDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    public List<CustomerDto> convertCustomersToList(List<CustomerEntity> customerEntities) {
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDto>>(){}.getType());
    }
}
