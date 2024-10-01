package com.voghbum.application.service;

import com.voghbum.application.data.entity.AiOutputType;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class LockMapsForAnalyzeService {
    public static final Map<String, Object> LOCK_FOR_USER_FEED = new ConcurrentHashMap<>();
    public static final Map<String, Object> LOCK_FOR_USER_PROFILE = new ConcurrentHashMap<>();
    public static final Map<String, Object> LOCK_FOR_USER_STORIES = new ConcurrentHashMap<>();
}

class LockMapsForAiService {
    public static final Map<AiKey, Object> LOCK_FOR_AI_OUTPUT = new ConcurrentHashMap<>();
}

class AiKey extends Pair<String, AiOutputType> {
    public AiKey(String username, AiOutputType outputType) {
        super(username, outputType);
    }
}
