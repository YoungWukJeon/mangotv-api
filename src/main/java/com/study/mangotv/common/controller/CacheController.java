package com.study.mangotv.common.controller;

import com.study.mangotv.auth.emailauthenticationcode.EmailAuthenticationCodeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/config")
public class CacheController {
    @Autowired
    private EmailAuthenticationCodeService emailAuthenticationCodeService;

    public String cacheEvict() {

        return "CACHE_EVICTED";
    }

//    @PostMapping("/cache/email_authentication")
//    public EmailAuthenticationCacheDto putCache(@RequestBody Request request) {
//        EmailAuthenticationCacheDto tt1 = emailAuthenticationValueService.put(request.key);
//        System.out.println("tt1: " + tt1);
//        return tt1;
////        return emailAuthenticationValueService.put(request.key);
//    }
//
//    @GetMapping("/cache/email_authentication/{key:.+}")
//    public EmailAuthenticationCacheDto getCache(@PathVariable String key) {
//        EmailAuthenticationCacheDto tt = emailAuthenticationValueService.get(key);
//        System.out.println("tt: " + tt);
//        return tt;
////        return emailAuthenticationValueService.get(key);
//    }


}

@Data
class Request {
    String key;
}
