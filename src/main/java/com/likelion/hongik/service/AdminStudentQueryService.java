package com.likelion.hongik.service;

import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.enums.PartType;
import com.likelion.hongik.dto.response.AdminStudentRowDto;
import com.likelion.hongik.repository.StudentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminStudentQueryService {

    private final StudentResultRepository studentResultRepository;

    public List<AdminStudentRowDto> getStudents(String part) {
        List<StudentResult> results;

        if (part == null || part.isBlank() || part.equals("전체")) {
            results = studentResultRepository.findAllWithStudentOrderByIdDesc();
        } else {
            PartType partType = mapPart(part);
            results = studentResultRepository.findAllWithStudentByPartOrderByIdDesc(partType);
        }

        return results.stream()
                .map(sr -> AdminStudentRowDto.builder()
                        .studentId(sr.getStudent().getId())
                        .part(sr.getStudent().getPart())
                        .name(sr.getStudent().getName())
                        .studentNum(sr.getStudent().getStudentNum())
                        .phoneNum(sr.getStudent().getPhoneNum())
                        .document(sr.getDocument())
                        .finalResult(sr.getFinalResult())
                        .build())
                .toList();
    }

    private PartType mapPart(String raw) {
        return switch (raw.trim()) {
            case "기획/디자인", "디자인" -> PartType.디자인;
            case "프론트엔드" -> PartType.프론트엔드;
            case "백엔드" -> PartType.백엔드;
            default -> throw new IllegalArgumentException("Unknown part: " + raw);
        };
    }
}
