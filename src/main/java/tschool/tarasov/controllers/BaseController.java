package tschool.tarasov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class BaseController {

    @GetMapping("/customer")
    public String customerPage() {
        return "auth/customer";

    }
    @GetMapping("/employee")
    public String employeePage(){
        return "auth/employee";

    }
}
