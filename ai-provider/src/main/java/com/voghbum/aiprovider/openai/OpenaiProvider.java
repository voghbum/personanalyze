package com.voghbum.aiprovider.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.RoastInput;
import com.voghbum.aiprovider.commons.data.RoastOutput;
import com.voghbum.aiprovider.commons.data.ShipInput;
import com.voghbum.aiprovider.commons.data.ShipOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenaiProvider implements AiProvider {
    private final Environment environment;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public OpenaiProvider(@Qualifier("aiWebClient") WebClient webClient, ObjectMapper objectMapper, Environment environment) {
        this.environment = environment;
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public RoastOutput roast(RoastInput roastInput) {
        String jsonBody;
        try {
            jsonBody = generateJsonBodyForRoast(roastInput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String body = webClient.post()
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class).block();
        var result = new RoastOutput();
        result.setRoastText(body);
        result.setRelatedImages(roastInput.getImages().stream().map(RoastInput.RoastImage::getImageUrl).toList());

        return result;
    }

    @Override
    public ShipOutput ship(ShipInput shipInput) {
        String jsonBody;
        try {
            jsonBody = generateJsonBodyForShip(shipInput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String body = webClient.post()
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class).block();
        var result = new ShipOutput();
        result.setShipText(body);
        result.setRelatedImages(shipInput.getImageUrls());

        return result;
    }

    private String generateJsonBodyForRoast(RoastInput postsData) throws JsonProcessingException {

    }

    private String generateJsonBodyForShip(ShipInput shipInput) throws JsonProcessingException {
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
        contentText.put("text", "Sen, sivri dilli ve kışkırtıcı tarzınla tanınan profesyonel bir yorumcusun. Görevin, insanların intagram profillerindeki fotoğraflara bakarak onları komik ve absürt kişiler ve karakterlerle eşleştirmek. Bu karakterler çizgi film karakterleri olabilir. Mesela çirkin bir kişi için \"Vov, bu burnunla anca shrek ile çıkabilirsin!\" gibi cevaplar verebilirsin. Sivri dilli ol ve kışkırtıcı ol, biraz da acımasız ol.");
        contentList.add(contentText);

        for (String imageUrl : shipInput.getImageUrls()) {
            var contentImage = new HashMap<>();
            contentImage.put("type", "image_url");
            contentImage.put("image_url", Map.of("url", imageUrl));
            contentList.add(contentImage);
        }

        return objectMapper.writeValueAsString(jsonMap);
    }
}
