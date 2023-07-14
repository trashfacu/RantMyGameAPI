package com.rmg.rantMyGames.repository;

import com.rmg.rantMyGames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository <User, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail (String email);
}
