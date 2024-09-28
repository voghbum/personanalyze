package com.voghbum.application.controller;

import com.voghbum.application.data.request.UserRequest;
import com.voghbum.application.data.response.*;
import com.voghbum.application.service.AiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Scope("prototype")
@RequestMapping("/api/ai")
public class AnalyzeAiController {
    private static final Logger LOG = LoggerFactory.getLogger(AnalyzeAiController.class);
    private final AiService aiService;

    public AnalyzeAiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/personal_life")
    public ResponseEntity<PersonalLifeAnalyzeResponse> personalLife(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
//        var result = aiService.getPersonalInformation(username);
        var result = new PersonalLifeAnalyzeResponse();
        result.setAiResult("PERSONAL LIFE :: Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/love_life")
    public ResponseEntity<LoveLifeResponse> loveLife(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        //var result = aiService.shipMe(username);
        var result = new LoveLifeResponse();
        result.setAiResult("LOVE LIFE :: Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change_millionaire")
    public ResponseEntity<ChangeMillionaireResponse> changeOfMillionaire(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        //var result = aiService.millionaireChange(username);
        var result = new ChangeMillionaireResponse();
        result.setAiResult("MILLIONAIRE :: Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/similar_celeb")
    public ResponseEntity<SimilarCelebResponse> similarCeleb(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        //var result = aiService.similarCeleb(username);
        var result = new SimilarCelebResponse();
        result.setAiResult("SIMILAR CELEB :: Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/strength_and_weaknesses")
    public ResponseEntity<StrengthAndWeaknessResponse> strengthAndWeaknesses(@RequestBody UserRequest request) throws IOException, InterruptedException {
        String username = request.getUsername();
        LOG.info("roast requested: " + username);
        //var result = aiService.strengthAndWeakness(username);
        var result = new StrengthAndWeaknessResponse();
        result.setAiResult("ST AND WEAKNESS :: Arkadaşın, sosyal medya için bir stilde takılamayan ve modaya uygun kıyafetleri asla bulamayan tiplerden biri. İkinci fotoğraftaki gömlek, sanki bir paletin içine düşüp çıkmış gibi, ama herhalde onun için bu, \"sanat eseri\" sayılır. İlk fotoğraftaki ciddi ifadesiyle aslında içten içe gülmekten başka bir şey yapmadığını düşünüyorum. Üçüncü fotoğraftaki karizmatik havası ise sadece güneş gözlüklerine dayanıyor; yoksa Amsterdam'dan dönerken kaybettiği tarzını bulmaktan aciz kalmış durumda. Onun bu özgüveni ve absürt moda anlayışı, bizim için bir komedi kaynağı!\n" + "\n");
        LOG.info("roast result for user: {} -> {}", username, result);
        return ResponseEntity.ok(result);
    }
}
