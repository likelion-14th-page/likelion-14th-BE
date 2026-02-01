package com.likelion.hongik.global.init;

import com.likelion.hongik.domain.Admin;
import com.likelion.hongik.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInit.class);

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Override
    @Transactional
    public void run(String... args) {
        if (adminRepository.findByLoginId(username).isEmpty()) {
            Admin rootAdmin = Admin.builder()
                    .loginId(username)
                    .password(passwordEncoder.encode(password)) // 가져온 비번 암호화
                    .name("총관리자")
                    .build();

            adminRepository.save(rootAdmin);
            logger.info("✅ 초기 관리자 계정 생성 완료! ID: {}", username);
        }
    }
}