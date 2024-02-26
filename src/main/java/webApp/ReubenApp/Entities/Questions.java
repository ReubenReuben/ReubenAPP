package webApp.ReubenApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import webApp.ReubenApp.Dto.QuestionDto;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    @Column(name = "body", length = 512)
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @ElementCollection
    private List<String> tags;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
  public QuestionDto getQuestionDto(){
      QuestionDto questionDto=new QuestionDto();
      questionDto.setId(id);
      questionDto.setTitle(title);
      questionDto.setBody(body);
      questionDto.setTags(tags);
//      questionDto.setUserId(user.getId());
      questionDto.setUserId(user.getId());
      questionDto.setName(user.getName());
      return  questionDto;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Questions(Long id, String title, String body, Date createdDate, List<String> tags, User user) {

        this.id = id;
        this.title = title;
        this.body = body;
        this.createdDate = createdDate;
        this.tags = tags;
        this.user = user;
    }

    public Questions() {
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", tags=" + tags +
                ", user=" + user +
                '}';
    }
}
