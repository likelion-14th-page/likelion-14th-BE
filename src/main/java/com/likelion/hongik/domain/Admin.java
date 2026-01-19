package com.likelion.hongik.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId; // 관리자 아이디

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    private String name; // 관리자 이름

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() { return loginId; }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 안 됨
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠김 안 됨
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 안 됨
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 됨
    }
}