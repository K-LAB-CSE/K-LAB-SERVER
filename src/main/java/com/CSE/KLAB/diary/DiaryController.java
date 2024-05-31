package com.CSE.KLAB.diary;

import com.CSE.KLAB.global.BaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public BaseResponse<DiaryResponseDto> saveDiary(@RequestBody DiaryRequestDto requestDto) {
        DiaryResponseDto responseDto = diaryService.saveDiary(requestDto);
        return new BaseResponse<>(responseDto);
    }
    @GetMapping("/{diaryId}")
    public BaseResponse<DiaryResponseDto> getDiary(@PathVariable Long diaryId) {
        DiaryResponseDto responseDto = diaryService.getDiary(diaryId);
        return new BaseResponse<>(responseDto);
    }
    @PutMapping("/{diaryId}")
    public BaseResponse<DiaryResponseDto> updateDiary(@PathVariable Long diaryId, @RequestBody DiaryUpdateRequestDto requestDto) {
        DiaryResponseDto responseDto = diaryService.updateDiary(diaryId, requestDto);
        return new BaseResponse<>(responseDto);
    }
    @GetMapping("/user/{userId}")
    public BaseResponse<List<DiaryResponseDto>> getDiariesByUserId(@PathVariable Long userId) {
        List<DiaryResponseDto> responseDtoList = diaryService.getDiariesByUserId(userId);
        return new BaseResponse<>(responseDtoList);
    }
}