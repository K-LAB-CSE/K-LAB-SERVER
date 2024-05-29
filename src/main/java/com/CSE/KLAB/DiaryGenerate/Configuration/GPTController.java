package com.CSE.KLAB.DiaryGenerate.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GPTController {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;


    // 한국어 일기 생성 컨트롤러
    @PostMapping("/diary/korean")
    public String generateKoreanDiary(
            @RequestParam String username,
            @RequestParam String userInput,
            @RequestParam String todayDate) {

        // 일기 생성 프롬프트 초기화
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("This system is ai auto writing diary\n");
        promptBuilder.append("Written person name is ").append(username).append(", write name at the end of diary.\n");
        promptBuilder.append("I want you to write diary with information ").append(userInput).append("\n");
        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder.append("Also, write down date with information, ").append(todayDate).append("\n");
        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder.append("In the diary, change lines every 20 words.\n");
        promptBuilder.append("okay is there any information i need to know?\n");

        GPTRequest gptRequest = new GPTRequest(
                model, promptBuilder.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse = restTemplate.postForObject(
                apiUrl, gptRequest, GPTResponse.class);

        // 한국어 일기 생성 프롬프트 초기화
        StringBuilder promptKoreanBuilder = new StringBuilder();
        promptKoreanBuilder.append("translate this diary in korean language: ").append(gptResponse.getChoices().get(0).getMessage().getContent()).append("\n");
        promptKoreanBuilder.append("okay I will translate this diary in korean word!\n");

        GPTRequest gptKoreanRequest = new GPTRequest(
                model, promptKoreanBuilder.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptKoreanResponse = restTemplate.postForObject(
                apiUrl, gptKoreanRequest, GPTResponse.class);

        return gptKoreanResponse.getChoices().get(0).getMessage().getContent();
    }

    // 네덜란드어 일기 생성 컨트롤러
    @PostMapping("/diary/dutch")
    public String generateDutchDiary(
            @RequestParam String username,
            @RequestParam String userInput,
            @RequestParam String todayDate) {

        // 일기 생성 프롬프트 초기화
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("This system is ai auto writing diary\n");
        promptBuilder.append("Written person name is ").append(username).append(", write name at the end of diary.\n");
        promptBuilder.append("I want you to write diary with information ").append(userInput).append("\n");
        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder.append("Also, write down date with information, ").append(todayDate).append("\n");
        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder.append("In the diary, change lines every 20 words.\n");
        promptBuilder.append("okay is there any information i need to know?\n");

        GPTRequest gptRequest = new GPTRequest(
                model, promptBuilder.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse = restTemplate.postForObject(
                apiUrl, gptRequest, GPTResponse.class);

        // 네덜란드어 일기 생성 프롬프트 초기화
        StringBuilder promptDutchBuilder = new StringBuilder();
        promptDutchBuilder.append("translate this diary in dutch language: ").append(gptResponse.getChoices().get(0).getMessage().getContent()).append("\n");
        promptDutchBuilder.append("okay I will translate this diary in dutch word!\n");

        GPTRequest gptDutchRequest = new GPTRequest(
                model, promptDutchBuilder.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptDutchResponse = restTemplate.postForObject(
                apiUrl, gptDutchRequest, GPTResponse.class);

        return gptDutchResponse.getChoices().get(0).getMessage().getContent();
    }
}