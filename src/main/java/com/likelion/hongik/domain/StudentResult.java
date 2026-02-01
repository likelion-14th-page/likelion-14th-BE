package com.likelion.hongik.domain;

import com.likelion.hongik.domain.enums.ResultType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class StudentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private ResultType document; // 서류 결과 (null 가능)

    @Enumerated(EnumType.STRING)
    private ResultType finalResult; // 최종 결과 (null 가능)

    private String meetingDate;
    private String meetingTime;
    private String location;

    // 단계별 업데이트를 위한 편의 메서드
    public void updateDocumentResult(ResultType result) {
        this.document = result;
    }

    public void updateFinalResult(ResultType result) {
        this.finalResult = result;
    }

    @CreatedDate
    private LocalDateTime createdAt;
}
