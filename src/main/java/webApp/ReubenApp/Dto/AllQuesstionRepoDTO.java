package webApp.ReubenApp.Dto;


import java.util.List;

public class AllQuesstionRepoDTO {
    private List<QuestionDto> questionDTOList;
    private  Integer totalPages;
    private  Integer pageNumber;

    public List<QuestionDto> getQuestionDTOList() {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDto> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}

