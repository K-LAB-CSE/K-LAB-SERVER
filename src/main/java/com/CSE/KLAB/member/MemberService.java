package com.CSE.KLAB.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveMember(MemberRequestDto requestDto) {
        Member member = new Member();
        member.setName(requestDto.getName());
        member.setIntroduce(requestDto.getIntroduce());
        member.setProfileImage("https://kusuk-image-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B1%B4%EA%B5%AD%EB%8C%80+%EB%A1%9C%EA%B3%A0.png");

        member.setField(requestDto.getField());

        Member savedMember = memberRepository.save(member);

        MemberResponseDto responseDto = new MemberResponseDto();
        responseDto.setUserId(savedMember.getUserId());
        responseDto.setName(savedMember.getName());
        responseDto.setIntroduce(savedMember.getIntroduce());
        responseDto.setProfileImage(savedMember.getProfileImage());

        return responseDto;
    }

    public MemberResponseDto getMember(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + userId));

        MemberResponseDto responseDto = new MemberResponseDto();
        responseDto.setUserId(member.getUserId());
        responseDto.setName(member.getName());
        responseDto.setIntroduce(member.getIntroduce());
        responseDto.setProfileImage(member.getProfileImage());

        return responseDto;
    }
}