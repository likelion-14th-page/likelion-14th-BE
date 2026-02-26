package com.likelion.hongik.service;

import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.enums.PartType;
import com.likelion.hongik.domain.enums.ResultType;
import com.likelion.hongik.dto.request.GoogleFormWebhookRequest;
import com.likelion.hongik.repository.StudentRepository;
import com.likelion.hongik.repository.StudentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentImportService {

    private final StudentRepository studentRepository;
    private final StudentResultRepository studentResultRepository;

    @Transactional
    public void importOne(GoogleFormWebhookRequest req) {
        PartType part = mapPart(req.getPart());

        Student student = studentRepository.findByStudentNum(req.getStudentNum())
                .orElseGet(() -> Student.builder()
                        .studentNum(req.getStudentNum())
                        .name(req.getName())
                        .email(req.getEmail())
                        .phoneNum(req.getPhoneNum())
                        .part(part)
                        .privateNum(req.getPrivateNum())
                        .build()
                );

        if (student.getId() != null) {
            student.updateApplication(req.getName(), req.getPhoneNum(), part, req.getPrivateNum(), req.getEmail());
        }

        studentRepository.save(student);

        LocalDateTime additionTime = LocalDateTime.of(2026, 2, 27, 0, 0);

        if (LocalDateTime.now().isAfter(additionTime) ){
            student.setAddition(true);
        }

        studentResultRepository.findByStudent(student)
                .orElseGet(() -> studentResultRepository.save(
                        StudentResult.builder()
                                .student(student)
                                .document(ResultType.불합격)
                                .finalResult(ResultType.불합격)
                                .build()
                ));
    }

    private PartType mapPart(String raw) {
        if (raw == null) throw new IllegalArgumentException("part is null");
        return switch (raw.trim()) {
            case "기획/디자인", "디자인" -> PartType.디자인;
            case "프론트엔드" -> PartType.프론트엔드;
            case "백엔드" -> PartType.백엔드;
            default -> throw new IllegalArgumentException("Unknown part: " + raw);
        };
    }
}
