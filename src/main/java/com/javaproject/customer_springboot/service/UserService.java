package com.javaproject.customer_springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.javaproject.customer_springboot.dto.UserDTO;
import com.javaproject.customer_springboot.exception.UserException;
import com.javaproject.customer_springboot.model.User;
import com.javaproject.customer_springboot.repository.UserRepository;

@Service
public class UserService {

	@Value("${spring.mail.username}")
	private String remetente;
	
    @Autowired
    UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
    public List<UserDTO> listUser(){
    	List<User> user = userRepository.findAll();
    	return user.stream().map(UserDTO::new).toList();
    }
    
    public List<UserDTO> listActiveUser(){
    	List<User> user = userRepository.findByStatusAtivo();
    	return user.stream().map(UserDTO::new).toList();
    }
    
    public List<UserDTO> listInactiveUser(){
    	List<User> user = userRepository.findByStatusInativo();
    	return user.stream().map(UserDTO::new).toList();
    }
    
    public Page<UserDTO> listUserPageable(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "userName");
        return new PageImpl<>(userRepository.findAll()
        		.stream().map(UserDTO::new).toList(), pageRequest, size);
    }
    
    public Page<UserDTO>  listUserByStatusPageable(String status, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "userName");
    	return userRepository.findByStatus(status, pageRequest)
    			.map(UserDTO::new);
    }
    
    public UserDTO findUser(Long id) throws UserException {
		Optional<User> user = userRepository.findById(id);
    	return new UserDTO(user.orElseThrow(
				() -> new UserException("Usuário não encontrado")));
    }
    
    public UserDTO findUserLogin(String userLogin) throws UserException {
    	Optional<User> user = userRepository.findByUserLogin(userLogin);
		return new UserDTO(user.orElseThrow(
				() -> new UserException("Usuário não encontrado")));
    }
    
    public UserDTO findUserName(String name) throws UserException {
    	Optional<User> user = userRepository.findByUserName(name);
		return new UserDTO(user.orElseThrow(
				() -> new UserException("Usuário não encontrado")));
    }
    
    public UserDTO findUserEmail(String userEmail) throws UserException {
    	Optional<User> user = userRepository.findByUserEmailContainingIgnoreCase(userEmail);
		return new UserDTO(user.orElseThrow(
				() -> new UserException("Usuário não encontrado")));
    }

    public UserDTO findUserEmailPassword(String userEmail, String password) throws UserException {
    	Optional<User> user = userRepository.findByUserEmailAndPasswordContainingIgnoreCase(userEmail, password);
		return new UserDTO(user.orElseThrow(
				() -> new UserException("Usuário não encontrado")));
    }
    
    public UserDTO createUser(UserDTO userDTO) throws UserException {
        if(!userRepository.findByUserLogin(userDTO.getUserLogin()).isEmpty()) {
            throw new UserException("Este usuário já esta cadastrado: " + userDTO.getUserLogin());
    	}

		/**Enviar um email para verificar a conta**/
		emailService.enviarEmailTexto(userDTO.getUserEmail(), "Novo usuário cadastrado", 
			"Você está recebendo um email de cadastro o número para validação");
		
    	return new UserDTO(userRepository.save(new User(userDTO)));
    }
    
    public UserDTO updateUser(UserDTO userDTO) throws UserException {   	
        if(userRepository.findByUserLogin(userDTO.getUserLogin()).isEmpty()) {
            throw new UserException("Este usuário não esta cadastrado: " + userDTO.getUserLogin());
    	}
        
    	return new UserDTO(userRepository.save(new User(userDTO)));
    }
    
    public void deleteUser(Long id) throws UserException {
		Optional<User> user = userRepository.findById(id);
    	user.orElseThrow(
				() -> new UserException("Usuário não encontrado"));
    	userRepository.delete(user.get());
    }
}
