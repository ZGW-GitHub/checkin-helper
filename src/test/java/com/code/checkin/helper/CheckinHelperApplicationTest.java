package com.code.checkin.helper;

import com.code.checkin.helper.service.CheckinActuator;
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
    private CheckinActuator checkinActuator;

    @Test
    public void contextLoadTest() {
        checkinActuator.checkin();
    }

}
