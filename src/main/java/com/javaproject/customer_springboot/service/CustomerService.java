package com.javaproject.customer_springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.javaproject.customer_springboot.dto.CustomerDTO;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.model.Customer;
import com.javaproject.customer_springboot.repository.CustomerRepository;
import org.springframework.data.domain.Sort;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
 
    public List<CustomerDTO> listCustomer(){
    	List<Customer> customer = customerRepository.findAll();
    	return customer.stream().map(CustomerDTO::new).toList();
    }
    
    public List<CustomerDTO> listActiveCustomer(){
    	List<Customer> customer = customerRepository.findByStatusAtivo();
    	return customer.stream().map(CustomerDTO::new).toList();
    }
    
    public List<CustomerDTO> listInactiveCustomer(){
    	List<Customer> customer = customerRepository.findByStatusInativo();
    	return customer.stream().map(CustomerDTO::new).toList();
    }
    
    public Page<CustomerDTO> listCustomerPageable(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "customerName");
        return new PageImpl<>(customerRepository.findAll()
        		.stream().map(CustomerDTO::new).toList(), pageRequest, size);
    }
    
    public Page<CustomerDTO> listCustomerByStatusPageable(String status, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "userName");
    	return customerRepository.findByStatus(status, pageRequest)
    			.map(CustomerDTO::new);
    }
    
    public CustomerDTO findCustomer(Long id) {
    	Customer customer = customerRepository.findById(id).get();
		return new CustomerDTO(customer);
    }
    
    public CustomerDTO findCustomerNumber(String number) throws CustomerException {
    	Optional<Customer> customer = customerRepository.findByCustomerNumber(number);
		return new CustomerDTO(customer.orElseThrow(
				() -> new CustomerException("Cliente não encontrado")));
    }
    
    public CustomerDTO findCustomerName(String name) throws CustomerException {
    	Optional<Customer> customer = customerRepository.findByCustomerNameContainingIgnoreCase(name);
		return new CustomerDTO(customer.orElseThrow(
				() -> new CustomerException("Cliente não encontrado")));
    }
    
    public CustomerDTO findCustomerFantasyName(String number) throws CustomerException {
    	Optional<Customer> customer = customerRepository.findByCustomerFantasyNameContainingIgnoreCase(number);
		return new CustomerDTO(customer.orElseThrow(
				() -> new CustomerException("Cliente não encontrado")));
    }
    
    public CustomerDTO createCustomer(CustomerDTO customerDTO) throws CustomerException {
        if(customerRepository.findByCustomerNumber(customerDTO.getCustomerNumber()) !=null) {
            throw new CustomerException("Este cliente já esta cadastrado: " + customerDTO.getCustomerNumber());
    	}
        
    	Customer customer = new Customer(customerDTO);
    	return new CustomerDTO(customerRepository.save(customer));
    }
    
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
    	Customer customer = new Customer(customerDTO);
    	return new CustomerDTO(customerRepository.save(customer));
    }
    
    public void deleteCustomer(Long id) {
    	Customer customer = customerRepository.findById(id).get();
    	customerRepository.delete(customer);
    }
}
