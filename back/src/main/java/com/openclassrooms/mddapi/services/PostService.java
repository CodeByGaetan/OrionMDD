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


    /**
     * Get page containing posts filtered by page number and page size and sorted by created date 
     * 
     * @param page The page number requested
     * @param size The number of items per page
     * @param asc Whether the articles are sorted in ascending order or in descending order
     * @return The page containing the filtered posts
     */
    public Page<Post> getAllPagedSorted(Integer page, Integer size, Boolean asc) {
        Sort sort = asc ? Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return postRepository.findAll(pageable);
    }

    /**
     * Get a post by its Id
     * 
     * @param id The number identifying the post
     * @return The post identified
     */
    public Post getById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * Create a post
     * 
     * @param newPost The post to create containing a title, a content and a topic Id
     * @return The post created in the database
     */
    public Post create(Post newPost) {
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(userService.getCurrentUser());

        Post savedPost = postRepository.save(newPost);

        return savedPost;
    }

    /**
     * Get all the comments of the post 
     * 
     * @param post The post containing the requested comments 
     * @return The comments list
     */
    public List<Comment> getCommentsFromPost(Post post) {
        return post.getComments();
    }

    /**
     * Create a comment on a post
     * 
     * @param post The post we want to comment on
     * @param comment The comment to create containing a content
     * @return The comment created in the database
     */
    public Comment createCommentOnPost(Post post, Comment comment) {
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(userService.getCurrentUser());

        Comment savedComment = commentService.save(comment);
        return savedComment;
    }
}
