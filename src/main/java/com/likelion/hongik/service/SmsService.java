package com.likelion.hongik.service;
import com.likelion.hongik.domain.Student;
import com.likelion.hongik.domain.enums.SmsType;
import com.likelion.hongik.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class SmsService {

    private final DefaultMessageService messageService;
    private final StudentRepository studentRepository;
    private final SmsTemplateService smsTemplateService;

    @Value("${coolsms.from-number}")
    private String fromNumber;

    public int sendNoticeToAll(SmsType type) {
        String text = smsTemplateService.buildMessage(type);

        //지원자 전원조회
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) return 0;

        //메시지 리스트 생성
        List<Message> messageList = students.stream()
                .filter(s -> s.getPhoneNum() != null) // 번호 없는 학생 제외
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
