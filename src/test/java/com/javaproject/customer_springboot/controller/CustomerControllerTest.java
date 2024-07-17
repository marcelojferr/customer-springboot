package com.javaproject.customer_springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
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
import com.javaproject.customer_springboot.dto.AddressDTO;
import com.javaproject.customer_springboot.dto.ContactDTO;
import com.javaproject.customer_springboot.dto.CustomerDTO;
import com.javaproject.customer_springboot.dto.SegmentDTO;
import com.javaproject.customer_springboot.enums.CustomerStatus;
import com.javaproject.customer_springboot.service.CustomerService;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerServiceMock;

    @Mock
    protected static CustomerDTO customerResponseDTO, customerResquestDTO;

    @Mock
    protected static AddressDTO addressResponseDTO, addressResquestDTO;

    @Mock
    protected static Set<ContactDTO> contactSetResponseDTO, contactSetResquestDTO;

    @Mock
    protected static ContactDTO contactResponseDTO, contactRequestDTO;

    @Mock
    protected static SegmentDTO segmentResponseDTO, segmentResquestDTO;
    
    
    
	@BeforeEach
    void setUp() {
    	
    	addressResquestDTO = AddressDTO.builder()
    		.streetAddress("Teste")
    		.numberAddress("Teste")
    		.districtAddress("Teste")
    		.cityAddress("Teste")
    		.stateAddress("Teste")
    		.zipAddress("Teste")
    		.build();
    	
    	addressResponseDTO = AddressDTO.builder()
    		.id(1L)
    		.streetAddress("Teste")
    		.numberAddress("Teste")
    		.districtAddress("Teste")
    		.cityAddress("Teste")
    		.stateAddress("Teste")
    		.zipAddress("Teste")
    		.build();
    	
    	contactRequestDTO = ContactDTO.builder()
    		.contactNumber("Teste")
    		.contactResponsable("Teste")
    		.contactZone("Teste")
    		.contactZone("Teste")
    		.build();
    	
    	contactResponseDTO = ContactDTO.builder()
    		.id(1L)
    		.contactNumber("Teste")
    		.contactResponsable("Teste")
    		.contactZone("Teste")
    		.contactZone("Teste")
    		.build();
    	
    	segmentResquestDTO = SegmentDTO.builder()
    		.segmentDescription("Teste")
    		.segmentPercentualValue("Teste")
    		.build();
    	
    	segmentResponseDTO = SegmentDTO.builder()
    		.id(1L)
    		.segmentDescription("Teste")
    		.segmentPercentualValue("Teste")
    		.build();
    	
    	contactSetResquestDTO.add(contactRequestDTO);

    	contactSetResponseDTO.add(contactResponseDTO);
    	
    	customerResquestDTO = CustomerDTO.builder()
    		.customerName("Teste")
    		.customerFantasyName("Teste")
    		.customerNumber("123456789010101")
    		.segment(segmentResquestDTO)
    		.address(addressResquestDTO)
    		.contact(contactSetResquestDTO)
    		.customerStatus(CustomerStatus.ATIVO)
            .build();
    	
    	customerResponseDTO = CustomerDTO.builder()
    		.id(1L)
    		.customerName("Teste")
    		.customerFantasyName("Teste")
    		.customerNumber("123456789010101")
    		.segment(segmentResponseDTO)
    		.address(addressResponseDTO)
    		.contact(contactSetResponseDTO)
    		.customerStatus(CustomerStatus.ATIVO)
            .build();
    }
    
    
    @Test
    @DisplayName("Returns list of customer")
    void listCustomerTest() {

        BDDMockito.when(customerServiceMock.listCustomer())
        		.thenReturn(List.of(customerResponseDTO));

        ResponseEntity<List<CustomerDTO>> customerDTO = customerController
        		.listCustomer();
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),List.of(customerResponseDTO));
        assertEquals(customerDTO.getBody().get(0),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(customerDTO.getBody().get(0)).isEqualTo(customerResponseDTO);

        Assertions.assertThat(customerDTO.getBody().get(0).toString()).isEqualTo(customerResponseDTO.toString());
    }
    
    @Test
    @DisplayName("Returns list of customer")
    void listCustomerActiveTest() {

        BDDMockito.when(customerServiceMock.listActiveCustomer())
        		.thenReturn(List.of(customerResponseDTO));

        ResponseEntity<List<CustomerDTO>> customerDTO = customerController
        		.listActiveCustomer();
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),List.of(customerResponseDTO));
        assertEquals(customerDTO.getBody().get(0),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(customerDTO.getBody().get(0)).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of customer Inactive")
    void listCustomerInactiveTest() {

        BDDMockito.when(customerServiceMock.listInactiveCustomer())
        		.thenReturn(List.of(customerResponseDTO));

        ResponseEntity<List<CustomerDTO>> customerDTO = customerController
        		.listInactiveCustomer();
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),List.of(customerResponseDTO));
        assertEquals(customerDTO.getBody().get(0),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(customerDTO.getBody().get(0)).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of customer pageable")
    void listCustomerPageableTest() {

        BDDMockito.when(customerServiceMock.listCustomerPageable(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        		.thenReturn(new PageImpl<>(List.of(customerResponseDTO)));

        ResponseEntity<Page<CustomerDTO>> customerDTO = customerController
        		.listCustomerPageable(0, 10);
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody().getContent(),List.of(customerResponseDTO));

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(customerDTO.getBody().getContent()).isEqualTo(List.of(customerResponseDTO));
    }
    
    @Test
    @DisplayName("Returns list of customer status pageable")
    void listCustomerStatusPageableTest() {

        BDDMockito.when(customerServiceMock.listCustomerByStatusPageable(
        		ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        		.thenReturn(new PageImpl<>(List.of(customerResponseDTO)));

        ResponseEntity<Page<CustomerDTO>> customerDTO = customerController
        		.listCustomerStatusPageable("ATIVO", 0, 10);
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody().getContent(),List.of(customerResponseDTO));

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isNotEmpty().hasSize(1);
        Assertions.assertThat(customerDTO.getBody().getContent()).isEqualTo(List.of(customerResponseDTO));
    }
    
    @Test
    @DisplayName("Find by customer id")
    void findCustomerIdTest() {

        BDDMockito.when(customerServiceMock.findCustomer(1L))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.findCustomer(1L);
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Find by customer number")
    void findCustomerNumberTest() {

        BDDMockito.when(customerServiceMock.findCustomerNumber(ArgumentMatchers.anyString()))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.findCustomerNumber(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Find by customer name")
    void findByCustomerNameTest() {

        BDDMockito.when(customerServiceMock.findCustomerName(ArgumentMatchers.anyString()))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.findCustomerName(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Find by customer fantasy name")
    void findByCustomerFantasyNameTest() {

        BDDMockito.when(customerServiceMock.findCustomerFantasyName(ArgumentMatchers.anyString()))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.findCustomerFantasyName(ArgumentMatchers.anyString());
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Create customer")
    void createCustomerTest() {

        BDDMockito.when(customerServiceMock.createCustomer(customerResquestDTO))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.saveCustomer(customerResquestDTO);
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Update customer")
    void updateCustomerTest() {

        BDDMockito.when(customerServiceMock.updateCustomer(customerResquestDTO))
                .thenReturn(customerResponseDTO);

        ResponseEntity<CustomerDTO> customerDTO = customerController
        		.updateCustomer(customerResquestDTO);
        
        assertEquals(HttpStatus.OK, customerDTO.getStatusCode());
        assertEquals(customerDTO.getBody(),customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO.getBody()).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Delete customer")
    void deleteCustomerTest() {

        ResponseEntity<Void> status = customerController
        		.deleteCustomer(1L);

        Assertions.assertThatCode(() -> customerServiceMock.deleteCustomer(1L)).doesNotThrowAnyException();
        assertEquals(HttpStatus.NO_CONTENT, status.getStatusCode());
        Assertions.assertThat(status.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}