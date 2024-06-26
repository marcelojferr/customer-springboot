package com.javaproject.customer_springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaproject.customer_springboot.dto.CustomerDTO;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listCustomer(){
    	log.info("List All customer");
    	return ResponseEntity.ok(customerService.listCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable("id") Long id) {
    	log.info("Search customer by ID");
    	return ResponseEntity.ok(customerService.findCustomer(id));
    }

    @GetMapping("name/{customerName}")
    public ResponseEntity<CustomerDTO> findCustomerName(
    		@PathVariable("customerName") String customerName) throws CustomerException {
    	log.info("Search customer by Name");
    	return ResponseEntity.ok(customerService.findCustomerName(customerName));
    }

    @GetMapping("number/{customerNumber}")
    public ResponseEntity<CustomerDTO> findCustomerNumber(
    		@PathVariable("customerNumber") String customerNumber) throws CustomerException {
    	log.info("Search customer by Number");
    	return ResponseEntity.ok(customerService.findCustomerNumber(customerNumber));
    }

    @GetMapping("fantasy/{fantasyName}")
    public ResponseEntity<CustomerDTO> findCustomerFantasyName(
    		@PathVariable("fantasyName") String fantasyName) throws CustomerException {
    	log.info("Search customer by Fantasy Name");
    	return ResponseEntity.ok(customerService.findCustomerFantasyName(fantasyName));
    }
    
    @GetMapping("/pageable-all")
    public ResponseEntity<Page<CustomerDTO>> listCustomerPageable(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	log.info("List All customer pageable");
        return ResponseEntity.ok(customerService.listCustomerPageable(page, size));
    }

    @GetMapping("/pageable/{status}")
    public ResponseEntity<Page<CustomerDTO>> listCustomerStatusPageable(
    		@PathVariable("status") String status, 
	        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
	        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
    	log.info("List customer by status");
        return ResponseEntity.ok(customerService.listCustomerByStatusPageable(status, page, size));
    }
    
    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException{
    	log.info("Create customer");
    	return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }
    
    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO){
    	log.info("Update customer");
    	return ResponseEntity.ok(customerService.updateCustomer(customerDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id){
    	log.info("Delete customer");
    	customerService.deleteCustomer(id);
		return ResponseEntity.ok().build();
    }
}
