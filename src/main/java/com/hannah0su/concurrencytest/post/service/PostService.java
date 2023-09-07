package com.hannah0su.concurrencytest.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hannah0su.concurrencytest.post.dto.PostResponseDto;
import com.hannah0su.concurrencytest.post.entity.Post;
import com.hannah0su.concurrencytest.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepository;

	// 조회 시 문제 발생
	// @Transactional
	// public PostResponseDto getPost(Long id) {
	// 	Post post = postRepository.findById(id)
	// 		.orElseThrow(IllegalArgumentException::new);
	//
	// 	post.increaseView();
	//
	// 	return new PostResponseDto(post);
	// }

	// Update쿼리 사용으로 해결
	// @Transactional
	// public PostResponseDto getPost(Long id) {
	// 	Post post = postRepository.findById(id)
	// 		.orElseThrow(IllegalArgumentException::new);
	//
	// 	postRepository.increaseViewCount(post.getId());
	//
	// 	return new PostResponseDto(post);
	// }

	// 비관적락(Select for Update)으로 해결
	@Transactional
	public PostResponseDto getPost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);

		post.increaseViewCount();

		return new PostResponseDto(post);
	}

	@Transactional(readOnly = true)
	public List<PostResponseDto> getPosts() {
		return postRepository.findAll()
			.stream()
			.map(PostResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void createPost(String title, String body) {
		Post post = Post.builder()
			.title(title)
			.body(body)
			.build();
		postRepository.save(post);
	}
}