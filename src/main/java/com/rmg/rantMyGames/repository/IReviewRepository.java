package com.rmg.rantMyGames.repository;

import com.rmg.rantMyGames.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository <Review, Long> {
}
