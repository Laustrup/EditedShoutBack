package laustrup.editedshoutback.controllers.sub_controllers;

import laustrup.editedshoutback.controllers.Controller;
import laustrup.editedshoutback.models.Post;
import laustrup.editedshoutback.repositories.PostRepository;
import laustrup.editedshoutback.repositories.UserRepository;
import laustrup.editedshoutback.service.sub_services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PostController extends Controller {

    private PostService postService;

    public PostController(UserRepository userRepo, PostRepository postRepo) {
        super(userRepo,postRepo);
        postService = new PostService(userRepo, postRepo);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(postRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping("/post/:{id}")
    public ResponseEntity<Post> getPost(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(postRepo.findById(id).get(), HttpStatus.OK);
    }

    //Without authorid in pathvariable
    @PostMapping(value = "/post", consumes = "application/json")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepo.save(post),HttpStatus.CREATED);
    }

    @PutMapping(value = "/post", consumes = "application/json")
    public ResponseEntity<Post> editPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.editPost(post),HttpStatus.OK);
    }

    @DeleteMapping("/delete_post/:{id}")
    public ResponseEntity<Post> deletePost(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(postService.deletePost(id),HttpStatus.OK);
    }
}
