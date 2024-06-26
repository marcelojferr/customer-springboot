package com.javaproject.customer_springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.javaproject.customer_springboot.model.User;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUserLogin(String userLogin);

    public Optional<User> findByUserName(String userName);
    
    public Optional<User> findByUserEmailContainingIgnoreCase(String userEmail);

    public Optional<User> findByUserEmailAndPasswordContainingIgnoreCase(String userEmail, String Password);

    @Query("SELECT u FROM User u WHERE u.userStatus = 'ATIVO' ")
    public List<User> findByStatusAtivo();
    
    @Query("SELECT u FROM User u WHERE u.userStatus = 'INATIVO' ")
    public List<User> findByStatusInativo();
    
    @Query("SELECT u FROM User u WHERE u.userStatus = 'status' ")
    Page<User> findByStatus(@Param("status") String status, Pageable pageable);
}
