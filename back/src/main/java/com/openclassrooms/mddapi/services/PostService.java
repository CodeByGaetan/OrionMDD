package com.openclassrooms.mddapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post create(Post newPost) {
        newPost.setCreatedAt(LocalDateTime.now());

        //  A MODIFIER POUR RECUPERER L'UTILISATEUR DANS LE CONTEXTE
        User user = userService.getById(1);
        newPost.setUser(user);

        Post savedPost = postRepository.save(newPost);

        return savedPost;
    }

    public List<Comment> getCommentsFromPost(Post post) {
        return post.getComments();
    }
}
