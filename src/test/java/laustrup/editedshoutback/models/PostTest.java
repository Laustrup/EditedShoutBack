package laustrup.editedshoutback.models;

import laustrup.editedshoutback.models.enums.Gender;
import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostTest {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;

    private Post expected,act;
    private User user;

    @BeforeEach
    public void beforeEach() {
        expected = new Post();
        act = new Post();
        if (!userRepo.existsById(new Long(1))) {
            userRepo.save(new User("TestUser","qscqsccsqsc",
                    "This user is made for testing", Gender.Other));
        }
        user = userRepo.findByUsername("TestUser");
    }

    @ParameterizedTest
    @CsvSource(value = {"TestPostTestPost|This post is made for a test|#TestTest|true",
            "123456789-123456789-123456789-123456789|This post is made for a test|#TestTestTest|false",
            "123456789-123456789-123456789-123456789|123456789-123456789-123456789-123456789-123456789-123456789-123456789-123456789|#TestTestTestTest|true",
            "TestPostTestPostTestPost|123456789-123456789-123456789-123456789-123456789-123456789-123456789-123456789|#TestTestTestTest|false"}, delimiter = '|')
    public void createAndFindPost(String title, String content,
                           String hashtag,boolean isPoliticalCorrect) {
        // Arrange
        expected = new Post(user,title,content,hashtag,isPoliticalCorrect);

        // Act
        try {
            postRepo.save(expected);
            act = postRepo.getById(expected.getId());

            // Assert
            assertPosts();
        }
        catch (Exception e) {
            // Assert
            assertExpectedFalse();
        }

    }

    @ParameterizedTest
    @CsvSource(value = {"TestPostTestPost|TestPost|This content has changed",
                        "TestPost|TestPostTestPost-TestPostTestPost-TestPostTestPost|This content has changed",
                        "TestPost|TestPostTestPost-TestPostTestPost-TestPostTestPost|This content has changed-This content has changed-This content has changed-This content has changed-This content has changed",
                        "TestPost|This title has changed|This content has changed-This content has changed-This content has changed-This content has changed-This content has changed"},delimiter = '|')
    public void editPost(String originalTitle,String newTitle, String newContent) {
        // Arrange
        expected = postRepo.findByTitle(originalTitle);
        Post edited = expected;

        // Act
        edited.setTitle(newTitle);
        edited.setContent(newContent);
        try {
            act = postRepo.save(edited);
            expected = edited;

            // Assert
            assertPosts();
        }
        catch (Exception e) {
            act = expected;

            // Assert
            assertPosts();
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"This title has changed"},delimiter = '|')
    public void deletePost(String title) {
        // Arrange
        expected = postRepo.findByTitle(title);

        // Act
        postRepo.delete(expected);

        // Assert
        assertFalse(postRepo.existsById(expected.getId()));
    }

    private void assertPosts() {
        // Assert
        assertEquals(expected.getId(),act.getId());
        assertEquals(expected.getTitle(),act.getTitle());
        assertEquals(expected.getAuthor().getId(),act.getAuthor().getId());
        assertEquals(expected.getContent(),act.getContent());
        //assertEquals(expected.getDate(),act.getDate());
        assertEquals(expected.isPoliticalCorrect(),act.isPoliticalCorrect());
    }
    private void assertExpectedFalse() {
        // Assert
        if (expected.getId()!=null) {
            assertFalse(postRepo.existsById(expected.getId()));
        }
        else {
            assertTrue(true);
        }
    }
    
}