package com.likelion.hongik.repository;

import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.enums.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // 학번으로 학생 조회
    Optional<Student> findByStudentNum(String studentNum);

    // 파트별(디자인/백엔드/프론트엔드) 학생 목록 조회
    List<Student> findAllByPart(PartType part);

    Optional<Student> findByNameAndPrivateNum(String name, String privateNum);
}