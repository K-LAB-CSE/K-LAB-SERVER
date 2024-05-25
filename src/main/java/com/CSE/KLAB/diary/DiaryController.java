package com.CSE.KLAB.diary;

import com.CSE.KLAB.global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public BaseResponse<Long> saveDiary(@RequestBody DiaryRequestDto requestDto) {
        Long diaryId = diaryService.saveDiary(requestDto);
        return new BaseResponse<>(diaryId);
    }
}