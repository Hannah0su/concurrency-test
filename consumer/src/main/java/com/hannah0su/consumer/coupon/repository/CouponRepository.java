package com.hannah0su.consumer.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hannah0su.consumer.coupon.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}