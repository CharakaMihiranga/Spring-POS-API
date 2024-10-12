package lk.ijse.gdse.springboot.springposapi.controller;

import lk.ijse.gdse.springboot.springposapi.dto.CustomerDto;
import lk.ijse.gdse.springboot.springposapi.exception.CustomerNotFoundException;
import lk.ijse.gdse.springboot.springposapi.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.springposapi.response.CustomerResponse;
import lk.ijse.gdse.springboot.springposapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Controller
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    //Save customer
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer (@RequestBody CustomerDto customer){
        System.out.println("CustomerEntity: " + customer);
        if (customer == null){
            logger.warn("Invalid request: Customer object is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            try {
                customerService.saveCustomer(customer);
                logger.info("Customer with ID: {} saved successfully", customer.getId());
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (DataPersistFailedException e) {
                logger.error("Failed to save customer: {}", customer, e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.error("Internal server error while saving customer: {}", customer, e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    //delete customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer( @PathVariable("customerId") String customerId){
        try{
            customerService.deleteCustomer(customerId);
            logger.info("Customer with ID: {} deleted successfully", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.warn("Customer with ID: {} not found for deletion", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error while deleting customer with ID: {}", customerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get customer by id
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getSelectedCustomer(@PathVariable("customerId") String customerId) {
        return customerService.getSelectedCustomer(customerId);
    }
    // get all customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    // update customer
    @PatchMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto){
        try{
            if (customerDto == null && (customerId == null || customerId.isEmpty())){
                logger.warn("Invalid request: Missing customer ID or customer data");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.updateCustomer(customerId, customerDto);
            logger.info("Customer with ID: {} updated successfully", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.warn("Customer with ID: {} not found for update", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Internal server error while updating customer with ID: {}", customerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
