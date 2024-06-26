package com.CSE.KLAB.diary;

import com.CSE.KLAB.member.Member;
import com.CSE.KLAB.member.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public DiaryResponseDto saveDiary(DiaryRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Diary diary = new Diary();
        diary.setMember(member);
        diary.setContent(requestDto.getContent());
        diary.setBackgroundImageIdx(requestDto.getBackgroundImageIdx());

        Diary savedDiary = diaryRepository.save(diary);

        DiaryResponseDto responseDto = new DiaryResponseDto();
        responseDto.setDiaryId(savedDiary.getDiaryId());
        responseDto.setUserId(savedDiary.getMember().getUserId());
        responseDto.setContent(savedDiary.getContent());
        responseDto.setBackgroundImageIdx(savedDiary.getBackgroundImageIdx());

        return responseDto;
    }

    @Transactional
    public DiaryResponseDto getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        DiaryResponseDto responseDto = new DiaryResponseDto();
        responseDto.setDiaryId(diary.getDiaryId());
        responseDto.setUserId(diary.getMember().getUserId());
        responseDto.setContent(diary.getContent());
        responseDto.setBackgroundImageIdx(diary.getBackgroundImageIdx());

        return responseDto;
    }

    @Transactional
    public DiaryResponseDto updateDiary(Long diaryId, DiaryUpdateRequestDto requestDto) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        diary.setContent(requestDto.getContent());
        diary.setBackgroundImageIdx(requestDto.getBackgroundImageIdx());

        Diary updatedDiary = diaryRepository.save(diary);

        DiaryResponseDto responseDto = new DiaryResponseDto();
        responseDto.setDiaryId(updatedDiary.getDiaryId());
        responseDto.setUserId(updatedDiary.getMember().getUserId());
        responseDto.setContent(updatedDiary.getContent());
        responseDto.setBackgroundImageIdx(updatedDiary.getBackgroundImageIdx());

        return responseDto;
    }

    @Transactional
    public List<DiaryResponseDto> getDiariesByUserId(Long userId) {
        List<Diary> diaries = diaryRepository.findByMemberUserId(userId);

        return diaries.stream().map(diary -> {
            DiaryResponseDto responseDto = new DiaryResponseDto();
            responseDto.setDiaryId(diary.getDiaryId());
            responseDto.setUserId(diary.getMember().getUserId());
            responseDto.setContent(diary.getContent());
            responseDto.setBackgroundImageIdx(diary.getBackgroundImageIdx());
            return responseDto;
        }).collect(Collectors.toList());
    }
}