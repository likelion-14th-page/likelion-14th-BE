package com.likelion.hongik.service;


import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.enums.ResultType;
import com.likelion.hongik.repository.StudentRepository;
import com.likelion.hongik.repository.StudentResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.likelion.hongik.domain.enums.SmsType;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsFinalService {

    private final DefaultMessageService messageService;
    private final StudentResultRepository studentResultRepository;
    private final SmsTemplateService smsTemplateService;


    @Value("${coolsms.from-number}")
    private String fromNumber;

    // 최종 결과 발송 -> 서류 합격자에 한해서 발송하기
    public int sendNoticeToDocuments(SmsType type)
    {
        String text = smsTemplateService.buildMessage(type);

        //서류 합격자조회
        List<StudentResult> passDocumentStudents =
                studentResultRepository.findAllByDocument(ResultType.합격);

        if (passDocumentStudents.isEmpty()) return 0;

        // 메시지 리스트 생성 (StudentResult -> Student -> phoneNum)
        List<Message> messageList = passDocumentStudents.stream()
                .map(StudentResult::getStudent)
                .filter(student -> student != null)
                .filter(student -> student.getPhoneNum() != null && !student.getPhoneNum().isBlank())
                .map(student -> {
                    Message message = new Message();
                    message.setFrom(fromNumber);
                    message.setTo(student.getPhoneNum().replace("-", ""));
                    message.setText(text);
                    return message;
                })
                .toList();

        if (messageList.isEmpty()) return 0;

        try {
            // 그룹 발송 실행
            MultipleDetailMessageSentResponse response = messageService.send(messageList,false,true);

            int total = messageList.size();
            int failCount = response.getFailedMessageList() == null ? 0 : response.getFailedMessageList().size();
            int successCount = total - failCount;

            if (response.getFailedMessageList() != null) {
                response.getFailedMessageList().forEach(fail ->
                        log.error("[SMS 접수실패] 번호: {}, 사유: {}", fail.getTo(), fail.getStatusMessage())
                );
            }

            log.info("[SMS 통계] 요청: {}, 접수성공: {}, 접수실패: {}", total, successCount, failCount);

            return successCount;

        } catch (Exception e) {
            log.error("[SMS] 발송 중 시스템 오류 발생", e);
            return 0;
        }

    }

}
