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
        String message = checkAnnouncementTime();

        StudentResult studentResult = studentResultRepository
                .findWithStudentByNameAndPrivateNum(studentName, privateNum)
                .orElseThrow(() -> new IllegalArgumentException("지원자 정보를 찾을 수 없습니다."));

        return StudentResultResponseDto.builder()
                .message(message)
                .studentId(studentResult.getStudent().getId())
                .studentName(studentResult.getStudent().getName())
                .document(studentResult.getDocument())
                .finalResult(studentResult.getFinalResult())
                .location(studentResult.getLocation())
                .meetingDate(studentResult.getMeetingDate())
                .meetingTime(studentResult.getMeetingTime())
                .build();
    }

    private String checkAnnouncementTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetDoc = LocalDateTime.of(2026, 3, 1, 10, 0);
        LocalDateTime closeDoc = LocalDateTime.of(2026,3,5,22,0);
        LocalDateTime targetFinal = LocalDateTime.of(2026,3,7,10,0);

        if (now.isBefore(targetDoc)) {
            throw new IllegalArgumentException("아직 서류 합격자 조회 시간이 아닙니다." + now);
        }
        else if (now.isAfter(closeDoc) && now.isBefore(targetFinal)) {
            throw new IllegalArgumentException("아직 최종 합격자 조회 시간이 아닙니다." + now);
        }
        else if(now.isAfter(targetDoc) && now.isBefore(closeDoc)){
            return "서류 합격자 조회 기간입니다.";
        }
        else {
            return "최종 합격자 조회 기간입니다.";
        }
    }
}
