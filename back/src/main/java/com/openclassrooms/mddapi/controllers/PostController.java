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
     * 
     * @param page
     * @param size
     * @param asc
     * @return
     * @throws InterruptedException
     */
    @Operation(summary = "Get all posts paged and sorted")
    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Boolean asc) throws InterruptedException {

        Page<Post> postsPage = postService.getAllPagedSorted(page, size, asc);
        List<PostDto> postDtos = postMapper.toDto(postsPage.getContent());
        Integer totalItems = (int) postsPage.getTotalElements();

        PageResponse<PostDto> pageResponse = new PageResponse<PostDto>(postDtos, totalItems);

        return ResponseEntity.ok().body(pageResponse);
    }

    /**
     * 
     * @param id
     * @return
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
     * 
     * @param postDto
     * @return
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
     * 
     * @param id
     * @return
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
     * 
     * @param id
     * @param commentDto
     * @return
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
