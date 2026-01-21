package com.likelion.hongik.service;

import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.dto.response.StudentResultResponseDto;
import com.likelion.hongik.repository.ResultRepository;
import com.likelion.hongik.repository.StudentRepository;
import com.likelion.hongik.repository.StudentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentResultService {
    private final StudentResultRepository studentResultRepository;
    private final StudentRepository studentRepository;

    //지원 결과 확인
    public StudentResultResponseDto getStudentResult(String studentName, String privateNum) {
        Student student = studentRepository.findByNameAndPrivateNum(studentName, privateNum)
                .orElseThrow(()-> new IllegalArgumentException("Student not found"));

        StudentResult studentResult = studentResultRepository.findByStudent(student)
                .orElseThrow(()-> new IllegalArgumentException("StudentResult not found"));

        return StudentResultResponseDto.builder()
                .studentId(student.getId())
                .studentName(student.getName())
                .document(studentResult.getResult().getDocument())
                .finalResult(studentResult.getResult().getFinalResult())
                .location(studentResult.getLocation())
                .meetingDate(studentResult.getMeetingDate())
                .meetingTime(studentResult.getMeetingTime())
                .build();
    }
}
