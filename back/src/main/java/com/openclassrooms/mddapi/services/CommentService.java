package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
