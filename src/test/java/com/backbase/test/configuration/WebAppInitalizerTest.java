package com.backbase.test.configuration;

import junit.framework.TestCase;
import org.junit.Assert;

public class WebAppInitalizerTest extends TestCase {

    public void testGetRootConfigClasses() {
        WebAppInitalizer webAppInitalizer = new WebAppInitalizer();

        Assert.assertArrayEquals(new Class<?>[]{WebAppInitalizer.class}, webAppInitalizer.getRootConfigClasses());
    }

    public void testGetServletConfigClasses() {
        WebAppInitalizer webAppInitalizer = new WebAppInitalizer();

        Assert.assertArrayEquals(new Class[0], webAppInitalizer.getServletConfigClasses());
    }

    public void testGetServletMappings() {
        WebAppInitalizer webAppInitalizer = new WebAppInitalizer();

        Assert.assertArrayEquals(new String[]{"/*"}, webAppInitalizer.getServletMappings());
    }

}