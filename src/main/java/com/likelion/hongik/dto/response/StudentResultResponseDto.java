package com.likelion.hongik.dto.response;

import com.likelion.hongik.domain.enums.ResultType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResultResponseDto {
    private String message;
    private Long studentId;
    private Boolean addition;
    private String studentName;
    private ResultType document;
    private ResultType finalResult;
    private String meetingDate;
    private String meetingTime;
    private String location;
}
