package com.chelsea.springcloud2.log4j;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    
    @RequestMapping("/test")
    public void log(String param) {
        log.warn("warn {}", param);
        log.debug("debug {}", param);
        log.info("info {}", param);
        log.error("error {}", param);
    }

}
