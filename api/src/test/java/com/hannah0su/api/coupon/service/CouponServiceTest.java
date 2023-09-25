package com.hannah0su.api.coupon.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hannah0su.api.coupon.repository.CouponRepository;

@SpringBootTest
public class CouponServiceTest {

	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponRepository couponRepository;

	@Test
	@DisplayName("단건 발급")
	public void 발급() {
		couponService.issuance(1L);
		long count = couponRepository.count();
		assertThat(count).isEqualTo(1);
	}

	@Test
	@DisplayName("여러명 발급")
	public void 여러명발급() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					couponService.issuance(userId);
				} finally {
					countDownLatch.countDown();
				}

			});
		}

		countDownLatch.await();
		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}

	@Test
	@DisplayName("여러명 발급_레디스")
	public void 여러명발급_레디스() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					couponService.issuanceByRedis(userId);
				} finally {
					countDownLatch.countDown();
				}

			});
		}

		countDownLatch.await();
		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}

	@Test
	@DisplayName("여러명 발급_카프카")
	public void 여러명발급_카프카() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					couponService.issuanceByKafka(userId);
				} finally {
					countDownLatch.countDown();
				}

			});
		}

		countDownLatch.await();
		Thread.sleep(10000);
		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}


}
