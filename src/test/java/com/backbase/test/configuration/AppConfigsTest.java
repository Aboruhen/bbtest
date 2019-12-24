package com.backbase.test.configuration;

import junit.framework.TestCase;
import org.junit.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class AppConfigsTest extends TestCase {

    public void testRestTemplate() {

        AppConfigs appConfigs = new AppConfigs();
        RestTemplate restTemplate = appConfigs.restTemplate();
        Assert.assertFalse(Objects.isNull(restTemplate));

    }
}