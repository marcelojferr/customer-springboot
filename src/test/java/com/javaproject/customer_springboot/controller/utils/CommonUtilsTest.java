package com.javaproject.customer_springboot.controller.utils;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.javaproject.customer_springboot.utils.CommonUtils;

@ExtendWith(SpringExtension.class)
public class CommonUtilsTest {

    @InjectMocks
    private CommonUtils commonUtils;
    
    @Test
    @DisplayName("Returns data format")
    void formatLocalDateTimeToDatebaseStyleTest() {
    	
    	 String response = commonUtils.formatLocalDateTimeToDatebaseStyle(LocalDateTime.now());
    	 
         Assertions.assertThat(response).isNotNull();
         Assertions.assertThat(response).isEqualTo(response);
    }
}
