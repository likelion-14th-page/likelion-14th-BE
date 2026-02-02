package com.likelion.hongik.controller;


import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.enums.ResultType;
import com.likelion.hongik.domain.enums.SmsType;
import com.likelion.hongik.service.SmsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.service.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/result/message")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    // 서류 결과 전원 발송
    @Tag(name = "서류 결과 전원 발송 SMS api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/document")
    public String sendDocumentNotice() {
        int successCount = smsService.sendNoticeToAll(SmsType.DOCUMENT_NOTICE);
        return successCount + "건의 서류 결과 문자가 성공적으로 발송되었습니다.";
    }

    // 최종 결과 전원 발송

    @Tag(name = "최종 결과 전원 발송 SMS api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/final")
    public String sendFinalNotice() {
        int successCount = smsService.sendNoticeToAll(SmsType.FINAL_NOTICE);
        return successCount + "건의 최종 결과 문자가 성공적으로 발송되었습니다.";
    }

}
