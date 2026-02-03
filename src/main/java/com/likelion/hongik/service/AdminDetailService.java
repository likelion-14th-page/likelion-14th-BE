package com.likelion.hongik.service;

import com.likelion.hongik.domain.Admin;
import com.likelion.hongik.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    /**
     *
     * @param loginId
     * @return 로그인 (spring security 처리)
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return adminRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 관리자가 존재하지 않습니다: " + loginId));
    }
}