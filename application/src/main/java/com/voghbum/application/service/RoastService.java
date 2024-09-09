package com.voghbum.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.RoastInput;
import com.voghbum.instaprovider.InstaProvider;
import com.voghbum.instaprovider.data.ProfilePostsData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Array;
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


    public Map<String, Object> roast(String username) throws IOException, InterruptedException {
        Map<String, Object> result = new HashMap<>();
        ProfilePostsData data = instaProvider.getProfilePosts(username);

        var allSingleImages = data.getItems().stream()
                .map(p -> p.getImageVersions().getItems())
                .flatMap(List::stream)
                .map(ProfilePostsData.UrlItem::getUrl)
                .sorted((a, b) -> new Random().nextInt(3) - 1)
                .toList();

        var allCarouselImages = data.getItems().stream().map(ProfilePostsData.Item::getCarouselMedia).filter(Objects::nonNull).flatMap(Collection::stream)
                .map(ProfilePostsData.CarouselMedia::getThumbnailUrl).toList();

        var randomizedAllImages = Stream.concat(allCarouselImages.stream(), allSingleImages.stream()).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(randomizedAllImages);

        // İlk 3 elemanı içeren bir sublist oluşturuyoruz
        List<String> selectedImages = randomizedAllImages.subList(0, Math.min(3, allSingleImages.size()));

        var roastImagesFromSelectedImages = selectedImages.stream().map(i -> {
           var res = new RoastInput.RoastImage();
           res.setImageUrl(i);
           return res;
        }).toList();
        result.put("imageUrls", selectedImages);


        var roastInput = new RoastInput();
        roastInput.setImages(roastImagesFromSelectedImages);
        var aiResultJson = aiProvider.generateRoast(roastInput);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(aiResultJson);
        var aiResultText = rootNode.get("choices").get(0).get("message").get("content").asText();

        result.put("aiResult", aiResultText);
        return result;
    }
}
