package com.CSE.KLAB.member;


import com.CSE.KLAB.global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public BaseResponse<MemberResponseDto> saveMember(@RequestBody MemberRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.saveMember(requestDto);
        return new BaseResponse<>(responseDto);
    }
    @GetMapping("/{userId}")
    public BaseResponse<MemberResponseDto> getMember(@PathVariable Long userId) {
        MemberResponseDto responseDto = memberService.getMember(userId);
        return new BaseResponse<>(responseDto);
    }
    @GetMapping("/name/{name}")
    public BaseResponse<MemberResponseDto> getMemberByName(@PathVariable String name) {
        MemberResponseDto responseDto = memberService.getMemberByName(name);
        return new BaseResponse<>(responseDto);
    }
}