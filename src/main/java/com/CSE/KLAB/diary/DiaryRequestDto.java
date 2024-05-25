package com.CSE.KLAB.diary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryRequestDto {
    private Long userId;
    private String content;
}
