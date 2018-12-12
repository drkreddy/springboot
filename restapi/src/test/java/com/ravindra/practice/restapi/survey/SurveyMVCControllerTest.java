package com.ravindra.practice.restapi.survey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyMVCControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    public void testMethod() throws Exception {
        Question question = new Question("Question1","WhoamI","ravi", Arrays.asList("Ravi","Ramu","Raju","Srinu"));
        when(surveyService.retrieveQuestion(Mockito.anyString(),Mockito.anyString())).thenReturn(question);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":\"Question1\",\"description\":\"WhoamI\",\"correctAnswer\":\"ravi\",\"options\":[\"Ravi\",\"Ramu\",\"Raju\",\"Srinu\"]}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }

    @Test
    public void testAddMethod() throws Exception {
        Question question = new Question("Question1","WhoamI","ravi", Arrays.asList("Ravi","Ramu","Raju","Srinu"));
               String questionJson = "{\"id\":\"Question1\",\"description\":\"WhoamI\",\"correctAnswer\":\"ravi\",\"options\":[\"Ravi\",\"Ramu\",\"Raju\",\"Srinu\"]}";
        when(surveyService.addQuestion(Mockito.anyString(),Mockito.any(Question.class))).thenReturn(question);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/surveys/Survey1/questions").accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        String expected = "{\"id\":\"Question1\",\"description\":\"WhoamI\",\"correctAnswer\":\"ravi\",\"options\":[\"Ravi\",\"Ramu\",\"Raju\",\"Srinu\"]}";

//        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

        assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
        assertEquals("http://localhost/surveys/Survey1/questions/Question1",result.getResponse().getHeader(HttpHeaders.LOCATION));
    }
}
