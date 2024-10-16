package lk.ijse.gdse.springboot.springposapi.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.CustomerDao;
import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.entity.CustomerEntity;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.response.CustomerErrorResponse;
import lk.ijse.gdse.springboot.springposapi.response.CustomerResponse;
import lk.ijse.gdse.springboot.springposapi.util.AppUtil;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerDto.setId(AppUtil.generateId("CUS"));
        CustomerEntity savedCustomerEntity = customerDao.save(mapping.map(customerDto, CustomerEntity.class));
        if (savedCustomerEntity.getId() == null){
            throw new DataPersistFailedException("Can't save the customer");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> selectedCustomerId = customerDao.findById(customerId);
        if (!selectedCustomerId.isPresent()) {
            throw new CustomerNotFoundException( "CustomerEntity not found" );
        } else {
            customerDao.deleteById(customerId);
        }
    }

    @Override
    public CustomerResponse getSelectedCustomer(String customerId) {
       if (customerDao.existsById(customerId)){
            return mapping.map(customerDao.getById(customerId), CustomerDto.class);
       } else {
           return new CustomerErrorResponse(0, "CustomerEntity not found");
       }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mapping.mapList(customerDao.findAll(), CustomerDto.class);
    }

    @Override
    public void updateCustomer(String customerId, CustomerDto customerDto) {
        Optional<CustomerEntity> tmpCustomerById = customerDao.findById(customerId);
        if (!tmpCustomerById.isPresent()){
            throw new CustomerNotFoundException("CustomerEntity not found");
        } else {
            tmpCustomerById.get().setName(customerDto.getName());
            tmpCustomerById.get().setEmail(customerDto.getEmail());
            tmpCustomerById.get().setAddress(customerDto.getAddress());
        }
    }
}
