package com.code.checkin.helper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zgw
 * @date 2024/12/10
 */
@Slf4j
@Data
@Configuration(proxyBeanMethods = false)
public class DemoConfig {

    @Value("${demo.test}")
    private String demo;

}
