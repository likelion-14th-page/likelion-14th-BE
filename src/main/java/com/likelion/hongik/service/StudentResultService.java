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

import java.time.LocalDateTime;

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

//        LocalDateTime nowTime = LocalDateTime.now();
//        LocalDateTime target_doc = LocalDateTime.of(2026, 2, 28, 10, 0, 0)
//        LocalDateTime target_final= LocalDateTime.of(2026,3,7,10,0,0);
//
//        if(nowTime.isBefore(target_doc)){
//          throw new IllegalArgumentException("아직 합격자 조회 시간이 아닙니다. 현재 시각 = " + nowTime);
//        }

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
