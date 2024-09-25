package com.voghbum.aiprovider.configuration;

//todo: commentler ingilizceye çevrilecek bi ara.
public enum PromptKeys {
    /*
        system rolünde, sorudan önce api'ye verilecek genel instruction.
     */
    SYS_COMMON_INS("sys.instruction.common"),
    SYS_PERSONAL_INFO_QUEST("sys.quest.personal-info"),
    SYS_STRENGTH_WEAKNESSES_QUEST("sys.quest.strengths-weaknesses-quest"),
    SYS_LOVE_LIFE_QUEST("sys.quest.love-life"),
    SYS_QUEST_MILLIONAIRE_CHANGE_QUEST("sys.quest.millionaire-chance"),
    SYS_ISIM_BULAMADIGIM_DALGA_QUEST("sys.quest.bunaneisimkoyacambulamadim"),
    SYS_SIMILIAR_CELEB_QUEST("sys.quest.similiar-celeb");

    public final String key;
    PromptKeys(String key) {
        this.key =key;
    }
}
