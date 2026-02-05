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
        [홍익대학교 멋쟁이사자처럼]
        
        안녕하세요, 홍익대학교 멋쟁이사자처럼입니다.
        먼저, 귀중한 시간 내어 14기 리크루팅에 참여해 주셔서 감사합니다.
                    
        이 문자는 전체 발송 문자입니다.
        서류 합격 여부는 아래의 링크에서 확인하실 수 있습니다.
                    
                    
                
        https://www.likelionhongik.com/apply-check
        에서 이름 및 식별 번호를 넣어 서류 합격 여부를 확인해 주세요.
        
        서류 합격자에 한해 면접 일정 및 장소가 함께 공지되었습니다. 
        반드시 확인하시고 변동사항이 생길 경우 오늘까지 010-3120-2936 으로 문의 부탁드립니다.
                    
        홍익대학교 멋쟁이사자처럼 드림
        """;

            case FINAL_NOTICE -> """
        [홍익대학교 멋쟁이사자처럼]
        
        안녕하세요, 홍익대학교 멋쟁이사자처럼입니다.
        먼저, 귀중한 시간 내어 14기 리크루팅에 참여해 주셔서 감사합니다.
                    
        이 문자는 전체 발송 문자입니다.
        최종 합격 여부는 아래의 링크에서 확인하실 수 있습니다.
                    
                    
        https://www.likelionhongik.com/apply-check
        에서 이름 및 식별 번호를 넣어 최종 합격 여부를 확인해 주세요.
           
        “최종 합격자”는 반드시 오늘 6시까지 010-3120-2936으로 확인 여부 회신 부탁드립니다.
        Ex) 000, 확인했습니다   
              
        홍익대학교 멋쟁이사자처럼 드림      
        """;

        };
    }

}
