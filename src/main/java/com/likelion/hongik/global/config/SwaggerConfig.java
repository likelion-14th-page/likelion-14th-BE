package com.likelion.hongik.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {
        Info info = new Info()
                .title("멋사 14기 홈페이지 API")
                .description("멋사 14기 홈페이지 스웨거")
                .version("1.0.0");

        // 세션 기반 인증(JSESSIONID 쿠키)을 위한 설정입니다.
        String sessionSchemeName = "SessionAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(sessionSchemeName);

        Components components = new Components()
                .addSecuritySchemes(sessionSchemeName, new SecurityScheme()
                        .name("JSESSIONID") // Spring Security에서 사용하는 기본 세션 쿠키 이름
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.COOKIE));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}