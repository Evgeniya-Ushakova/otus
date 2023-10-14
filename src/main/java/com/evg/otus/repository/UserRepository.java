package com.evg.otus.repository;

import com.evg.otus.entity.User;
import com.evg.otus.enums.ErrorMessageCode;
import com.evg.otus.exception.DataNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessageCode.DATA_NOT_FOUND.getCode(),
                String.format("User with id = %s not found", userId)));
    }

    boolean existsByEmail(String email);

}
