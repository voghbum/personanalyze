package com.voghbum.aiprovider.commons;

import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.commons.data.AiOutput;
import org.springframework.stereotype.Service;

@Service
public interface AiProvider {
    AiOutput analyzePersonalInfo(AiInput input);
    AiOutput analyzeLoveLife(AiInput input);
    AiOutput analyzeMillionaireChange(AiInput input);
    AiOutput analyzeSimilarCeleb(AiInput input);
    AiOutput analyzeStrengthAnfWeaknesses(AiInput input);
}
