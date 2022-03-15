package laustrup.editedshoutback.service.sub_services;

import laustrup.editedshoutback.models.Post;
import laustrup.editedshoutback.models.User;
import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import laustrup.editedshoutback.service.Service;

@org.springframework.stereotype.Service
public class PostService extends Service {

    public PostService(UserRepository userRepo, PostRepository postRepo) {
        super(userRepo, postRepo);
    }

    // Methods created for acting and returning entities
    public Post createPost(Post post) {
        User author = post.getAuthor();
        author.addPost(post);
        postRepo.save(post);
        userRepo.save(author);
        return post;
    }
    public Post editPost(Post post) {
        User author = post.getAuthor();
        author.replacePost(post);
        postRepo.save(post);
        userRepo.save(author);
        return post;
    }
    public Post deletePost(Long id) {
        if (postRepo.existsById(id)) {
            Post post = postRepo.findById(id).get();
            postRepo.delete(post);
            return post;
        }
        return null;
    }

}
