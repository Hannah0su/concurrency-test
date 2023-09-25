package com.hannah0su.api.coupon.service;

import org.springframework.stereotype.Service;

import com.hannah0su.api.coupon.producer.CouponIssuanceProducer;
import com.hannah0su.api.coupon.repository.CouponCountRepository;
import com.hannah0su.api.coupon.repository.CouponRepository;
import com.hannah0su.api.coupon.entity.Coupon;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository couponRepository;
	private final CouponCountRepository couponCountRepository;
	private final CouponIssuanceProducer couponIssuanceProducer;

	public void issuance(Long userId){
		long count = couponRepository.count();

		if (count>100){
			return;
		}

		couponRepository.save(new Coupon(userId));
	}

	public void issuanceByRedis(Long userId){
		Long count = couponCountRepository.increment();

		if (count>100){
			return;
		}

		couponRepository.save(new Coupon(userId));
	}

	public void issuanceByKafka(Long userId){
		Long count = couponCountRepository.increment();

		if (count>100){
			return;
		}

		couponIssuanceProducer.issuance(userId);
	}

}
