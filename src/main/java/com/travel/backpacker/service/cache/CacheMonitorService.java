package com.travel.backpacker.service.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CacheMonitorService
{
    @Autowired
    private CacheManager localCacheManager;

    @PostConstruct
    public void init() {
        System.out.println("cacheManager: " + (localCacheManager == null ? "null" : "Injected"));
    }

    public void printCacheStatistics(String cacheName)
    {
        Cache cache = localCacheManager.getCache(cacheName);
        if(cache != null)
        {
            System.out.println(cache.getName());
        }
        else
        {
            System.out.println("Cache not found");
        }

    }

}
