package com.voghbum.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.commons.data.AiInputImage;
import com.voghbum.aiprovider.commons.data.AiOutput;
import com.voghbum.application.data.entity.AiOutputEntity;
import com.voghbum.application.data.entity.AiOutputType;
import com.voghbum.application.data.repository.AiOutputRepository;
import com.voghbum.application.data.response.*;
import com.voghbum.instaprovider.data.UserFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AiService {
    private static final Logger LOG = LoggerFactory.getLogger(AiService.class);
    private final AiProvider aiProvider;
    private final AiOutputRepository aiOutputRepository;
    private final AnalyzeService analyzeService;

    public AiService(AiProvider aiProvider, AiOutputRepository aiOutputRepository, AnalyzeService analyzeService) {
        this.aiProvider = aiProvider;
        this.aiOutputRepository = aiOutputRepository;
        this.analyzeService = analyzeService;
    }

    public PersonalLifeAnalyzeResponse getPersonalInformation(String username) throws IOException, InterruptedException {
        PersonalLifeAnalyzeResponse result = new PersonalLifeAnalyzeResponse();
        var fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.PERSONAL_INFORMATION);
        AiOutput aoFromDb = getAiOutputIfSufficient(username, fromDb);
        if(aoFromDb != null) {
            var aiResultText = getOnlyAiText(aoFromDb);
            result.setAiResult(aiResultText);
        }

        var aiKey = new AiKey(username, AiOutputType.PERSONAL_INFORMATION);
        Object lock = LockMapsForAiService.LOCK_FOR_AI_OUTPUT.computeIfAbsent(aiKey, k -> new Object());
        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.PERSONAL_INFORMATION);
            aoFromDb = getAiOutputIfSufficient(username, fromDb);
            if(aoFromDb != null) {
                var aiResultText = getOnlyAiText(aoFromDb);
                result.setAiResult(aiResultText);
                return result;
            }

            UserFeed userFeed = analyzeService.getUserFeed(username, 2);
            var allImages = userFeed.getUserPosts();
            var aiInput = convertAiInput(allImages);
            var aiResult = aiProvider.analyzePersonalInfo(aiInput);
            var aiResultText = getOnlyAiText(aiResult);
            result.setAiResult(aiResultText);

            var persisting = new AiOutputEntity();
            persisting.setUsername(username);
            persisting.setOutputType(AiOutputType.PERSONAL_INFORMATION);
            persisting.setAiOutput(aiResult);
            aiOutputRepository.save(persisting);

            LockMapsForAiService.LOCK_FOR_AI_OUTPUT.remove(aiKey);
        }
        return result;
    }

    public LoveLifeResponse loveLife(String username) throws IOException, InterruptedException {
        LoveLifeResponse result = new LoveLifeResponse();
        var fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.LOVE_LIFE);
        AiOutput aoFromDb = getAiOutputIfSufficient(username, fromDb);
        if(aoFromDb != null) {
            var aiResultText = getOnlyAiText(aoFromDb);
            result.setAiResult(aiResultText);
            return result;
        }

        var aiKey = new AiKey(username, AiOutputType.LOVE_LIFE);
        Object lock = LockMapsForAiService.LOCK_FOR_AI_OUTPUT.computeIfAbsent(aiKey, k -> new Object());

        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.LOVE_LIFE);
            aoFromDb = getAiOutputIfSufficient(username, fromDb);
            if(aoFromDb != null) {
                var aiResultText = getOnlyAiText(aoFromDb);
                result.setAiResult(aiResultText);
                return result;
            }

            UserFeed userFeed = analyzeService.getUserFeed(username, 2);
            var allImages = userFeed.getUserPosts();
            var aiInput = convertAiInput(allImages);
            var aiResult = aiProvider.analyzeLoveLife(aiInput);
            var aiText = getOnlyAiText(aiResult);
            result.setAiResult(aiText);
            var persisting = new AiOutputEntity();

            persisting.setUsername(username);
            persisting.setOutputType(AiOutputType.LOVE_LIFE);
            persisting.setAiOutput(aiResult);
            aiOutputRepository.save(persisting);

            LockMapsForAiService.LOCK_FOR_AI_OUTPUT.remove(aiKey);
        }
        return result;
    }

    public ChangeMillionaireResponse millionaireChange(String username) throws IOException, InterruptedException {
        var result = new ChangeMillionaireResponse();
        var fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.MILLIONAIRE_CHANGE);
        AiOutput aoFromDb = getAiOutputIfSufficient(username, fromDb);
        if(aoFromDb != null) {
            var aiResultText = getOnlyAiText(aoFromDb);
            result.setAiResult(aiResultText);
            return result;
        }

        var aiKey = new AiKey(username, AiOutputType.MILLIONAIRE_CHANGE);
        Object lock = LockMapsForAiService.LOCK_FOR_AI_OUTPUT.computeIfAbsent(aiKey, k -> new Object());
        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.MILLIONAIRE_CHANGE);
            aoFromDb = getAiOutputIfSufficient(username, fromDb);
            if(aoFromDb != null) {
                var aiResultText = getOnlyAiText(aoFromDb);
                result.setAiResult(aiResultText);
                return result;
            }

            UserFeed userFeed = analyzeService.getUserFeed(username, 2);
            var allImages = userFeed.getUserPosts();
            var aiInput = convertAiInput(allImages);
            var aiResult = aiProvider.analyzeMillionaireChange(aiInput);
            var aiText = getOnlyAiText(aiResult);
            result.setAiResult(aiText);

            var persisting = new AiOutputEntity();
            persisting.setUsername(username);
            persisting.setOutputType(AiOutputType.MILLIONAIRE_CHANGE);
            persisting.setAiOutput(aiResult);
            aiOutputRepository.save(persisting);

            LockMapsForAiService.LOCK_FOR_AI_OUTPUT.remove(aiKey);
        }
        return result;
    }

    public SimilarCelebResponse similarCeleb(String username) throws IOException, InterruptedException {
        var result = new SimilarCelebResponse();
        var fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.SIMILAR_CELEB);
        AiOutput aoFromDb = getAiOutputIfSufficient(username, fromDb);
        if(aoFromDb != null) {
            var aiResultText = getOnlyAiText(aoFromDb);
            result.setAiResult(aiResultText);
            return result;
        }

        var aiKey = new AiKey(username, AiOutputType.SIMILAR_CELEB);
        Object lock = LockMapsForAiService.LOCK_FOR_AI_OUTPUT.computeIfAbsent(aiKey, k -> new Object());
        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.SIMILAR_CELEB);
            aoFromDb = getAiOutputIfSufficient(username, fromDb);
            if(aoFromDb != null) {
                var aiResultText = getOnlyAiText(aoFromDb);
                result.setAiResult(aiResultText);
                return result;
            }

            UserFeed userFeed = analyzeService.getUserFeed(username, 2);
            var allImages = userFeed.getUserPosts();
            var aiInput = convertAiInput(allImages);
            var aiResult = aiProvider.analyzeSimilarCeleb(aiInput);
            var aiResultText = getOnlyAiText(aiResult);
            result.setAiResult(aiResultText);

            var persisting = new AiOutputEntity();
            persisting.setUsername(username);
            persisting.setOutputType(AiOutputType.SIMILAR_CELEB);
            persisting.setAiOutput(aiResult);
            aiOutputRepository.save(persisting);

            LockMapsForAiService.LOCK_FOR_AI_OUTPUT.remove(aiKey);
        }

        return result;
    }

    public StrengthAndWeaknessResponse strengthAndWeakness(String username) throws IOException, InterruptedException {
        var result = new StrengthAndWeaknessResponse();
        var fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.STRENGTH_WEAKNESS);
        AiOutput aoFromDb = getAiOutputIfSufficient(username, fromDb);
        if(aoFromDb != null) {
            var aiResultText = getOnlyAiText(aoFromDb);
            result.setAiResult(aiResultText);
            return result;
        }

        var aiKey = new AiKey(username, AiOutputType.STRENGTH_WEAKNESS);
        Object lock = LockMapsForAiService.LOCK_FOR_AI_OUTPUT.computeIfAbsent(aiKey, k -> new Object());
        synchronized (lock) {
            fromDb = aiOutputRepository.findByUsernameAndOutputType(username, AiOutputType.STRENGTH_WEAKNESS);
            aoFromDb = getAiOutputIfSufficient(username, fromDb);
            if(aoFromDb != null) {
                var aiResultText = getOnlyAiText(aoFromDb);
                result.setAiResult(aiResultText);
                return result;
            }

            UserFeed userFeed = analyzeService.getUserFeed(username, 2);
            var allImages = userFeed.getUserPosts();
            var aiInput = convertAiInput(allImages);
            var aiResult = aiProvider.analyzeStrengthAnfWeaknesses(aiInput);
            var aiResultText = getOnlyAiText(aiResult);
            result.setAiResult(aiResultText);

            var persisting = new AiOutputEntity();
            persisting.setUsername(username);
            persisting.setOutputType(AiOutputType.STRENGTH_WEAKNESS);
            persisting.setAiOutput(aiResult);
            aiOutputRepository.save(persisting);

            LockMapsForAiService.LOCK_FOR_AI_OUTPUT.remove(aiKey);
        }

        return result;
    }


    private AiOutput getAiOutputIfSufficient(String nickName, Optional<AiOutputEntity> fromDb) {
        if(fromDb.isPresent()) {
            try {
                return fromDb.get().getAiOutput();
            } catch (Exception e) {
                LOG.error("Exception while decoding data fetched from DB for username: {} ", nickName, e);
                return null;
            }
        }
        return null;
    }

    private static AiInput convertAiInput(List<UserFeed.UserPost> allImages) {
        var roastInput = new AiInput();
        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
            var res = new AiInputImage();
            res.setCaption(i.getCaption());
            res.setCommentCount(i.getCommentCount());
            res.setLikeCount(i.getLikeCount());
            return res;
        }).toList();

        roastInput.setImages(userPostToAiImage);
        return roastInput;
    }

    private static String getOnlyAiText(AiOutput aiResult) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        return rootNode.get("choices").get(0).get("message").get("content").asText();
    }
}
