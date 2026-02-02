package com.likelion.hongik;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class LikelionHongikApplication {


	@PostConstruct
	public void started() {
		// 어플리케이션 실행 시, 전역 시간대를 한국으로 설정
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(LikelionHongikApplication.class, args);
	}

}
