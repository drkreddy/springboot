package com.ravindra.practice.myfirstwebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@SessionAttributes("name")
@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/list-todos")
    public String showTodos(ModelMap model){
        String name= (String) model.get("name");
        model.put("todos",todoService.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showaddTodo(ModelMap model){
        return "add-todo";
    }
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String desc){
        todoService.addTodo((String) model.get("name"), desc, new Date(),false );
        return "redirect:/list-todos";
    }
}
