package com.CSE.KLAB.diary;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryUpdateRequestDto {
    private String content;
    private int backgroundImageIdx;
}