package com.voghbum.aiprovider.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.commons.data.AiOutput;
import com.voghbum.aiprovider.configuration.PromptValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OpenaiProvider implements AiProvider {
    private final RestClient restClient;
    private final PromptValues promptValues;
    private final OpenaiJsonBodyGenerator bodyGenerator;

    @Autowired
    public OpenaiProvider(@Qualifier("aiRestClient") RestClient restClient, OpenaiJsonBodyGenerator bodyGenerator, PromptValues promptValues) {
        this.restClient = restClient;
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

        String body = restClient.post()
                .body(jsonBody)
                .retrieve()
                .body(String.class);
        var result = new AiOutput();
        result.setAiOutput(body);
        result.setRelatedImages(List.of(input));

        return result;
    }

}
