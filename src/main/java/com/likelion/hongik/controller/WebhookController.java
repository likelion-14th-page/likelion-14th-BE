package com.likelion.hongik.controller;

import com.likelion.hongik.dto.request.GoogleFormWebhookRequest;
import com.likelion.hongik.service.StudentImportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webhook")
@Tag(
        name = "외부 연동 Webhook API",
        description = "Google Form/Sheet 제출 데이터를 수신하여 지원자 정보를 DB에 저장하는 Webhook API입니다."
)
public class WebhookController {

    private final StudentImportService studentImportService;

    @Value("${webhook.token}")
    private String webhookToken;

    @Operation(
            summary = "구글폼 제출 수신(Webhook)",
            description = "Google Apps Script에서 호출하는 Webhook입니다. 지원자(part, name, studentNum, phoneNum, privateNum)를 수신해 DB에 저장합니다. X-WEBHOOK-TOKEN 헤더로 인증합니다."
    )
    @PostMapping("/google-form")
    @ResponseStatus(HttpStatus.OK)
    public void receiveGoogleForm(
            @RequestHeader(value = "X-WEBHOOK-TOKEN", required = false) String token,
            @RequestBody GoogleFormWebhookRequest request
    ) {
        if (token == null || !token.equals(webhookToken)) {
            throw new IllegalArgumentException("Invalid webhook token");
        }
        studentImportService.importOne(request);
    }
}
