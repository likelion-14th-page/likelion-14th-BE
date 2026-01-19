package com.likelion.hongik.service;

import com.likelion.hongik.domain.Admin;
import com.likelion.hongik.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final AdminRepository adminRepository;

    public boolean login(String loginId, String password) {
        return adminRepository.findByLoginId(loginId)
                .map(admin -> admin.getPassword().equals(password)) // 실제 서비스에선 PasswordEncoder 필수
                .orElse(false);
    }
}