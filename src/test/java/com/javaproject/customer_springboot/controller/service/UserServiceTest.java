package com.javaproject.customer_springboot.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.UserDTO;
import com.javaproject.customer_springboot.enums.UserStatus;
import com.javaproject.customer_springboot.exception.UserException;
import com.javaproject.customer_springboot.model.User;
import com.javaproject.customer_springboot.repository.UserRepository;
import com.javaproject.customer_springboot.service.EmailService;
import com.javaproject.customer_springboot.service.UserService;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

	@InjectMocks
    private UserService userService;
    
    @Mock
    private UserRepository userRepositoryMock;

    @Mock
	private EmailService emailService;
    
    @Mock
    private static UserDTO userResponseDTO, userRequestDTO;
    
    @Mock
    private static User userResponse, userRequest;

	@BeforeEach
    void setUp() {
    	
		userRequestDTO = UserDTO.builder()
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
    		.userEmail("test@test")
    		.userStatus(UserStatus.ATIVO)
            .build();
			
		userResponseDTO = UserDTO.builder()
    		.id(1L)
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
    		.userEmail("test@test")
    		.userStatus(UserStatus.ATIVO)
            .build();
    	
    	userRequest = User.builder()
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
    		.userEmail("test@test")
    		.userStatus(UserStatus.ATIVO)
            .build();
    	
    	userResponse = User.builder()
    		.id(1L)
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
    		.userEmail("test@test")
    		.userStatus(UserStatus.ATIVO)
            .build();
    }
    
    @Test
    @DisplayName("Returns list of user")
    void listUserServiceTest() {

        BDDMockito.when(userRepositoryMock.findAll())
			.thenReturn(List.of(userResponse));

        List<UserDTO> user = userService.listUser();
        
        assertEquals(user, List.of(userResponseDTO));
        assertEquals(user.get(0),userResponseDTO);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isNotEmpty().hasSize(1);
        Assertions.assertThat(user.get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user active")
    void listUserActiveServiceTest() {

        BDDMockito.when(userRepositoryMock.findByStatusAtivo())
			.thenReturn(List.of(userResponse));

        List<UserDTO> user = userService.listActiveUser();

        assertEquals(user, List.of(userResponseDTO));
        assertEquals(user.get(0),userResponseDTO);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isNotEmpty().hasSize(1);
        Assertions.assertThat(user.get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user Inactive")
    void listUserInactiveServiceTest() {

        BDDMockito.when(userRepositoryMock.findByStatusInativo())
			.thenReturn(List.of(userResponse));

        List<UserDTO> user = userService.listInactiveUser();

        assertEquals(user, List.of(userResponseDTO));
        assertEquals(user.get(0),userResponseDTO);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isNotEmpty().hasSize(1);
        Assertions.assertThat(user.get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user pageable")
    void listUserPageableServiceTest() {

        BDDMockito.when(userRepositoryMock.findAll())
			.thenReturn(List.of(userResponse));

        Page<UserDTO> user = userService.listUserPageable(0, 10);

        assertEquals(user.getContent(),List.of(userResponseDTO));

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isNotEmpty().hasSize(1);
        Assertions.assertThat(user.getContent()).isEqualTo(List.of(userResponseDTO));
    }
    
    @Test
    @DisplayName("Returns list of user status pageable")
    void listUserStatusPageableServiceTest() {

        BDDMockito.when(userRepositoryMock
    		.findByStatus(ArgumentMatchers.anyString(), ArgumentMatchers.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(userResponse)));

        Page<UserDTO> user = userService.listUserByStatusPageable("ATIVO", 0, 10);

        assertEquals(user.getContent(),List.of(userResponseDTO));

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isNotEmpty().hasSize(1);
        Assertions.assertThat(user.getContent()).isEqualTo(List.of(userResponseDTO));
    }
    
    
    @Test
    @DisplayName("Find by user id")
    void findUserServiceTest() {

        BDDMockito.when(userRepositoryMock.findById(1L))
            .thenReturn(Optional.of(userResponse));

        UserDTO userDTO = userService
    		.findUser(1L);
        
        assertEquals(userDTO, userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Find by user name")
    void findByUserNameServiceTest() {

        BDDMockito.when(userRepositoryMock.findByUserName(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.of(userResponse));

	    UserDTO userDTO = userService
			.findUserName(ArgumentMatchers.anyString());
	    
	    assertEquals(userDTO, userResponseDTO);
	
	    Assertions.assertThat(userDTO).isNotNull();
	    Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Find by user login")
    void findByUserLoginServiceTest() {

        BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.of(userResponse));
	
	    UserDTO userDTO = userService
			.findUserLogin(ArgumentMatchers.anyString());
	    
	    assertEquals(userDTO, userResponseDTO);
	
	    Assertions.assertThat(userDTO).isNotNull();
	    Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Find by user email")
    void findUserEmailServiceTest() {
        
    	BDDMockito.when(userRepositoryMock.findByUserEmailContainingIgnoreCase(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.of(userResponse));
	
	    UserDTO userDTO = userService
			.findUserEmail(ArgumentMatchers.anyString());
	    
	    assertEquals(userDTO, userResponseDTO);
	
	    Assertions.assertThat(userDTO).isNotNull();
	    Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
	}
    
    @Test
    @DisplayName("Find by user email and password")
    void findUserEmailPassowrdServiceTest() {

        BDDMockito.when(userRepositoryMock
    		.findByUserEmailAndPasswordContainingIgnoreCase(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.of(userResponse));
	
	    UserDTO userDTO = userService
			.findUserEmailPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
	    
	    assertEquals(userDTO, userResponseDTO);
	
	    Assertions.assertThat(userDTO).isNotNull();
	    Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Create user")
    void createUserServiceTest() {
       
    	BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());
    	
        BDDMockito.when(userRepositoryMock.save(userRequest))
            .thenReturn(userResponse);
        
        BDDMockito.when(emailService.enviarEmailTexto(userRequestDTO.getUserEmail(), 
        		"Teste", "Teste")).thenReturn(ArgumentMatchers.anyString());

        UserDTO userDTO = userService
    		.createUser(userRequestDTO);
        
        assertEquals(userDTO, userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Update user")
    void updateUserServiceTest() {
    	
    	BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
    		.thenReturn(Optional.of(userResponse));
    	
        BDDMockito.when(userRepositoryMock.save(userRequest))
	        .thenReturn(userResponse);
		
		UserDTO userDTO = userService
				.updateUser(userRequestDTO);
		
		assertEquals(userDTO, userResponseDTO);
		
		Assertions.assertThat(userDTO).isNotNull();
		Assertions.assertThat(userDTO).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Delete user")
    void deleteUserServiceTest() {

        BDDMockito.when(userRepositoryMock.findById(1L))
        	.thenReturn(Optional.of(userResponse));

    	BDDMockito.doNothing()
    		.when(userRepositoryMock).delete(userRequest);

        Assertions.assertThatCode(() -> userService.deleteUser(1L)).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("Create user exception")
    void createUserServiceExceptionTest() {
       
    	BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.of(userResponse));

    	Assertions.assertThatExceptionOfType(UserException.class)
        	.isThrownBy(() -> this.userService.createUser(userRequestDTO));
    	
        Assertions.assertThatThrownBy(() -> this.userService.createUser(userRequestDTO))
            .isInstanceOf(UserException.class);
    }
    
    @Test
    @DisplayName("Update user exception")
    void updateUserServiceExceptionTest() {

    	BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());
    	
        Assertions.assertThatExceptionOfType(UserException.class)
        	.isThrownBy(() -> this.userService.updateUser(userRequestDTO));
    	
        Assertions.assertThatThrownBy(() -> this.userService.updateUser(userRequestDTO))
            .isInstanceOf(UserException.class);
    }
    

    @Test
    @DisplayName("Delete user exception")
    void deleteUserServiceExceptionTest() {

        BDDMockito.when(userRepositoryMock.findById(1L))
            .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService.deleteUser(1L));
	
        Assertions.assertThatThrownBy(() -> this.userService.deleteUser(1L))
        	.isInstanceOf(UserException.class);
    }
    
    @Test
    @DisplayName("Find by user id exception")
    void findUserServiceExceptionTest() {

        BDDMockito.when(userRepositoryMock.findById(1L))
            .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService.findUser(1L));
	
        Assertions.assertThatThrownBy(() -> this.userService.findUser(1L))
        	.isInstanceOf(UserException.class);
    }
    
    @Test
    @DisplayName("Find by user name exception")
    void findByUserNameServiceExceptionTest() {

        BDDMockito.when(userRepositoryMock.findByUserName(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService.findUserName(ArgumentMatchers.anyString()));
        
        Assertions.assertThatThrownBy(() -> this.userService.findUserName(ArgumentMatchers.anyString()))
        	.isInstanceOf(UserException.class);
    }

    @Test
    @DisplayName("Find by user login exception")
    void findByUserLoginServiceExceptionTest() {

        BDDMockito.when(userRepositoryMock.findByUserLogin(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService.findUserLogin(ArgumentMatchers.anyString()));
	
        Assertions.assertThatThrownBy(() -> this.userService.findUserLogin(ArgumentMatchers.anyString()))
        	.isInstanceOf(UserException.class);
    }

    @Test
    @DisplayName("Find by user email exception")
    void findUserEmailServiceExceptionTest() {
        
    	BDDMockito.when(userRepositoryMock.findByUserEmailContainingIgnoreCase(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService.findUserEmail(ArgumentMatchers.anyString()));
	
        Assertions.assertThatThrownBy(() -> this.userService.findUserEmail(ArgumentMatchers.anyString()))
        	.isInstanceOf(UserException.class);
	}
    
    @Test
    @DisplayName("Find by user email and password exception")
    void findUserEmailPassowrdServiceExceptionTest() {

        BDDMockito.when(userRepositoryMock
    		.findByUserEmailAndPasswordContainingIgnoreCase(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(UserException.class)
    		.isThrownBy(() -> this.userService
			.findUserEmailPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()));
	
        Assertions.assertThatThrownBy(() -> this.userService
    		.findUserEmailPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
        	.isInstanceOf(UserException.class);
    }

}