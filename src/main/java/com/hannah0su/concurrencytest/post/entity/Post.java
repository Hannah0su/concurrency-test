package com.hannah0su.concurrencytest.post.entity;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DynamicInsert
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String body;

	@ColumnDefault("0")
	private Long views;

	@Builder
	public Post(String title, String body, Long views) {
		this.title = title;
		this.body = body;
		this.views = views;
	}

	public void increaseViewCount() {
		this.views++;
	}
}
