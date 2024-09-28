package com.voghbum.aiprovider.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.voghbum.aiprovider.commons.data.AiInput;
import com.voghbum.aiprovider.configuration.PromptValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
class OpenaiJsonBodyGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(OpenaiJsonBodyGenerator.class);
    private final PromptValues promptValues;

    public OpenaiJsonBodyGenerator(PromptValues promptValues) {
        this.promptValues = promptValues;
    }

    public String generate(AiInput input, String question) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonMap = new HashMap<String, Object>();
        jsonMap.put("model", "gpt-4o-mini");
        var messagesList = new ArrayList<>();
        var sysRole = generateAdminRole(question);
        var userRole = generateUserRole(input);
        jsonMap.put("messages", messagesList);
        messagesList.add(userRole);
        messagesList.add(sysRole);

        return objectMapper.writeValueAsString(jsonMap);
    }

    private Map<String, Object> generateUserRole(AiInput input) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var userRole = new HashMap<String, Object>();
        userRole.put("role", "user");
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("excludeImageUrls",
                SimpleBeanPropertyFilter.serializeAllExcept("imageUrl"));
        objectMapper.setFilterProvider(filterProvider);
        String filteredAiInput = objectMapper.writeValueAsString(input);
        userRole.put("content", filteredAiInput);

        return userRole;
    }

    private Map<String, String> generateAdminRole(String question) {
        var adminRole = new HashMap<String, String>();
        String questionWithQuoata = "\"\"\"" + question + "\"\"\"";
        adminRole.put("role", "system");
        adminRole.put("content", promptValues.SYS_COMMON_INS + questionWithQuoata);
        return adminRole;
    }
}
