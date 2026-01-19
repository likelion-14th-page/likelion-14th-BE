package com.likelion.hongik.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResultType document; // 서류합격 여부

    @Enumerated(EnumType.STRING)
    private ResultType finalResult; // 최종합격 여부 (final은 예약어이므로 finalResult 권장)

    @OneToMany(mappedBy = "result")
    private List<StudentResult> studentResults = new ArrayList<>();
}