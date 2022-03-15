package laustrup.editedshoutback.service;

import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;

@org.springframework.stereotype.Service
public class Service {

    protected UserRepository userRepo;
    protected PostRepository postRepo;

    public Service(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

}
