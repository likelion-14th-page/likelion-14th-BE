package com.likelion.hongik.dto.request;

import lombok.Getter;

@Getter
public class GoogleFormWebhookRequest {
    private String part;
    private String name;
    private String email;
    private String studentNum;
    private String phoneNum;
    private String privateNum;
    private String submittedAt;
}
