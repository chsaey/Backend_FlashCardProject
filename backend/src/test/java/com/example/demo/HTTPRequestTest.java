package com.example.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTTPRequestTest {

    @LocalServerPort
    private int localPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testShouldReturnMessage() {
        assertThat(testRestTemplate.getForObject("http://localhost:" + localPort + "/hello", String.class)
                .contains("Hello World"));
    }
}
