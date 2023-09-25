package com.hannah0su.api.coupon.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CouponIssuanceProducer {
	private final KafkaTemplate<String, Long> kafkaTemplate;

	public void issuance(Long userId){
		kafkaTemplate.send("coupon-issuance",userId);
	}
}
