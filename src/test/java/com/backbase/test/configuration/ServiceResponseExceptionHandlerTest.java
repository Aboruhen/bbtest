package com.backbase.test.configuration;

import junit.framework.TestCase;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServiceResponseExceptionHandlerTest extends TestCase {

    public void testCustomHandleNotFound() {
        ServiceResponseExceptionHandler serviceResponseExceptionHandler = new ServiceResponseExceptionHandler();
        ResponseEntity<String> responseEntity = serviceResponseExceptionHandler.customHandleNotFound(new RuntimeException("test"), null);


        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertEquals("test", responseEntity.getBody());
    }

}