package com.ravindra.practice.myfirstwebapp.login;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    public boolean isValid(String username,String password){
        return username.equals("in28Minutes") && password.equals("ravi");
    }
}
