package com.openclassrooms.mddapi.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.others.responses.PageResponse;
import com.openclassrooms.mddapi.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Posts", description = "API for CRUD operations on Posts")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * Get all posts, subscribed by the user, and filtered by page number and page size and sorted by created date 
     * 
     * @param page The page number requested
     * @param size The number of items per page
     * @param asc Whether the articles are sorted in ascending order or in descending order
     * @return List of filtered posts and the total number of posts in the database 
     */
    @Operation(summary = "Get all posts subscribed paged and sorted")
    @Transactional
    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Boolean asc) {

        Page<Post> postsPage = postService.getAllPagedSorted(page, size, asc);
        List<PostDto> postDtos = postMapper.toDto(postsPage.getContent());
        Integer totalItems = (int) postsPage.getTotalElements();

        PageResponse<PostDto> pageResponse = new PageResponse<PostDto>(postDtos, totalItems);

        return ResponseEntity.ok().body(pageResponse);
    }

    /**
     * Get a post by its Id
     * 
     * @param id The number identifying the post
     * @return The post identified
     */
    @Operation(summary = "Get a post by Id")
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(postMapper.toDto(post));
    }

    /**
     * Create a post from a valid post request
     * 
     * @param postDto The post request containing a title, a content and a topic Id
     * @return Ok status response
     */
    @Operation(summary = "Create a post")
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto) {

        Post newPost = postMapper.toEntity(postDto);
        if (newPost.getTopic() == null) {
            return ResponseEntity.notFound().build();
        }

        postService.create(newPost);

        return ResponseEntity.ok().build();
    }

    /**
     * Get all the comments of the identified post 
     * 
     * @param id The number identifying the post containing the requested comments 
     * @return The comments list
     */
    @Operation(summary = "Get the post comments")
    @GetMapping("/posts/{id}/comments")
    @Transactional
    public ResponseEntity<?> getCommentsFromPost(@PathVariable Integer id) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        List<Comment> comments = postService.getCommentsFromPost(post);

        return ResponseEntity.ok().body(commentMapper.toDto(comments));
    }

    /**
     * Create a comment on a post from a valid comment request
     * 
     * @param id The number identifying the post we want to comment on
     * @param commentDto The comment request containing a content
     * @return Ok status response
     */
    @Operation(summary = "Create comment on the post")
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> createCommentOnPost(@PathVariable Integer id, @Valid @RequestBody CommentDto commentDto) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        Comment newComment = commentMapper.toEntity(commentDto);

        postService.createCommentOnPost(post, newComment);

        return ResponseEntity.ok().build();
    }

}
