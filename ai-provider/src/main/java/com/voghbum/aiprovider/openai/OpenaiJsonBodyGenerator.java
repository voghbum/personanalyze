package com.voghbum.aiprovider.openai;

import com.voghbum.aiprovider.commons.data.RoastInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenaiJsonBodyGenerator {

    public String generate(RoastInput input, String question) {
        var jsonMap = new HashMap<String, Object>();
        jsonMap.put("model", "gpt-4o-mini");
        var messagesList = new ArrayList<>();
        var sysRole = new HashMap<>();
        var userRole = generateUserRole(input);
        jsonMap.put("messages", messagesList);
        messagesList.add(userRole);
        var contentList = new ArrayList<>();

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

    private String generateUserRole(RoastInput input) {
        var result = new HashMap<>();
        userRole.put("role", "user");
        userRole.put("content", contentList);
    }
}
