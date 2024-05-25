package com.CSE.KLAB.diary;

import com.CSE.KLAB.member.Member;
import com.CSE.KLAB.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveDiary(DiaryRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 유저 아이디입니다."));

        Diary diary = new Diary();
        diary.setMember(member);
        diary.setContent(requestDto.getContent());

        Diary savedDiary = diaryRepository.save(diary);
        return savedDiary.getDiaryId();
    }
}