package com.voghbum.aiprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voghbum.aiprovider.commons.AiProvider;
import com.voghbum.aiprovider.commons.data.RoastInput;
import com.voghbum.aiprovider.commons.data.RoastOutput;
import com.voghbum.aiprovider.commons.data.ShipInput;
import com.voghbum.aiprovider.commons.data.ShipOutput;
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
        contentText.put("text", "Sen, sivri dilli ve kışkırtıcı tarzınla tanınan profesyonel bir yorumcusun. Görevin, insanların intagram profillerindeki fotoğraflara bakarak kişiliklerini değerlendirmek. Sivri ol ve kışkırtıcı ol, biraz da acımasız ol. Ama sakın basmakalıp ya da utandırıcı olma. İşte güzel bir \"roast\" (laf sokma) denemesi:\n" + "Tamam, bunu parçalayalım. Bir yığın ev bitkisinin arasında, çıplak ayakla oturmuş, yataktan yeni çıkmış gibi görünüyorsun. Bej tişörtün, \"Duvar kağıdına karışmaya çalışıyorum\" havasını veriyor. Siyah pantolonlar mı? Onlar da \"Uyanıp uygun bir şey bulmaya üşendim\" diye bağırıyor. Ama hey, en azından rahat görünüyorsun. Rahatlık önemli tabii, değil mi? Belki de bir moda ifadesi yapmaya çalışırken pek değil.");
        contentList.add(contentText);

        List<String> allImageUrls = postsData.getImages().stream().map(RoastInput.RoastImage::getImageUrl).toList();

        for (String imageUrl : allImageUrls) {
            var contentImage = new HashMap<>();
            contentImage.put("type", "image_url");
            contentImage.put("image_url", Map.of("url", imageUrl));
            contentList.add(contentImage);
        }

        return objectMapper.writeValueAsString(jsonMap);
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
