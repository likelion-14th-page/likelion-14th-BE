package com.likelion.hongik.controller;

import com.likelion.hongik.dto.request.ResultCheckRequest;
import com.likelion.hongik.dto.response.AdminStudentRowDto;
import com.likelion.hongik.service.AdminResultUpdateService;
import com.likelion.hongik.service.AdminStudentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/students")
@Tag(
        name = "관리자 지원자 결과 관리 API",
        description = "관리자 페이지에서 지원자의 서류/최종 합격 여부를 조회 및 수정하는 API입니다."
)
public class AdminResultController {

    private final AdminResultUpdateService adminResultUpdateService;
    private final AdminStudentQueryService adminStudentQueryService;

    @Operation(
            summary = "지원자 전체 조회",
            description = "관리자 페이지 목록 렌더링용 API입니다. part 파라미터가 없으면 전체를 조회하고, part가 있으면 해당 파트만 조회합니다. (예: 전체/기획/디자인/프론트엔드/백엔드)"
    )
    @GetMapping
    public List<AdminStudentRowDto> getStudents(@RequestParam(required = false) String part) {
        return adminStudentQueryService.getStudents(part);
    }
    @Operation(
            summary = "서류 결과 체크(합격/불합격)",
            description = "관리자 페이지 체크박스 동작입니다. checked=true이면 합격, false이면 불합격으로 저장합니다."
    )
    @PatchMapping("/{studentId}/document")
    public void updateDocument(@PathVariable Long studentId, @RequestBody ResultCheckRequest req) {
        adminResultUpdateService.updateDocument(studentId, req.isChecked());
    }

    @Operation(
            summary = "최종 결과 체크(합격/불합격)",
            description = "관리자 페이지 체크박스 동작입니다. checked=true이면 합격, false이면 불합격으로 저장합니다."
    )
    @PatchMapping("/{studentId}/final")
    public void updateFinal(@PathVariable Long studentId, @RequestBody ResultCheckRequest req) {
        adminResultUpdateService.updateFinal(studentId, req.isChecked());
    }
}
