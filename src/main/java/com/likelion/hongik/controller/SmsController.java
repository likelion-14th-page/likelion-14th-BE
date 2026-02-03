package com.likelion.hongik.controller;


import com.likelion.hongik.domain.enums.SmsType;
import com.likelion.hongik.service.SmsDocumentService;
import com.likelion.hongik.service.SmsFinalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/message")
@RequiredArgsConstructor
@Tag(name = "[Admin] SMS API")
public class SmsController {
    private final SmsDocumentService smsDocumentService;
    private final SmsFinalService smsFinalService;

    /**
     *
     * @return successCount + "건의 서류 결과 문자가 성공적으로 발송되었습니다."
     */
    // 서류 결과 전원 발송
    @Operation(summary = "서류 결과 전원 발송")
    @PostMapping("/document")
    public String sendDocumentNotice() {
        int successCount = smsDocumentService.sendNoticeToAll(SmsType.DOCUMENT_NOTICE);
        return successCount + "건의 서류 결과 문자가 성공적으로 발송되었습니다.";
    }

    /**
     *
     * @return successCount + "건의 서류 결과 문자가 성공적으로 발송되었습니다."
     */
    // 최종 결과 전원 발송
    @Operation(summary = "최종 결과 전원 발송")
    @PostMapping("/final")
    public String sendFinalNotice() {
        int successCount = smsFinalService.sendNoticeToDocuments(SmsType.FINAL_NOTICE);
        return successCount + "건의 최종 결과 문자가 성공적으로 발송되었습니다.";
    }

}
