package com.ravindra.practice.restapi.survey;

import com.ravindra.practice.restapi.RestapiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {

    @LocalServerPort
    private int port;
    @Test
    public void test(){
        System.out.println("TEST SERVER PORT:-"+ port);

        String url="http://localhost:"+port
                +"/surveys/Survey1/questions/Question1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<String>(null, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity.getBody());
//        fail("Fail the test");
    }

    @Test
    public void testAddQuestion(){
        String url="http://localhost:"+port
                +"/surveys/Survey1/questions";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Question question = new Question("Question12","Who am I?","ravi",Arrays.asList("ravi","raj","ram"));
        HttpEntity entity = new HttpEntity<Question>(question, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        assertThat(responseEntity.getHeaders().get(HttpHeaders.LOCATION).get(0),containsString("/surveys/Survey1/questions"));

    }
}