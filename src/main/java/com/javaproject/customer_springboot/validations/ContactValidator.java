package com.javaproject.customer_springboot.validations;

import com.javaproject.customer_springboot.dto.ContactDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactValidator implements 
ConstraintValidator<ContactValidation, ContactDTO> {

  public void initialize(ContactValidation contactValidation) {
  }

  public boolean isValid(ContactDTO contactField,
    ConstraintValidatorContext cxt) {
      return contactField.getContactResponsable() != null && 
		  contactField.getContactZone() != null && 
		  contactField.getContactNumber() != null && contactField.getContactNumber().matches("[0-9]+")
		  && (contactField.getContactNumber().length() > 8) && (contactField.getContactNumber().length() < 14);
  }

}
