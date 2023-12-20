package com.openclassrooms.mddapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    public PostDto create(PostDto postDto) throws Exception {
        Post newPost = postMapper.toEntity(postDto);

        if (newPost.getTopic() == null) {
            throw new Exception("Topic from topic_id not found");
        }

        newPost.setCreatedAt(LocalDateTime.now());

        //  A MODIFIER POUR RECUPERER L'UTILISATEUR DANS LE CONTEXTE
        User user = userService.getById(1);
        newPost.setUser(user);

        Post savedPost = postRepository.save(newPost);

        return postMapper.toDto(savedPost);
    }
}
