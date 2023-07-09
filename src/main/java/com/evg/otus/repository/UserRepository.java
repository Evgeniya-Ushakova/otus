package com.evg.otus.repository;

import com.evg.otus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new RuntimeException(String.format("User with id = %s not found", userId)));
    }

}
