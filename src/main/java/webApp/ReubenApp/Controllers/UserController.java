package webApp.ReubenApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
  @RequestMapping("/")
    public String home(){
        return "home.html";
    }
    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }
    @RequestMapping("/logout")
    public String logout(){
        return "logout.html";
    }

}
