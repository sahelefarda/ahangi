package com.example.ahangi;

import com.example.ahangi.service.AhangiMediaService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AhangiMediaServiceTest {

    @Test
    public void testServiceClassInstantiation() {
        AhangiMediaService service = new AhangiMediaService();
        assertNotNull("AhangiMediaService should be instantiable", service);
    }
}
