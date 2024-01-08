package com.openclassrooms.mddapi.controllers;

import java.util.List;

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
import com.openclassrooms.mddapi.responses.MessageResponse;
import com.openclassrooms.mddapi.responses.PageResponse;
import com.openclassrooms.mddapi.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Boolean asc) {

        Page<Post> postsPage = postService.getAllPagedSorted(page, size, asc);
        List<PostDto> postDtos = postMapper.toDto(postsPage.getContent());
        Integer totalItems = (int) postsPage.getTotalElements();

        PageResponse<PostDto> pageResponse = new PageResponse<PostDto>(postDtos, totalItems);

        return ResponseEntity.ok().body(pageResponse);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(postMapper.toDto(post));
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto) {

        Post newPost = postMapper.toEntity(postDto);
        if (newPost.getTopic() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Topic from topic_id not found"));
        }

        // Retourner l'objet ?
        postService.create(newPost);

        return ResponseEntity.ok().body(new MessageResponse("Post created !"));
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<?> getCommentsFromPost(@PathVariable Integer id) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        List<Comment> comments = postService.getCommentsFromPost(post);

        return ResponseEntity.ok().body(commentMapper.toDto(comments));
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> createCommentOnPost(@PathVariable Integer id, @Valid @RequestBody CommentDto commentDto) {

        Post post = postService.getById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        Comment newComment = commentMapper.toEntity(commentDto);

        // Retourner l'objet ?
        postService.createCommentOnPost(post, newComment);

        return ResponseEntity.ok().body(new MessageResponse("Post commented !"));
    }

}
