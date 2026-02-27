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
@Table(indexes = {
    @Index(name = "idx_student_private_name", columnList = "privateNum, name"),
    @Index(name = "idx_student_part", columnList = "part")
})
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
    private PartType part;

    private String privateNum; // 고유번호

    private Boolean addition;

    @OneToOne(mappedBy = "student")
    private StudentResult studentResult;

    public void updateApplication(String name, String phoneNum, PartType part, String privateNum, String email) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.part = part;
        this.privateNum = privateNum;
        this.email = email;
    }

    public void setStudentResult(StudentResult studentResult) {
        this.studentResult = studentResult;
        if (studentResult != null && studentResult.getStudent() != this) {
            studentResult.setStudent(this);
        }
    }

    public void setAddition(Boolean addition) {
        this.addition = addition;
    }
}
