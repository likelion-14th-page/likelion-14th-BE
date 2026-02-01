package com.likelion.hongik.controller;

import com.likelion.hongik.dto.response.StudentResultResponseDto;
import com.likelion.hongik.service.StudentResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
@Tag(name = "지원자 결과 조회 API")
public class StudentResultController {

    private final StudentResultService studentResultService;

    @GetMapping
    public StudentResultResponseDto getStudentResult(@RequestParam String studentName, @RequestParam String privateNum) {
        return studentResultService.getStudentResult(studentName, privateNum);
    }
}
