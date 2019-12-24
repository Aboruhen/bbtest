package com.backbase.test.configuration;

import junit.framework.TestCase;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Objects;

public class SwaggerConfigsTest extends TestCase {

    public void testApi() {
        SwaggerConfigs swaggerConfigs = new SwaggerConfigs();
        Docket api = swaggerConfigs.api();

        assertFalse(Objects.isNull(api));
    }

    public void testAddResourceHandlers() {
        SwaggerConfigs swaggerConfigs = new SwaggerConfigs();
        ResourceHandlerRegistry resourceHandlerRegistry = Mockito.mock(ResourceHandlerRegistry.class);

        ResourceHandlerRegistration swaggerUiRegistration = Mockito.mock(ResourceHandlerRegistration.class);
        ResourceHandlerRegistration webjars = Mockito.mock(ResourceHandlerRegistration.class);
        Mockito.when(resourceHandlerRegistry.addResourceHandler("swagger-ui.html"))
                .thenReturn(swaggerUiRegistration);

        Mockito.when(resourceHandlerRegistry.addResourceHandler("/webjars/**"))
                .thenReturn(webjars);

        //when
        swaggerConfigs.addResourceHandlers(resourceHandlerRegistry);

        Mockito.verify(swaggerUiRegistration).addResourceLocations("classpath:/META-INF/resources/");
        Mockito.verify(webjars).addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}