package com.CSE.KLAB.diary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryResponseDto {
    private Long diaryId;
    private Long userId;
    private String content;
    private int backgroundImageIdx;
}