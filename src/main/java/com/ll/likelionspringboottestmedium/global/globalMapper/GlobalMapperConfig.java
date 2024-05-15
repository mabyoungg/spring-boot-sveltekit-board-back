package com.ll.likelionspringboottestmedium.global.globalMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalMapperConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
