package com.demo.test;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Ivan on 2016/8/5.
 */
@ConfigurationProperties(prefix = "test",locations = "classpath:test.properties")
public class TestProperties {
        private String name;
        private String gender;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getGender() {
            return gender;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
}
