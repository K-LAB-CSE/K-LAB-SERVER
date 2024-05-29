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

    @PostMapping("/diary")
    public String generateDiary(
            @RequestParam String username,
            @RequestParam String userInput,
            @RequestParam String todayDate) {

        // 프롬프트 문자열 생성
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

        return gptResponse.getChoices().get(0).getMessage().getContent();
    }
}