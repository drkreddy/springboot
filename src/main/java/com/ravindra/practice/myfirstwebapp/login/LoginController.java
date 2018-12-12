package com.ravindra.practice.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("name")
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @RequestMapping(value="/login",method = RequestMethod.GET)
//    @ResponseBody
    public String loginMessasge(){
        return "login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
//    @ResponseBody
    public String welcomeMessasge(ModelMap model, @RequestParam String name,@RequestParam String password){
        if(loginService.isValid(name,password)){
            model.put("name",name);
            return "welcome";
        }else{
            model.put("message","Invalid credentials");
            return "login";
        }
    }
}
