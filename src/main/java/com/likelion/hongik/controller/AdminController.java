package com.likelion.hongik.controller;

import com.likelion.hongik.dto.request.LoginRequest; // DTO import 확인
import com.likelion.hongik.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자(Admin) 관련 API 요청을 처리하는 컨트롤러입니다.
 * <p>
 * 관리자 로그인 및 관리 기능 전반을 담당합니다.
 * </p>
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin 로그인 API")
public class AdminController {

    /**
     * 관리자 로그인을 처리하는 API 명세입니다.
     * <p>
     * 실제 로그인 로직은 Spring Security의 UsernamePasswordAuthenticationFilter에서 처리되므로,
     * 이 메서드는 Swagger 문서화를 위한 껍데기(Dummy) 메서드입니다.
     * </p>
     *
     * @param loginRequest 로그인 요청 정보 (username, password)
     */
    @PostMapping("/login")
    @Operation(
            summary = "관리자 로그인",
            description = "Spring Security 로그인입니다. **Body를 JSON이 아닌 x-www-form-urlencoded(Form Data) 형식으로 보내주세요.**"
    )
    public void login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "로그인 정보 (Form Data)",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            )
            LoginRequest loginRequest
    ) {
        // 실제 동작은 Security Filter가 처리
    }
}