package com.voghbum.application.data.dal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockMaps {
    public static final Map<String, Object> LOCK_FOR_AI_RESULT = new ConcurrentHashMap<>();
    public static final Map<String, Object> LOCK_FOR_USER_FEED = new ConcurrentHashMap<>();
    public static final Map<String, Object> LOCK_FOR_USER_PROFILE = new ConcurrentHashMap<>();
    public static final Map<String, Object> LOCK_FOR_USER_STORIES = new ConcurrentHashMap<>();
}
