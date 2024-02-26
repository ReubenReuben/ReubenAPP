package webApp.ReubenApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Dto.UserDto;
import webApp.ReubenApp.Repositories.RegistrationImplRepo;
import webApp.ReubenApp.ServiceClasses.RegistrationClass;
import webApp.ReubenApp.ServiceClasses.UserServiceToImplement;

@Controller
public class RegistrationController {
    @Autowired
    private UserServiceToImplement userServiceToImplement;
    @Autowired
    private RegistrationImplRepo registrationImplRepo;

//    @PostMapping({"/sign-up"})
//    public ResponseEntity<?> createUser(@RequestBody(required = true)RegistrationDTO registrationDTO){
//        //valid by name
//        if(registrationImplRepo.hasUserWithName(registrationDTO.getName())){
//            return new ResponseEntity<>("user name already exists:  "
//                    + registrationDTO.getName(), HttpStatus.NOT_ACCEPTABLE);
//        }
//        UserDto createdUser = registrationImplRepo.createUser(registrationDTO);
//        if(createdUser==null){
//            return new ResponseEntity<>("User not created, try again later.", HttpStatus.BAD_REQUEST);
//
//        }
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
    @GetMapping("/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "registration.html";
    }
    @PostMapping("/sign-up")
       public String createUser(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO, Model model){

             if(userServiceToImplement.hasUserWithName((registrationDTO.getName()))){
        model.addAttribute("error", "User with name already exists: " + registrationDTO.getName());
        return "registration.html";
    }
//    public String createUser(@ModelAttribute("registrationDTO") RegistrationDTO registrationDTO, Model model){
//        if(registrationImplRepo.hasUserWithName((registrationDTO.getName()))){
//            model.addAttribute("error", "User with name already exists: " + registrationDTO.getName());
//            return "registration.html";
//        }
//        UserDto createdUser = registrationImplRepo.createUser(registrationDTO);
//        if (createdUser == null) {
//            model.addAttribute("error", "User not created, please try again later.");
//            return "registration.html";
//        }
        UserDto createdUser = userServiceToImplement.createUser(registrationDTO);
        if (createdUser == null) {
            model.addAttribute("error", "User not created, please try again later.");
            return "registration.html";
        }
        // Redirect to a success page or back to the registration form
    return "redirect:/login";
    }


}
