package com.code.checkin.helper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zgw
 * @date 2024/12/10
 */
@Slf4j
@SpringBootTest
public class CheckinHelperApplicationTest {

    @Value("${demo.test}")
    private String demo;

    @Test
    public void contextLoadTest() {
        System.err.println("Test - contextLoad test" + demo);
    }

}
