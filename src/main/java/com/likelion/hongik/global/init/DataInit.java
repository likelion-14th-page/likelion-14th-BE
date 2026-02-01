package com.likelion.hongik.global.init;

import com.likelion.hongik.domain.Admin;
import com.likelion.hongik.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() == 0) {
            Admin rootAdmin = Admin.builder()
                    .loginId(username)
                    .password(passwordEncoder.encode(password)) // 가져온 비번 암호화
                    .name("총관리자")
                    .build();

            adminRepository.save(rootAdmin);
            System.out.println("✅ 초기 관리자 계정 생성 완료! ID: " + username);
        }
    }
}