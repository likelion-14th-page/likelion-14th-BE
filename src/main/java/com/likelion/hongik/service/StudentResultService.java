package com.likelion.hongik.service;

import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.dto.response.StudentResultResponseDto;
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
        //checkAnnouncementTime();

        StudentResult studentResult = studentResultRepository
                .findWithStudentByNameAndPrivateNum(studentName, privateNum)
                .orElseThrow(() -> new IllegalArgumentException("지원자 정보를 찾을 수 없습니다."));

        return StudentResultResponseDto.builder()
                .studentId(studentResult.getStudent().getId())
                .studentName(studentResult.getStudent().getName())
                .document(studentResult.getDocument())
                .finalResult(studentResult.getFinalResult())
                .location(studentResult.getLocation())
                .meetingDate(studentResult.getMeetingDate())
                .meetingTime(studentResult.getMeetingTime())
                .build();
    }

    private void checkAnnouncementTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetDoc = LocalDateTime.of(2026, 2, 28, 10, 0);

        if (now.isBefore(targetDoc)) {
            throw new IllegalArgumentException("아직 조회 시간이 아닙니다.");
        }
    }
}
