package com.voghbum.aiprovider.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.commons.data.AiOutput;
import com.voghbum.aiprovider.configuration.PromptValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OpenaiProvider implements AiProvider {
    private final WebClient webClient;
    private final PromptValues promptValues;
    OpenaiJsonBodyGenerator bodyGenerator;

    @Autowired
    public OpenaiProvider(@Qualifier("aiWebClient") WebClient webClient, OpenaiJsonBodyGenerator bodyGenerator, PromptValues promptValues) {
        this.webClient = webClient;
        this.bodyGenerator = bodyGenerator;
        this.promptValues = promptValues;
    }

    @Override
    public AiOutput analyzePersonalInfo(AiInput aiInput) {
        return analyzeIt(aiInput, promptValues.SYS_PERSONAL_INFO_QUEST);
    }

    @Override
    public AiOutput analyzeLoveLife(AiInput input) {
        return analyzeIt(input, promptValues.SYS_LOVE_LIFE_QUEST);
    }

    @Override
    public AiOutput analyzeMillionaireChange(AiInput input) {
        return analyzeIt(input, promptValues.SYS_QUEST_MILLIONAIRE_CHANGE_QUEST);
    }

    @Override
    public AiOutput analyzeSimilarCeleb(AiInput input) {
        return analyzeIt(input, promptValues.SYS_SIMILIAR_CELEB_QUEST);
    }

    @Override
    public AiOutput analyzeStrengthAnfWeaknesses(AiInput input) {
        return analyzeIt(input, promptValues.SYS_STRENGTH_WEAKNESSES_QUEST);
    }

    private AiOutput analyzeIt(AiInput input, String quest) {
        String jsonBody;
        try {
            jsonBody = bodyGenerator.generate(input, quest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String body = webClient.post()
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class).block();
        var result = new AiOutput();
        result.setAiOutput(body);
        result.setRelatedImages(List.of(input));

        return result;
    }

}
