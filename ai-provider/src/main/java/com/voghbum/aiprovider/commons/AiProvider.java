package com.voghbum.aiprovider.commons;

import org.springframework.stereotype.Service;

@Service
public interface AiProvider {
    String generateRoast(RoastInput profilePostsData);
}
