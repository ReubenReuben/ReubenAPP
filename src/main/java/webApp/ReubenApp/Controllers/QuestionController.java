package webApp.ReubenApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webApp.ReubenApp.Dto.QuestionDto;
import webApp.ReubenApp.Dto.RegistrationDTO;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.ServiceClasses.QuestionServiceToImpl;
import webApp.ReubenApp.ServiceClasses.UserNotFoundException;
import webApp.ReubenApp.ServiceClasses.UserService;

import java.util.Optional;

@Controller
public class QuestionController {
    @Autowired
    private QuestionServiceToImpl questionServiceTo;
    @Autowired
    private UserService userService;

    @GetMapping("/questions")
    public String showQuestions(Model model) {
        // Implement logic to fetch and display existing questions
        return "questions.html";
    }

    @GetMapping("/ask")
    public String showForm(Model model) {
        model.addAttribute("questionDto", new QuestionDto());
        return "ask.html";
    }






@PostMapping("/ask")
    public String askAQuestion(@ModelAttribute("questionDto")QuestionDto questionDto, Model model) {
    QuestionDto createdQuestionDto = questionServiceTo.addQuestion(questionDto);
//if(createdQuestionDto ==null){
//    throw new UserNotFoundException("User not found with id: " + questionDto.getUserId());
//}else{
    if (createdQuestionDto == null) {
        model.addAttribute("error", "Something went wrong");
        return "error"; // Return error page
    }
//    model.addAttribute("questionDto", createdQuestionDto); // Add an empty QuestionDto for a new form
    model.addAttribute("createdQuestionDto", createdQuestionDto);
    return "redirect:/questions";

}



//    @PostMapping("/post")
//    public String postQuestion(@ModelAttribute("questionDto") QuestionDto questionDto) {
//
//        Optional<User> currentUser= userService.getCurrentUser();
//       questionDto.setUserId(currentUser.get().getId());
//       questionDto.setName(currentUser.get().getName());
//
//        questionServiceTo.addQuestion(questionDto);
//        // Redirect to the list of questions or another page as needed
//        return "redirect:/questions";
//    }

//@PostMapping("/ask")
//public String askAQuestion(@ModelAttribute("questionDto") QuestionDto questionDto, Model model) {
//
//        try {
//            QuestionDto createdQuestionDto = questionServiceTo.addQuestion(questionDto);
//        model.addAttribute("successMessage", "Question added successfully!");
//    } catch (Exception e) {
//        model.addAttribute("errorMessage", "Error adding the question. Please try again.");
//    }
//
//    return "redirect:/questions";
//}
//@PostMapping("/ask")
//public String askAQuestion(@ModelAttribute("questionDto") QuestionDto questionDto, Model model) {
//    try {
//        QuestionDto createdQuestionDto = questionServiceTo.addQuestion(questionDto);
//        model.addAttribute("successMessage", "Question added successfully!");
//        // You can use createdQuestionDto in further processing if needed
//    } catch (UserNotFoundException e) {
//        model.addAttribute("errorMessage", "User not found. Please check the user ID.");
//        // Log the exception
//
//    } catch (Exception e) {
//        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
//        // Log the exception
//
//    }
//
//    return "redirect:/questions";
//}
@GetMapping("/post")
public String showPostQuestionForm(Model model) {
    model.addAttribute("questionDto", new QuestionDto());
    return "postQuestion";
}

}
