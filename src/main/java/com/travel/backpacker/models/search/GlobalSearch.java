package com.travel.backpacker.models.search;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public interface GlobalSearch {
    public HttpEntity execute();
}
