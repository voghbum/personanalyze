package com.voghbum.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.commons.data.AiInputImage;
import com.voghbum.aiprovider.commons.data.AiOutput;
import com.voghbum.application.data.dal.DataAccessLayer;
import com.voghbum.application.data.response.*;
import com.voghbum.instaprovider.data.UserFeed;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Function;

@Service
public class AiService {
    private final AiProvider aiProvider;
    private final DataAccessLayer dal;

    public AiService(AiProvider aiProvider, DataAccessLayer dal) {
        this.aiProvider = aiProvider;
        this.dal = dal;
    }

    public PersonalLifeAnalyzeResponse getPersonalInformation(String username) throws IOException, InterruptedException {
        PersonalLifeAnalyzeResponse result = new PersonalLifeAnalyzeResponse();
        UserFeed userFeed = dal.getUserFeed(username, 2);

        var aiInput = new AiInput();
        var allImages = userFeed.getUserPosts();

        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
           var res = new AiInputImage();
           res.setCaption(i.getCaption());
           res.setCommentCount(i.getCommentCount());
           res.setLikeCount(i.getLikeCount());
           return res;
        }).toList();

        aiInput.setImages(userPostToAiImage);

        var aiResult = aiProvider.analyzePersonalInfo(aiInput);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }

    public LoveLifeResponse shipMe(String username) throws IOException, InterruptedException {
        LoveLifeResponse result = new LoveLifeResponse();
        UserFeed userFeed = dal.getUserFeed(username, 2);

        var allImages = userFeed.getUserPosts();

        var roastInput = new AiInput();
        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
            var res = new AiInputImage();
            res.setCaption(i.getCaption());
            res.setCommentCount(i.getCommentCount());
            res.setLikeCount(i.getLikeCount());
            return res;
        }).toList();

        roastInput.setImages(userPostToAiImage);

        var aiResult = aiProvider.analyzeLoveLife(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }

    public ChangeMillionaireResponse millionaireChange(String username) throws IOException, InterruptedException {
        var result = new ChangeMillionaireResponse();
        UserFeed userFeed = dal.getUserFeed(username, 2);

        var allImages = userFeed.getUserPosts();

        var roastInput = new AiInput();
        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
            var res = new AiInputImage();
            res.setCaption(i.getCaption());
            res.setCommentCount(i.getCommentCount());
            res.setLikeCount(i.getLikeCount());
            return res;
        }).toList();

        roastInput.setImages(userPostToAiImage);

        var aiResult = aiProvider.analyzeMillionaireChange(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }

    public SimilarCelebResponse similarCeleb(String username) throws IOException, InterruptedException {
        var result = new SimilarCelebResponse();
        UserFeed userFeed = dal.getUserFeed(username, 2);

        var allImages = userFeed.getUserPosts();

        var roastInput = new AiInput();
        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
            var res = new AiInputImage();
            res.setCaption(i.getCaption());
            res.setCommentCount(i.getCommentCount());
            res.setLikeCount(i.getLikeCount());
            return res;
        }).toList();

        roastInput.setImages(userPostToAiImage);

        var aiResult = aiProvider.analyzeSimilarCeleb(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }

    public StrengthAndWeaknessResponse strengthAndWeakness(String username) throws IOException, InterruptedException {
        var result = new StrengthAndWeaknessResponse();
        UserFeed userFeed = dal.getUserFeed(username, 2);

        var allImages = userFeed.getUserPosts();

        var roastInput = new AiInput();
        var userPostToAiImage = allImages.stream().limit(20).map(i -> {
            var res = new AiInputImage();
            res.setCaption(i.getCaption());
            res.setCommentCount(i.getCommentCount());
            res.setLikeCount(i.getLikeCount());
            return res;
        }).toList();

        roastInput.setImages(userPostToAiImage);

        var aiResult = aiProvider.analyzeStrengthAnfWeaknesses(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getAiOutput());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }
}
