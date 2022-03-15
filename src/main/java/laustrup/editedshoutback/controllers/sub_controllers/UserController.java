package laustrup.editedshoutback.controllers.sub_controllers;

import laustrup.editedshoutback.controllers.Controller;
import laustrup.editedshoutback.models.User;
import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import laustrup.editedshoutback.service.sub_services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController extends Controller {

    private UserService userService;

    public UserController(UserRepository userRepo, PostRepository postRepo) {
        super(userRepo,postRepo);
        userService = new UserService(userRepo,postRepo);
    }

    @GetMapping("/user/:{id}")
    public ResponseEntity<User> getUser(@PathVariable(name="id") Long id) {
        return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
    }
    @GetMapping("/user/username/:{username}")
    public ResponseEntity<User> getUser(@PathVariable(name="username") String username) {
        return new ResponseEntity<>(userRepo.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepo.save(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_user/:{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(name="id") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

}
