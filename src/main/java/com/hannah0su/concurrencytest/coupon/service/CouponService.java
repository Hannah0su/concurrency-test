package com.hannah0su.concurrencytest.coupon.service;

import org.springframework.stereotype.Service;

import com.hannah0su.concurrencytest.coupon.entity.Coupon;
import com.hannah0su.concurrencytest.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository couponRepository;

	public void issuance(Long userId){
		long count = couponRepository.count();

		if (count>100){
			return;
		}

		couponRepository.save(new Coupon(userId));
	}

}
