package laustrup.editedshoutback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false,referencedColumnName = "id")
    @JsonIgnoreProperties("user_id")
    private User author;

    @Column(nullable = false, length = 30) private String title;
    @Lob @Column(nullable = false) private String content;

    @Column(nullable = false) private LocalDateTime date;

    @Column(length = 50) private String hashtag;

    @Column(nullable = false) private boolean isPoliticalCorrect;

    // Constructor for JPA
    public Post(){}
    // Constructor for creating a post
    public Post(User author,String title, String content,
                String hashtag,boolean isPoliticalCorrect) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.isPoliticalCorrect = isPoliticalCorrect;

        date = LocalDateTime.now();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public User getAuthor() {return author;}
    public void setAuthor(User author) {this.author = author;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content=content;}

    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date=date;}

    public String getHashtag() {return hashtag;}
    public void setHashtag(String hashtag) {this.hashtag = hashtag;}

    public boolean isPoliticalCorrect() {return isPoliticalCorrect;}
    public void setPoliticalCorrect(boolean politicalCorrect) {isPoliticalCorrect = politicalCorrect;}
}
