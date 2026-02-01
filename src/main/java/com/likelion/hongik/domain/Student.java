package com.likelion.hongik.domain;

import com.likelion.hongik.domain.enums.PartType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentNum; // 학번

    private String name;

    private String email;

    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private PartType part; // 파트 (Enum)

    private String privateNum; // 고유번호

    @OneToOne(mappedBy = "student")
    private StudentResult studentResult;

    public void setStudentResult(StudentResult studentResult) {
        this.studentResult = studentResult;
    }
}