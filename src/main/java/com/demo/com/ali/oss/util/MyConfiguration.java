package com.demo.com.ali.oss.util;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Ivan on 2016/6/21.
 */
//@Configuration
//public class MyConfiguration {
//
//    @Bean
//    public HttpMessageConverters customConverters() {
//        HttpMessageConverter<?> additional = new StringHttpMessageConverter();
//        HttpMessageConverter<?> another = new MappingJackson2HttpMessageConverter();
//        return new HttpMessageConverters(additional, another);
//    }
//}