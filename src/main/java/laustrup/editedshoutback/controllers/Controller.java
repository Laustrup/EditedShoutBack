package laustrup.editedshoutback.controllers;

import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Controller {

    protected UserRepository userRepo;
    protected PostRepository postRepo;

    public Controller(UserRepository userRepo,PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

}
