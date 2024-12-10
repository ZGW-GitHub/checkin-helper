package com.code.checkin.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zgw
 * @date 2024/12/10
 */
@Slf4j
@SpringBootApplication
public class CheckinHelperApplication {
    public static void main(String[] args) {

        new SpringApplicationBuilder(CheckinHelperApplication.class).run(args);

    }
}
