package com.javaproject.customer_springboot.validations;

import com.javaproject.customer_springboot.dto.AddressDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements 
ConstraintValidator<AddressValidation, AddressDTO> {
	
	  public void initialize(AddressValidation addressValidation) {
	  }

	  public boolean isValid(AddressDTO addressField,
	    ConstraintValidatorContext cxt) {
	      return addressField.getStreetAddress() != null && 
			  addressField.getNumberAddress() != null && 
			  addressField.getCustomerNumber() != null &&  
			  addressField.getDistrictAddress() != null && 
			  addressField.getCityAddress() != null && 
			  addressField.getStateAddress() != null && 
			  addressField.getZipAddress() != null;
	  }
}
