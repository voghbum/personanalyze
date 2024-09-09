package com.voghbum.aiprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.RoastInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenaiProvider implements AiProvider {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public OpenaiProvider(@Qualifier("aiWebClient") WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public String generateRoast(RoastInput profilePostsData) {
        String jsonBody;
        try {
            jsonBody = generateJsonBody(profilePostsData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return webClient.post()
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    private String generateJsonBody(RoastInput postsData) throws JsonProcessingException {
        var jsonMap = new HashMap<String, Object>();
        jsonMap.put("model", "gpt-4o-mini");
        var messagesList = new ArrayList<>();
        var inMessagesMap = new HashMap<>();
        jsonMap.put("messages", messagesList);
        messagesList.add(inMessagesMap);
        inMessagesMap.put("role", "user");
        var contentList = new ArrayList<>();
        inMessagesMap.put("content", contentList);
        var contentText = new HashMap<>();
        contentText.put("type", "text");
        contentText.put("text", "Bu fotoğraflar bir arkadaşımın sosyal medya fotoğrafları. Bu fotoğrafları kullanarak, onun hakkında komik yorumlar yap. Yorumlar kırıcı olabilir. Fotoğrafları tek tek yorumlama, onun hakkında fotoğraflardan ve karakterinden bahseden küçük bir paragraf yaz.");
        contentList.add(contentText);

        List<String> allImageUrls = postsData.getImages().stream().map(RoastInput.RoastImage::getImageUrl).toList();

        var firstThreeImageOrAll = allImageUrls.size() > 3 ? allImageUrls.subList(0, 3) : allImageUrls;
        for (String imageUrl : firstThreeImageOrAll) {
            var contentImage = new HashMap<>();
            contentImage.put("type", "image_url");
            contentImage.put("image_url", Map.of("url", imageUrl));
            contentList.add(contentImage);
        }

        return objectMapper.writeValueAsString(jsonMap);
    }
}
