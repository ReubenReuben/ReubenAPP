package webApp.ReubenApp.ServiceClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webApp.ReubenApp.Dto.AllQuesstionRepoDTO;
import webApp.ReubenApp.Dto.QuestionDto;
import webApp.ReubenApp.Entities.Questions;
import webApp.ReubenApp.Entities.User;
import webApp.ReubenApp.Repositories.QuestionRepo;
import webApp.ReubenApp.Repositories.UserRepo;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService implements QuestionServiceToImpl{
    public static  final int SEARCH_RESULTS_PER_PAGE=5;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {

        Optional<User> optionalUser= userRepo.findById(questionDto.getUserId());
//                userRepo.findById(questionDto.getUserId());

        if(optionalUser.isPresent()){

            User user = optionalUser.get();
            Questions questions= new Questions();

            questions.setTitle(questionDto.getTitle());
            questions.setBody(questionDto.getBody());
            questions.setTags(questionDto.getTags());
//            questions.setCreatedDate(new Date());
            questions.setCreatedDate(Date.from(Instant.now()));

            // Associate the user with the Questions entity
            questions.setUser(user);

            Questions createdQuestion = questionRepo.save(questions);
            QuestionDto createdQuestionDto = new QuestionDto();
            createdQuestionDto.setId(createdQuestion.getId());
            createdQuestionDto.setTitle(createdQuestion.getTitle());
            createdQuestionDto.setUserId(createdQuestion.getUser().getId());

            return  createdQuestionDto;

        }

        // Properly handle the case where the user is not found
        throw new UsernameNotFoundException("User not found with id: " + questionDto.getUserId());
    }

    @Override
    public AllQuesstionRepoDTO getAllquestions(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULTS_PER_PAGE);
        Page<Questions> questionsPage= questionRepo.findAll(paging);

        AllQuesstionRepoDTO allQuesstionRepoDTO = new AllQuesstionRepoDTO();
        allQuesstionRepoDTO.setQuestionDTOList(questionsPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
        allQuesstionRepoDTO.setPageNumber(questionsPage.getPageable().getPageNumber());
        allQuesstionRepoDTO.setTotalPages(questionsPage.getTotalPages());
        return null;
    }


//public QuestionDto addQuestion(QuestionDto questionDto){
//    Questions question = new Questions();
//    question.setTitle(questionDto.getTitle());
//    question.setBody(questionDto.getBody());
//    question.setTags(questionDto.getTags());
//    // Set other fields as needed
//    // For example: question.setCreatedDate(new Date());
//
//    // Save the question to the database
//    Questions savedQuestion = questionRepo.save(question);
//
//    return savedQuestion.getQuestionDto();
//}

}
