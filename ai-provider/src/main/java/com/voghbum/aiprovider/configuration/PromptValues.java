package com.voghbum.aiprovider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PromptValues {
    /*
        system rolünde, sorudan önce api'ye verilecek genel instruction.
     */
    @Value("#{promptProvider['ai.sys.instruction.common']}")
    public String SYS_COMMON_INS;
    @Value("#{promptProvider['ai.sys.quest.personal-info']}")
    public String SYS_PERSONAL_INFO_QUEST;
    @Value("#{promptProvider['ai.sys.quest.strengths-weaknesses-quest']}")
    public String SYS_STRENGTH_WEAKNESSES_QUEST;
    @Value("#{promptProvider['ai.sys.quest.love-life']}")
    public String SYS_LOVE_LIFE_QUEST;
    @Value("#{promptProvider['ai.sys.quest.millionaire-chance']}")
    public String SYS_QUEST_MILLIONAIRE_CHANGE_QUEST;
    @Value("#{promptProvider['ai.sys.quest.bunaneisimkoyacambulamadim']}")
    public String SYS_ISIM_BULAMADIGIM_DALGA_QUEST;
    @Value("#{promptProvider['ai.sys.quest.similiar-celeb']}")
    public String SYS_SIMILIAR_CELEB_QUEST;
}
