package com.javaproject.customer_springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.UserAuthenticationRequestDTO;
import com.javaproject.customer_springboot.dto.UserAuthenticationResponseDTO;
import com.javaproject.customer_springboot.dto.UserDTO;
import com.javaproject.customer_springboot.model.User;
import com.javaproject.customer_springboot.repository.UserRepository;
import com.javaproject.customer_springboot.service.UserAuthenticationService;
import com.javaproject.customer_springboot.service.UserService;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userServiceMock;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
	private UserAuthenticationService userAuthenticationService;

    @Mock
    protected static UserDTO userResponseDTO, userResquestDTO, userEmptyResquestDTO;
    
    @Mock
    protected static UserAuthenticationRequestDTO userAuthenticationRequestDTO, userEmptyAuthenticationRequestDTO;
    
    @Mock
    protected static UserAuthenticationResponseDTO userAuthenticationResponseDTO;

    @Mock
    protected static User userResponse, userRequest;
    
	@BeforeEach
    void setUp() {

		userResquestDTO = UserDTO.builder()
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
            .build();
    	
    	userResponseDTO = UserDTO.builder()
    		.id(1L)
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
            .build();
    	
    	userAuthenticationRequestDTO = UserAuthenticationRequestDTO.builder()
			.userLogin("Teste")
			.password("Teste")
			.build();
    	
    	userAuthenticationResponseDTO = UserAuthenticationResponseDTO.builder()
			.token("Token Teste")
			.build();
    	
    	userRequest = User.builder()
    		.userName("Teste")
    		.userLogin("123456789010101")
    		.password("Teste")
            .build();
    	
    	userResponse = User.builder()
        		.id(1L)
        		.userName("Teste")
        		.userLogin("123456789010101")
        		.password("Teste")
                .build();
    }
    
    @Test
    @DisplayName("Returns list of user")
    void listUserTest() {

        BDDMockito.when(userServiceMock.listUser())
        		.thenReturn(List.of(userResponseDTO));

        ResponseEntity<List<UserDTO>> userDTO = userController
        		.listUser();
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),List.of(userResponseDTO));
        assertEquals(userDTO.getBody().get(0),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(userDTO.getBody().get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user")
    void listUserActiveTest() {

        BDDMockito.when(userServiceMock.listActiveUser())
        		.thenReturn(List.of(userResponseDTO));

        ResponseEntity<List<UserDTO>> userDTO = userController
        		.listActiveUser();
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),List.of(userResponseDTO));
        assertEquals(userDTO.getBody().get(0),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(userDTO.getBody().get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user Inactive")
    void listUserInactiveTest() {

        BDDMockito.when(userServiceMock.listInactiveUser())
        		.thenReturn(List.of(userResponseDTO));

        ResponseEntity<List<UserDTO>> userDTO = userController
        		.listInactiveUser();
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),List.of(userResponseDTO));
        assertEquals(userDTO.getBody().get(0),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(userDTO.getBody().get(0)).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of user pageable")
    void listUserPageableTest() {

        BDDMockito.when(userServiceMock.listUserPageable(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        		.thenReturn(new PageImpl<>(List.of(userResponseDTO)));

        ResponseEntity<Page<UserDTO>> userDTO = userController
        		.listUserPageable(0, 10);
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody().getContent(),List.of(userResponseDTO));

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(userDTO.getBody().getContent()).isEqualTo(List.of(userResponseDTO));
    }
    
    @Test
    @DisplayName("Returns list of user status pageable")
    void listUserStatusPageableTest() {

        BDDMockito.when(userServiceMock.listUserByStatusPageable(
        		ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        		.thenReturn(new PageImpl<>(List.of(userResponseDTO)));

        ResponseEntity<Page<UserDTO>> userDTO = userController
        		.listUserStatusPageable("ATIVO", 0, 10);
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody().getContent(),List.of(userResponseDTO));

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(userDTO.getBody().getContent()).isEqualTo(List.of(userResponseDTO));
    }
    
    @Test
    @DisplayName("Find by user id")
    void findUserTest() {

        BDDMockito.when(userServiceMock.findUser(1L))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.findUser(1L);
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Find by user name")
    void findByUserNameTest() {

        BDDMockito.when(userServiceMock.findUserName(ArgumentMatchers.anyString()))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.findUserName(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Find by user login")
    void findByUserLoginTest() {

        BDDMockito.when(userServiceMock.findUserLogin(ArgumentMatchers.anyString()))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.findUserLogin(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Find by user email")
    void findUserEmailTest() {

        BDDMockito.when(userServiceMock.findUserEmail(ArgumentMatchers.anyString()))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.findUserEmail(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Find by user email and password")
    void findUserEmailPassowrdTest() {

        BDDMockito.when(userServiceMock.findUserEmailPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.findUserEmailPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Create user")
    void createUserTest() {

        BDDMockito.when(userServiceMock.createUser(userResquestDTO))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.saveUser(userResquestDTO);
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }
    
    @Test
    @DisplayName("Update user")
    void updateUserTest() {

        BDDMockito.when(userServiceMock.updateUser(userResquestDTO))
                .thenReturn(userResponseDTO);

        ResponseEntity<UserDTO> userDTO = userController
        		.updateUser(userResquestDTO);
        
        assertEquals(HttpStatus.OK, userDTO.getStatusCode());
        assertEquals(userDTO.getBody(),userResponseDTO);

        Assertions.assertThat(userDTO).isNotNull();
        Assertions.assertThat(userDTO.getBody()).isEqualTo(userResponseDTO);
    }

    @Test
    @DisplayName("Delete user")
    void deleteUserTest() {

        ResponseEntity<Void> status = userController
        		.deleteUser(1L);
        
        Assertions.assertThatCode(() -> userServiceMock.deleteUser(1L)).doesNotThrowAnyException();
        assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
        Assertions.assertThat(status.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    
    @Test
    @DisplayName("Login user")
    void loginUserTest() {

        BDDMockito.when(userAuthenticationService.login(userAuthenticationRequestDTO))
        .thenReturn(userAuthenticationResponseDTO);

		ResponseEntity<?> userDTO = userController
				.login(userAuthenticationRequestDTO);
		
		assertEquals(HttpStatus.OK, userDTO.getStatusCode());
		assertEquals(userDTO.getBody(),userAuthenticationResponseDTO);
		
		Assertions.assertThat(userDTO).isNotNull();
		Assertions.assertThat(userDTO.getBody()).isEqualTo(userAuthenticationResponseDTO);
    }
}