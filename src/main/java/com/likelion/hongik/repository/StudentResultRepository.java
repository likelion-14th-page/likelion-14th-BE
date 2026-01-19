package com.likelion.hongik.repository;

import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    // 특정 학생의 결과 정보 조회
    Optional<StudentResult> findByStudent(Student student);
}