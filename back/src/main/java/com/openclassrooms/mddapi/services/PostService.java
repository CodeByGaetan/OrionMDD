package com.openclassrooms.mddapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    public Page<Post> getAllPagedSorted(Integer page, Integer size, Boolean asc) {

        Sort sort = asc ? Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository.findAll(pageable);
    }

    public Post getById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post create(Post newPost) {
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userService.getCurrentUser());

        Post savedPost = postRepository.save(newPost);

        return savedPost;
    }

    public List<Comment> getCommentsFromPost(Post post) {
        return post.getComments();
    }

    public Comment createCommentOnPost(Post post, Comment comment) {
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(userService.getCurrentUser());

        Comment savedComment = commentService.save(comment);
        return savedComment;
    }
}
