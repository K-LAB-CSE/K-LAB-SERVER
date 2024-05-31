package com.CSE.KLAB.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private Long userId;
    private String name;
    private String introduce;
    private String profileImage;
}