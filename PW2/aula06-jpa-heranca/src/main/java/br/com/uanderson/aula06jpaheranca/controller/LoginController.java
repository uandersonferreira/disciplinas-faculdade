package br.com.uanderson.aula06jpaheranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }//cadastrar






}//class
