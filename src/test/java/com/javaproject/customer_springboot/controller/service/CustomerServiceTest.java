package com.javaproject.customer_springboot.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.dto.AddressDTO;
import com.javaproject.customer_springboot.dto.ContactDTO;
import com.javaproject.customer_springboot.dto.CustomerDTO;
import com.javaproject.customer_springboot.dto.SegmentDTO;
import com.javaproject.customer_springboot.enums.CustomerStatus;
import com.javaproject.customer_springboot.exception.CustomerException;
import com.javaproject.customer_springboot.model.Address;
import com.javaproject.customer_springboot.model.Contact;
import com.javaproject.customer_springboot.model.Customer;
import com.javaproject.customer_springboot.model.Segment;
import com.javaproject.customer_springboot.repository.CustomerRepository;
import com.javaproject.customer_springboot.service.CustomerService;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

	@InjectMocks
    private CustomerService customerService;
    
    @Mock
    private CustomerRepository customerRepositoryMock;
    
    @Mock
    private static CustomerDTO customerResponseDTO, customerRequestDTO;
    
    @Mock
    private static AddressDTO addressResponseDTO, addressResquestDTO;

    private static Set<ContactDTO> contactSetResponseDTO = new HashSet<>();
    
    private static Set<ContactDTO>  contactSetResquestDTO = new HashSet<>();

    @Mock
    private static ContactDTO contactResponseDTO, contactRequestDTO;

    @Mock
    private static SegmentDTO segmentResponseDTO, segmentResquestDTO;
    
    @Mock
    private static Customer customerResponse, customerRequest, customerRequest2;
    
    @Mock
    private static Address addressResponse, addressResquest;

    private static Set<Contact> contactSetResponse = new HashSet<>();
    
    private static Set<Contact> contactSetResquest = new HashSet<>();

    @Mock
    private static Contact contactResponse, contactRequest;

    @Mock
    private static Segment segmentResponse, segmentResquest;

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
		
		customerRequestDTO = CustomerDTO.builder()
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
    	
		addressResquest = Address.builder()
    		.streetAddress("Teste")
    		.numberAddress("Teste")
    		.districtAddress("Teste")
    		.cityAddress("Teste")
    		.stateAddress("Teste")
    		.zipAddress("Teste")
    		.customer(customerRequest)
    		.build();
    	
    	addressResponse = Address.builder()
    		.id(1L)
    		.streetAddress("Teste")
    		.numberAddress("Teste")
    		.districtAddress("Teste")
    		.cityAddress("Teste")
    		.stateAddress("Teste")
    		.zipAddress("Teste")
    		.customer(customerRequest)
    		.build();
    	
    	contactRequest = Contact.builder()
    		.contactNumber("Teste")
    		.contactResponsable("Teste")
    		.contactZone("Teste")
    		.contactZone("Teste")
    		.customer(customerRequest)
    		.build();
    	
    	contactResponse = Contact.builder()
    		.id(1L)
    		.contactNumber("Teste")
    		.contactResponsable("Teste")
    		.contactZone("Teste")
    		.contactZone("Teste")
    		.customer(customerRequest)
    		.build();
    	
    	segmentResquest = Segment.builder()
    		.segmentDescription("Teste")
    		.segmentPercentualValue("Teste")
    		.build();
    	
    	segmentResponse = Segment.builder()
    		.id(1L)
    		.segmentDescription("Teste")
    		.segmentPercentualValue("Teste")
    		.build();
    	
    	contactSetResquest.add(contactRequest);

    	contactSetResponse.add(contactResponse);
		
    	customerRequest = Customer.builder()
    		.customerName("Teste")
    		.customerFantasyName("Teste")
    		.customerNumber("123456789010101")
    		.segment(segmentResquest)
    		.address(addressResquest)
    		.contact(contactSetResquest)
    		.customerStatus(CustomerStatus.ATIVO)
            .build();

    	customerResponse = Customer.builder()
	   		.id(1L)
    		.customerName("Teste")
    		.customerFantasyName("Teste")
    		.customerNumber("123456789010101")
    		.segment(segmentResponse)
    		.address(addressResponse)
    		.contact(contactSetResponse)
    		.customerStatus(CustomerStatus.ATIVO)
            .build();
    }
	
    @Test
    @DisplayName("Returns list of customer")
    void listCustomerServiceTest() {

        BDDMockito.when(customerRepositoryMock.findAll())
			.thenReturn(List.of(customerResponse));

        List<CustomerDTO> customer = customerService.listCustomer();
        
        assertEquals(customer, List.of(customerResponseDTO));
        assertEquals(customer.get(0),customerResponseDTO);

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer).isNotEmpty().hasSize(1);
        Assertions.assertThat(customer.get(0)).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of customer active")
    void listCustomerActiveServiceTest() {

        BDDMockito.when(customerRepositoryMock.findByStatusAtivo())
			.thenReturn(List.of(customerResponse));

        List<CustomerDTO> customer = customerService.listActiveCustomer();

        assertEquals(customer, List.of(customerResponseDTO));
        assertEquals(customer.get(0),customerResponseDTO);

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer).isNotEmpty().hasSize(1);
        Assertions.assertThat(customer.get(0)).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of customer Inactive")
    void listCustomerInactiveServiceTest() {

        BDDMockito.when(customerRepositoryMock.findByStatusInativo())
			.thenReturn(List.of(customerResponse));

        List<CustomerDTO> customer = customerService.listInactiveCustomer();

        assertEquals(customer, List.of(customerResponseDTO));
        assertEquals(customer.get(0),customerResponseDTO);

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer).isNotEmpty().hasSize(1);
        Assertions.assertThat(customer.get(0)).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Returns list of customer pageable")
    void listCustomerPageableServiceTest() {

        BDDMockito.when(customerRepositoryMock.findAll())
			.thenReturn(List.of(customerResponse));

        Page<CustomerDTO> customer = customerService.listCustomerPageable(0, 10);

        assertEquals(customer.getContent(),List.of(customerResponseDTO));

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer).isNotEmpty().hasSize(1);
        Assertions.assertThat(customer.getContent()).isEqualTo(List.of(customerResponseDTO));
    }
    
    @Test
    @DisplayName("Returns list of customer status pageable")
    void listCustomerStatusPageableServiceTest() {

        BDDMockito.when(customerRepositoryMock
    		.findByStatus(ArgumentMatchers.anyString(), ArgumentMatchers.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(customerResponse)));

        Page<CustomerDTO> customer = customerService.listCustomerByStatusPageable("ATIVO", 0, 10);

        assertEquals(customer.getContent(),List.of(customerResponseDTO));

        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer).isNotEmpty().hasSize(1);
        Assertions.assertThat(customer.getContent()).isEqualTo(List.of(customerResponseDTO));
    }
    
    
    @Test
    @DisplayName("Find by customer id")
    void findCustomerServiceTest() {

        BDDMockito.when(customerRepositoryMock.findById(1L))
            .thenReturn(Optional.of(customerResponse));

        CustomerDTO customerDTO = customerService
    		.findCustomer(1L);
        
        assertEquals(customerDTO, customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Find by customer name")
    void findByCustomerNameServiceTest() {

        BDDMockito.when(customerRepositoryMock.findByCustomerNameContainingIgnoreCase(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.of(customerResponse));

	    CustomerDTO customerDTO = customerService
			.findCustomerName(ArgumentMatchers.anyString());
	    
	    assertEquals(customerDTO, customerResponseDTO);
	
	    Assertions.assertThat(customerDTO).isNotNull();
	    Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Find by customer fantasy name")
    void findByCustomerFantasyNameServiceTest() {

        BDDMockito.when(customerRepositoryMock.findByCustomerFantasyNameContainingIgnoreCase(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.of(customerResponse));
	
	    CustomerDTO customerDTO = customerService
			.findCustomerFantasyName(ArgumentMatchers.anyString());
	    
	    assertEquals(customerDTO, customerResponseDTO);
	
	    Assertions.assertThat(customerDTO).isNotNull();
	    Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Find by customer number")
    void findCustomerNumberServiceTest() {
        
    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.of(customerResponse));
	
	    CustomerDTO customerDTO = customerService
			.findCustomerNumber(ArgumentMatchers.anyString());
	    
	    assertEquals(customerDTO, customerResponseDTO);
	
	    Assertions.assertThat(customerDTO).isNotNull();
	    Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
	}
    
    @Test
    @DisplayName("Create customer")
    void createCustomerServiceTest() {
    	
    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());
    	
        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any()))
            .thenReturn(customerResponse);

        CustomerDTO customerDTO = customerService
    		.createCustomer(customerRequestDTO);
        
        assertEquals(customerDTO, customerResponseDTO);

        Assertions.assertThat(customerDTO).isNotNull();
        Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
    }
    
    @Test
    @DisplayName("Update customer")
    void updateCustomerServiceTest() {
    	
    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
    		.thenReturn(Optional.of(customerResponse));
    	
        BDDMockito.when(customerRepositoryMock.save(ArgumentMatchers.any()))
	        .thenReturn(customerResponse);
		
		CustomerDTO customerDTO = customerService
				.updateCustomer(customerRequestDTO);
		
		assertEquals(customerDTO, customerResponseDTO);
		
		Assertions.assertThat(customerDTO).isNotNull();
		Assertions.assertThat(customerDTO).isEqualTo(customerResponseDTO);
    }

    @Test
    @DisplayName("Delete customer")
    void deleteCustomerServiceTest() {

        BDDMockito.when(customerRepositoryMock.findById(1L))
        	.thenReturn(Optional.of(customerResponse));

    	BDDMockito.doNothing()
    		.when(customerRepositoryMock).delete(customerRequest);

        Assertions.assertThatCode(() -> customerService.deleteCustomer(1L)).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("Create customer exception")
    void createCustomerServiceExceptionTest() {
       
    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.of(customerResponse));

    	Assertions.assertThatExceptionOfType(CustomerException.class)
        	.isThrownBy(() -> this.customerService.createCustomer(customerRequestDTO));
    	
        Assertions.assertThatThrownBy(() -> this.customerService.createCustomer(customerRequestDTO))
            .isInstanceOf(CustomerException.class);
    }
    
    @Test
    @DisplayName("Update customer exception")
    void updateCustomerServiceExceptionTest() {

    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());
    	
        Assertions.assertThatExceptionOfType(CustomerException.class)
        	.isThrownBy(() -> this.customerService.updateCustomer(customerRequestDTO));
    	
        Assertions.assertThatThrownBy(() -> this.customerService.updateCustomer(customerRequestDTO))
            .isInstanceOf(CustomerException.class);
    }
    

    @Test
    @DisplayName("Delete customer exception")
    void deleteCustomerServiceExceptionTest() {

        BDDMockito.when(customerRepositoryMock.findById(1L))
            .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerException.class)
    		.isThrownBy(() -> this.customerService.deleteCustomer(1L));
	
        Assertions.assertThatThrownBy(() -> this.customerService.deleteCustomer(1L))
        	.isInstanceOf(CustomerException.class);
    }
    
    @Test
    @DisplayName("Find by customer id exception")
    void findCustomerServiceExceptionTest() {

        BDDMockito.when(customerRepositoryMock.findById(1L))
            .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerException.class)
    		.isThrownBy(() -> this.customerService.findCustomer(1L));
	
        Assertions.assertThatThrownBy(() -> this.customerService.findCustomer(1L))
        	.isInstanceOf(CustomerException.class);
    }
    
    @Test
    @DisplayName("Find by customer name exception")
    void findByCustomerNameServiceExceptionTest() {

        BDDMockito.when(customerRepositoryMock.findByCustomerNameContainingIgnoreCase(ArgumentMatchers.anyString()))
        	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerException.class)
    		.isThrownBy(() -> this.customerService.findCustomerName(ArgumentMatchers.anyString()));
        
        Assertions.assertThatThrownBy(() -> this.customerService.findCustomerName(ArgumentMatchers.anyString()))
        	.isInstanceOf(CustomerException.class);
    }

    @Test
    @DisplayName("Find by customer fantasy name exception")
    void findByCustomerFantasyNameServiceExceptionTest() {

        BDDMockito.when(customerRepositoryMock.findByCustomerFantasyNameContainingIgnoreCase(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerException.class)
    		.isThrownBy(() -> this.customerService.findCustomerFantasyName(ArgumentMatchers.anyString()));
	
        Assertions.assertThatThrownBy(() -> this.customerService.findCustomerFantasyName(ArgumentMatchers.anyString()))
        	.isInstanceOf(CustomerException.class);
    }

    @Test
    @DisplayName("Find by customer number exception")
    void findCustomerNumberServiceExceptionTest() {
        
    	BDDMockito.when(customerRepositoryMock.findByCustomerNumber(ArgumentMatchers.anyString()))
	    	.thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(CustomerException.class)
    		.isThrownBy(() -> this.customerService.findCustomerNumber(ArgumentMatchers.anyString()));
	
        Assertions.assertThatThrownBy(() -> this.customerService.findCustomerNumber(ArgumentMatchers.anyString()))
        	.isInstanceOf(CustomerException.class);
	}

}