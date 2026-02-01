package com.likelion.hongik.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

@Getter
public class LoginRequest {

    // "loginId"라는 이름으로 들어와도 username에 드감
    @JsonAlias("loginId")
    private String username;
    private String password;
}