package com.code.checkin.helper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author zgw
 * @date 2024/12/10
 */
@Slf4j
@SpringBootTest
public class CheckinHelperApplicationTest {

    @Test
    public void contextLoadTest() {
        try {
            log.info("Test - contextLoad test");
            TimeUnit.SECONDS.sleep(1);
            System.err.println("Test - contextLoad test");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
