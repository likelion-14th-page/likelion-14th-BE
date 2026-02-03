package com.likelion.hongik.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRequestDto {
    private String meetingDate;
    private String meetingTime;
    private String location;
}
