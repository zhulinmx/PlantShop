package com.controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {
	
	@RequestMapping("/main")
    public String toIndex(HttpServletRequest request,Model model){  
        return "main";  
    }

}
