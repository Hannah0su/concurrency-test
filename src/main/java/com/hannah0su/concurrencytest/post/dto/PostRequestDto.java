package com.hannah0su.concurrencytest.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

	private String title;

	private String body;

	public PostRequestDto(String title, String body) {
		this.title = title;
		this.body = body;
	}
}
