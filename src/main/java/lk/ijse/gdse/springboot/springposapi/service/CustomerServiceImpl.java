package lk.ijse.gdse.springboot.springposapi.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.springboot.springposapi.dao.CustomerDao;
import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.entity.Customer;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.util.AppUtil;
import lk.ijse.gdse.springboot.springposapi.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
        customerDto.setId(AppUtil.createCustomerId());
        Customer savedCustomer = customerDao.save(mapping.convertToEntity(customerDto));
        if (savedCustomer == null && savedCustomer.getId() == null){
            throw new DataPersistFailedException("Can't save the customer");
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<Customer> selectedCustomerId = customerDao.findById(customerId);
        if (!selectedCustomerId.isPresent()) {
            throw new CustomerNotFoundException( "Customer not found" );
        } else {
            customerDao.deleteById(customerId);
        }
    }
}
