package com.likelion.hongik.domain;

import com.likelion.hongik.domain.enums.PartType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentNum; // 학번

    private String email;
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private PartType part; // 파트 (Enum)

    private String privateNum; // 고유번호

    @OneToMany(mappedBy = "student")
    private List<StudentResult> studentResults = new ArrayList<>();
}