package com.voghbum.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.RoastInput;
import com.voghbum.application.data.response.RoastResponse;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.UserPosts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoastService {
    private final InstaProvider instaProvider;
    private final AiProvider aiProvider;

    public RoastService(InstaProvider instaProvider, AiProvider aiProvider) {
        this.instaProvider = instaProvider;
        this.aiProvider = aiProvider;
    }


    public RoastResponse roast(String username) throws IOException, InterruptedException {
        RoastResponse result = new RoastResponse();
        UserPosts data = instaProvider.getUserInfo(username);

        var allSingleImages = data.getData().getItems().stream()
                .map(p -> p.getImageVersions().getItems())
                .flatMap(List::stream)
                .map(UserPosts.ImageVersion::getUrl)
                .sorted((a, b) -> new Random().nextInt(3) - 1)
                .toList();

        var randomizedAllImages = Stream.concat(allSingleImages.stream(), allSingleImages.stream()).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(randomizedAllImages);

        // İlk 3 elemanı içeren bir sublist oluşturuyoruz
        List<String> selectedImages = randomizedAllImages.subList(0, Math.min(3, allSingleImages.size()));

        var roastImagesFromSelectedImages = selectedImages.stream().map(i -> {
           var res = new RoastInput.RoastImage();
           res.setImageUrl(i);
           return res;
        }).toList();
        result.setImageUrls(selectedImages);


        var roastInput = new RoastInput();
        roastInput.setImages(roastImagesFromSelectedImages);
        var aiResultJson = aiProvider.generateRoast(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResultJson);
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.setAiResult(aiResultText);
        return result;
    }
}
