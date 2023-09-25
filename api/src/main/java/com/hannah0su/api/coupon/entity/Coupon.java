package com.hannah0su.api.coupon.entity;

import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Coupon {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@Builder
	public Coupon(Long userId) {
		this.userId = userId;
	}
}
