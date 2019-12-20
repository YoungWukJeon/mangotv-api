package com.study.mangotv.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheService<K, V> {

//    @Autowired
//    private CacheManager cacheManager;

//    public void remove(String name, K key) {
//        cacheManager.getCache(name).evict(key);
//    }
//
//    public void clear(String name) {
//        cacheManager.getCache(name).clear();
//    }
//
//    public void put(String name, K key, V value) {
//        cacheManager.getCache(name).put(key, value);
//    }
//
//    public Optional<V> get(String name, K key) {
//        return Optional.ofNullable((V) cacheManager.getCache(name).get(key).get());
//    }
}
