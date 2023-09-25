package com.hannah0su.api.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hannah0su.api.post.entity.Post;

import jakarta.persistence.LockModeType;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Modifying
	@Query("UPDATE Post p SET p.views = p.views + 1 WHERE p.id = :id")
	int increaseViewCount(Long id);
	
	// 비관적락(Select for Update)으로 해결
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Post> findById(Long id);
}