package com.code.checkin.helper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zgw
 * @date 2024/12/10
 */
@Slf4j
@SpringBootTest
public class CheckinHelperApplicationTest {

    @Resource
    private DemoConfig demoConfig;

    @Test
    public void contextLoadTest() {
        System.err.println("Test - contextLoad test -" + demoConfig.getDemo() + "- ov");
    }

}
