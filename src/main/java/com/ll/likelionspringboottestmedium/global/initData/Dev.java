package com.ll.likelionspringboottestmedium.global.initData;

import com.ll.likelionspringboottestmedium.global.app.AppConfig;
import com.ll.likelionspringboottestmedium.standard.util.Ut.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class Dev {
    @Bean
    @Order(4)
    ApplicationRunner initDev() {
        return args -> {
            String backUrl = AppConfig.getSiteBackUrl();
            String cmd = "cd ../spring-boot-sveltekit-board-front && npx openapi-typescript " + backUrl + "/v3/api-docs/apiV1 -o ./src/lib/types/api/v1/schema.d.ts";
            Ut.cmd.runAsync(cmd);
        };
    }
}
