package com.voghbum.aiprovider.commons;

import com.voghbum.aiprovider.commons.data.RoastInput;
import com.voghbum.aiprovider.commons.data.RoastOutput;
import com.voghbum.aiprovider.commons.data.ShipInput;
import com.voghbum.aiprovider.commons.data.ShipOutput;
import org.springframework.stereotype.Service;

@Service
public interface AiProvider {
    RoastOutput roast(RoastInput profilePostsData);
    ShipOutput ship(ShipInput shipInput);
}
