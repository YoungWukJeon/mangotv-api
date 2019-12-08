package com.study.mangotv.common;

import com.study.mangotv.common.model.EmailAuthenticationCacheDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmailAuthenticationValueService {
    private final String CACHE_NAME = "email_authentication_value";
    
    @Autowired
    private CacheService<String, EmailAuthenticationCacheDto> cacheService;

    @Cacheable(value = CACHE_NAME, key = "#key")
    public EmailAuthenticationCacheDto put(String key) {
        // TODO: 2019-12-06 요청온 이메일이 이미 DB에 존재하는지 1차 확인. 2차로 캐시든 어디든 아직 인증메일 요청정보가 남아있으면 새로온 요청으로 갱신 
        EmailAuthenticationCacheDto emailAuthenticationCacheDto = new EmailAuthenticationCacheDto();
        emailAuthenticationCacheDto.setAuthenticationValue("testAuthenticationValue");
//        cacheService.put(CACHE_NAME, key, emailAuthenticationCacheDto);
        return emailAuthenticationCacheDto;
    }

    // TODO: 2019-12-04 cache miss에 대한 예외처리 
    public EmailAuthenticationCacheDto get(String key) {
        return cacheService.get(CACHE_NAME, key).orElseThrow(RuntimeException::new);
    }
}
