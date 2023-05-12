package com.rmg.rantMyGames.repository;

import com.rmg.rantMyGames.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository <Rating, Long> {
}
