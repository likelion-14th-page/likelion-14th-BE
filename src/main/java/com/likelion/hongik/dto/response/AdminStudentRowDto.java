package com.likelion.hongik.dto.response;

import com.likelion.hongik.domain.enums.PartType;
import com.likelion.hongik.domain.enums.ResultType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentRowDto {
    private Long studentId;
    private PartType part;
    private String name;
    private String studentNum;
    private String phoneNum;

    private ResultType document;
    private ResultType finalResult;
}
