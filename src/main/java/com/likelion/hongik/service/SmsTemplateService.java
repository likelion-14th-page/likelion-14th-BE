package com.likelion.hongik.service;


import com.likelion.hongik.domain.enums.SmsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsTemplateService {

    public String buildMessage(SmsType type) {
        return switch (type) {
            case DOCUMENT_NOTICE -> """
        [멋쟁이사자처럼 홍익대학교]
        [Web발신]

        이 문자는 전체 발송 문자입니다.
        서류 합격 여부는 아래의 링크에서 확인하실 수 있습니다.

        안녕하세요, 멋쟁이사자처럼 홍익대학교입니다.
        귀중한 시간 내어 14기 리크루팅에 참여해 주셔서 감사합니다.

        서류 결과가 발표되었으니
        
        https://www.likelionhongik.com
        에서 확인해 주세요.

        홍익대학교 멋쟁이사자처럼 드림
        """;

            case FINAL_NOTICE -> """
        [멋쟁이사자처럼 홍익대학교]
        [Web발신]

        이 문자는 전체 발송 문자입니다.
        최종 합격 여부는 아래의 링크에서 확인하실 수 있습니다.

        안녕하세요, 멋쟁이사자처럼 홍익대학교입니다.
        귀중한 시간 내어 14기 리크루팅에 참여해 주셔서 감사합니다.
        최종 합격 결과가 발표되었습니다.

        아래 링크에서 최종 결과를 확인해 주세요.

        https://www.likelionhongik.com
        
        다시 한 번 지원해주셔서 감사합니다.
        
        홍익대학교 멋쟁이사자처럼 드림
        """;

        };
    }

}
