package com.likelion.hongik.controller;

import com.likelion.hongik.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin 로그인 API")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/login") // 로그인 '페이지'를 보여주는 역할
    public String loginPage() {
        return "admin/login"; // src/main/resources/templates/admin/login.html 파일을 찾음
    }
}