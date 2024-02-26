package webApp.ReubenApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webApp.ReubenApp.Dto.QuestionDto;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.UserRepo;

@Controller

public class ProfileController {
@Autowired
private UserRepo userRepo;

    @GetMapping("/profile/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        User user = userRepo.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }
}