package com.voghbum.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.RoastInput;
import com.voghbum.aiprovider.commons.data.ShipInput;
import com.voghbum.application.data.dal.DataAccessLayer;
import com.voghbum.application.data.response.RoastResponse;
import com.voghbum.application.data.response.ShipResponse;
import com.voghbum.instaprovider.data.UserFeed;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoastService {
    private final AiProvider aiProvider;
    private final DataAccessLayer dal;

    public RoastService(AiProvider aiProvider, DataAccessLayer dal) {
        this.aiProvider = aiProvider;
        this.dal = dal;
    }

    public RoastResponse roastMe(String username) throws IOException, InterruptedException {
        RoastResponse result = new RoastResponse();
        UserFeed userFeed = dal.getUserFeed(username);

        var allImages = userFeed.getUserPosts().stream().map(UserFeed.UserPost::getThumbnailUrl).toList();

        var roastImagesFromSelectedImages = allImages.stream().map(i -> {
           var res = new RoastInput.RoastImage();
           res.setImageUrl(i);
           return res;
        }).toList();

        result.setImageUrls(allImages);

        var roastInput = new RoastInput();
        roastInput.setImages(roastImagesFromSelectedImages.subList(0, 3));
        var aiResult = aiProvider.roast(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getRoastText());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }

    public ShipResponse shipMe(String username) throws IOException, InterruptedException {
        ShipResponse result = new ShipResponse();
        UserFeed userFeed = dal.getUserFeed(username);

        var allImages = userFeed.getUserPosts().stream().map(UserFeed.UserPost::getThumbnailUrl).toList();


        var roastInput = new ShipInput();
        roastInput.setImageUrls(allImages.subList(0, 3));
        var aiResult = aiProvider.ship(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResult.getShipText());
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }
}
