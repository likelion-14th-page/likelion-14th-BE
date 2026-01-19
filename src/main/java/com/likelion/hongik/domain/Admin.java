package com.likelion.hongik.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId; // 관리자 아이디

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    private String name; // 관리자 이름
}