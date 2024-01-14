package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Save a comment in the database
     * 
     * @param comment containing a content, a created date, a post id and a user id
     * @return The saved comment, with its own id
     */
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
