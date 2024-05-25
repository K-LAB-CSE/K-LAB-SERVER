package com.CSE.KLAB.diary;

import com.CSE.KLAB.global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/{diaryId}")
    public BaseResponse<DiaryResponseDto> getDiary(@PathVariable Long diaryId) {
        DiaryResponseDto responseDto = diaryService.getDiary(diaryId);
        return new BaseResponse<>(responseDto);
    }
}