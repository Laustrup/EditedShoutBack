package laustrup.editedshoutback.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import laustrup.editedshoutback.models.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // String attributes
    @Column(nullable = false, length = 40) private String username,password;
    @Lob @Column private String description;

    // Isn't boolean, for political correctness sake
    @Enumerated(EnumType.ORDINAL) @Column private Gender gender;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    private List<Post> posts;

    // Constructor for JPA
    public User(){}
    // Constructor for creating user
    public User(String username,String password,String description,Gender gender) {
        this.username = username;
        this.password = password;
        this.description = description;
        this.gender = gender;

        posts = new ArrayList<>();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Gender getGender() {return gender;}
    public void setGender(Gender gender) {this.gender = gender;}

    public List<Post> getPosts() {return posts;}
    public void addPost(Post post) {posts.add(post);}
    public List<Post> replacePost(Post post) {
        for (int i = 0; i < posts.size();i++) {
            if (posts.get(i).getId()==post.getId()) {
                posts.set(i,post);
                return posts;
            }
        }
        return null;
    }
    public void setPosts(List<Post> posts) {this.posts = posts;}

}
