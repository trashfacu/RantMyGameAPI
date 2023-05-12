package com.rmg.rantMyGames.repository;

import com.rmg.rantMyGames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <User, Long> {
}
