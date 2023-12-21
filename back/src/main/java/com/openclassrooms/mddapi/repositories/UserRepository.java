package com.openclassrooms.mddapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Boolean existsByEmail(String email);
    // Boolean existsByName(String name);

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
