package vn.iostar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.iostar.service.UserService;

@Controller
@RequestMapping("admin")
public class UserController {

    @Autowired
    private UserService userSer;

    @GetMapping("/login")
    public String loginPage(Model model) {
    	System.out.print("chào");
        return "web/login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        if (userSer.checklogin(username, password)) {
            return "redirect:/admin/home";
        } else {
            return "web/login";
        }
    }
    
    @GetMapping("/home")
    public String homePage(Model model) {
        return "web/home";
    }
}
