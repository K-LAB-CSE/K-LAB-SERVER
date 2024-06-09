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

        // 첫 번째 프롬프트: 일기 생성
        StringBuilder promptBuilder1 = new StringBuilder();
        promptBuilder1.append("This system is ai auto writing diary\n");
        promptBuilder1.append("Written person name is ").append(username).append(", write name at the end of diary.\n");
        promptBuilder1.append("I want you to write diary with information ").append(userInput).append("\n");
        promptBuilder1.append("Also, write down date with information, ").append(todayDate).append("\n");
        promptBuilder1.append("In the diary, change lines every 20 words.\n");

        GPTRequest gptRequest1 = new GPTRequest(
                model, promptBuilder1.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse1 = restTemplate.postForObject(
                apiUrl, gptRequest1, GPTResponse.class);

        String diaryContent = gptResponse1.getChoices().get(0).getMessage().getContent();

        // 두 번째 프롬프트: 일기 번역
        StringBuilder promptBuilder2 = new StringBuilder();
        promptBuilder2.append("Translate this diary in korean language\n");
        promptBuilder2.append(diaryContent);

        GPTRequest gptRequest2 = new GPTRequest(
                model, promptBuilder2.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse2 = restTemplate.postForObject(
                apiUrl, gptRequest2, GPTResponse.class);

        return gptResponse2.getChoices().get(0).getMessage().getContent();
    }

    // 네덜란드어 일기 생성 컨트롤러
    @PostMapping("/diary/dutch")
    public String generateDutchDiary(
            @RequestParam String username,
            @RequestParam String userInput,
            @RequestParam String todayDate) {

        // 일기 생성 프롬프트 초기화
        StringBuilder promptBuilder1 = new StringBuilder();
        promptBuilder1.append("This system is ai auto writing diary\n");
        promptBuilder1.append("Written person name is ").append(username).append(", write name at the end of diary.\n");
        promptBuilder1.append("I want you to write diary with information ").append(userInput).append("\n");
//        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder1.append("Also, write down date with information, ").append(todayDate).append("\n");
//        promptBuilder.append("okay is there any information i need to know?\n");
        promptBuilder1.append("In the diary, change lines every 20 words.\n");

        GPTRequest gptRequest1 = new GPTRequest(
                model, promptBuilder1.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse1 = restTemplate.postForObject(
                apiUrl, gptRequest1, GPTResponse.class);

        String diaryContent = gptResponse1.getChoices().get(0).getMessage().getContent();

        // 두 번째 프롬프트: 일기 번역
        StringBuilder promptBuilder2 = new StringBuilder();
        promptBuilder2.append("Translate this diary in dutch language\n");
        promptBuilder2.append(diaryContent);

        GPTRequest gptRequest2 = new GPTRequest(
                model, promptBuilder2.toString(), 1, 256, 1, 2, 2);

        GPTResponse gptResponse2 = restTemplate.postForObject(
                apiUrl, gptRequest2, GPTResponse.class);

        return gptResponse2.getChoices().get(0).getMessage().getContent();
    }
}