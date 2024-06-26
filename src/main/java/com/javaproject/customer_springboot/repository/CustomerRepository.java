package com.javaproject.customer_springboot.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.javaproject.customer_springboot.model.Customer;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByCustomerNumber(String number);
    
    public Optional<Customer> findByCustomerNameContainingIgnoreCase(String name);

    public Optional<Customer> findByCustomerFantasyNameContainingIgnoreCase(String name);
    
    @Query("SELECT a FROM Customer a WHERE a.customerStatus = 'ATIVO' ")
    public List<Customer> findByStatusAtivo();

    @Query("SELECT i FROM Customer i WHERE i.customerStatus = 'INATIVO' ")
    public List<Customer> findByStatusInativo();
    
    @Query("SELECT u FROM Customer u WHERE u.customerStatus = 'status' ")
    Page<Customer> findByStatus(@Param("status") String status, Pageable pageable);
}
