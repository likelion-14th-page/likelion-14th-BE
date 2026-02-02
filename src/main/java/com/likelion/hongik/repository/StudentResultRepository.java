package com.likelion.hongik.repository;

import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.enums.ResultType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    // 특정 학생의 결과 정보 조회
    Optional<StudentResult> findByStudent(Student student);

    @Query("SELECT sr FROM StudentResult sr " +
            "JOIN FETCH sr.student s " +
            "WHERE s.name = :name AND s.privateNum = :privateNum")
    Optional<StudentResult> findWithStudentByNameAndPrivateNum(
            @Param("name") String name,
            @Param("privateNum") String privateNum
    );

    // 서류 합격자 전체 조회
    @Query("""
        SELECT sr
        FROM StudentResult sr
        JOIN FETCH sr.student s
        WHERE sr.document = :result
    """)
    List<StudentResult> findAllByDocument(
            @Param("result") ResultType result
    );
}