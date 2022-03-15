package laustrup.editedshoutback.service.sub_services;

import laustrup.editedshoutback.models.User;
import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import laustrup.editedshoutback.service.Service;

@org.springframework.stereotype.Service
public class UserService extends Service {

    public UserService(UserRepository userRepo, PostRepository postRepo) {
        super(userRepo,postRepo);
    }

    // Methods created for acting and returning entities
    public User createUser(User user) {return userRepo.save(user);}
    public User deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            User user = userRepo.findById(id).get();
            userRepo.delete(user);
            return user;
        }
        return null;
    }
}
