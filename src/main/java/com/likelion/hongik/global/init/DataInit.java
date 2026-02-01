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

/**
 * 애플리케이션 시작 시 초기 데이터를 설정하는 초기화 클래스입니다.
 * <p>
 * 관리자 계정이 존재하지 않을 경우, 설정 파일의 정보를 바탕으로 초기 관리자 계정을 생성합니다.
 * </p>
 */
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

    /**
     * 애플리케이션 구동 시점에 실행되는 메서드입니다.
     * <p>
     * DB에 해당 관리자 아이디가 존재하는지 확인하고,
     * 없을 경우 새로운 관리자 계정(Root Admin)을 생성하여 저장합니다.
     * </p>
     *
     * @param args 커맨드 라인 인자
     */
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