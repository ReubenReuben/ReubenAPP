package webApp.ReubenApp.ServiceClasses;

import webApp.ReubenApp.Dto.AllQuesstionRepoDTO;
import webApp.ReubenApp.Dto.QuestionDto;

public interface QuestionServiceToImpl {
    QuestionDto addQuestion(QuestionDto questionDto);
    AllQuesstionRepoDTO getAllquestions(int pageNumber);
}
