package com.likelion.hongik.controller;

import com.likelion.hongik.dto.response.StudentResultResponseDto;
import com.likelion.hongik.service.StudentResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
@Tag(name = "[User] 지원자 결과 조회 API")
public class StudentResultController {

    private final StudentResultService studentResultService;

    /**
     * 이름 + 고유번호 조합으로 합격자 여부를 조회하는 API
     * @param studentName 이름
     * @param privateNum 고유번호
     * @return StudentResultResponseDto
     */
    @Operation(summary = "결과 조회", description = "이름 + 고유번호 조합으로 합격자 여부를 조회하는 API입니다.")
    @GetMapping
    public StudentResultResponseDto getStudentResult(@RequestParam String studentName, @RequestParam String privateNum) {
        return studentResultService.getStudentResult(studentName, privateNum);
    }
}
