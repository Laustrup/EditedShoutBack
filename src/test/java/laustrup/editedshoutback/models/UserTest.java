package laustrup.editedshoutback.models;

import laustrup.editedshoutback.models.enums.Gender;
import laustrup.editedshoutback.repositories.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepo;

    @ParameterizedTest
    @CsvSource(value = {"TestTestTest|qsccsq|ThisIsATest|Other"},delimiter = '|')
    public void createAndFindUser(String username, String password, String description, Gender gender) {
        // Arrange
        User expected = new User(username,password,description,gender);

        // Act
        userRepo.save(expected);
        User act = userRepo.findByUsername(username);

        // Assert
        assertEquals(expected.getId(),act.getId());
        assertEquals(expected.getPassword(),act.getPassword());
        assertEquals(expected.getDescription(),act.getDescription());
        assertEquals(expected.getGender(),act.getGender());
    }

    @ParameterizedTest
    @CsvSource(value = {"TestTestTest"},delimiter = '|')
    public void deleteUser(String username) {
        // Arrange
        User user = userRepo.findByUsername(username);

        // Act
        userRepo.delete(user);

        // Assert
        assertFalse(userRepo.existsById(user.getId()));
    }

}