package com.hannah0su.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.hannah0su.consumer.coupon.entity.Coupon;
import com.hannah0su.consumer.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponIssuedConsumer {

	private final CouponRepository couponRepository;

	@KafkaListener(topics = "coupon-issuance", groupId = "group-1")
	public void listener(Long userId) {
		// System.out.println("Issued By : " + userId);
		couponRepository.save(new Coupon(userId));
	}
}
