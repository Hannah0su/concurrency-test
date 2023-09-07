package com.hannah0su.concurrencytest.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannah0su.concurrencytest.post.dto.PostRequestDto;
import com.hannah0su.concurrencytest.post.dto.PostResponseDto;
import com.hannah0su.concurrencytest.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	private final PostService postService;

	@GetMapping("{id}")
	public PostResponseDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@GetMapping
	public List<PostResponseDto> getPosts() {
		return postService.getPosts();
	}

	@PostMapping
	public void createPost(@RequestBody PostRequestDto request) {
		postService.createPost(request.getTitle(), request.getBody());
	}
}