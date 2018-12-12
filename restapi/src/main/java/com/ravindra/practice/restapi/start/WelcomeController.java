package com.ravindra.practice.restapi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    WelcomeService service;

    @Autowired
    BasicConfiguration configuration;

    @RequestMapping("/welcome")
    public String welcome(){
        return service.retrieveWelcomeMessage();
    }


    @RequestMapping("/config")
    public Map config(){
        Map map = new HashMap();
        map.put("message", configuration.getMessage());
        map.put("number", configuration.getNumber());
        map.put("value", configuration.isValue());
        return map;
    }

}

@Component
class WelcomeService{

    @Value("${welcome.message}")
    public String welcomeMessage;
    public String retrieveWelcomeMessage(){
        return welcomeMessage;
//        return "This is great start, I will complete dfaddeostoday";
    }
}
