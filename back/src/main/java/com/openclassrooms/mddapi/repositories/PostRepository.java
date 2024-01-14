package com.openclassrooms.mddapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByTopicIn(List<Topic> topics, Pageable pageable);
    
}
