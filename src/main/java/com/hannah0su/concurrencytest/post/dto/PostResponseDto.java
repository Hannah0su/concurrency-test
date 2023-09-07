package com.hannah0su.concurrencytest.post.dto;

import com.hannah0su.concurrencytest.post.entity.Post;

import lombok.Getter;

@Getter
public class PostResponseDto {

	private Long id;

	private String title;

	private String body;

	private Long views;

	public PostResponseDto(Long id, String title, String body, Long views) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.views = views;
	}

	public PostResponseDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.body = post.getBody();
		this.views = post.getViews();
	}
}
