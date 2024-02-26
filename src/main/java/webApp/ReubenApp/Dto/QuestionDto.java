package webApp.ReubenApp.Dto;

import java.util.List;

public class QuestionDto {
    private Long id;
    private String title;
    private String body;
    private List<String> tags;
    private Long userId;
    private String name;

    public QuestionDto(Long id, String title, String body, List<String> tags, Long userId, String username) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.userId = userId;
        this.name = username;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }

    public QuestionDto() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
