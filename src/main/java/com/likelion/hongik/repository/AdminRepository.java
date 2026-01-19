package com.likelion.hongik.repository;

import com.likelion.hongik.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // 로그인 아이디로 관리자 찾기
    Optional<Admin> findByLoginId(String loginId);
}