package com.code.checkin.helper;

import cn.hutool.core.util.RandomUtil;
import com.code.checkin.helper.service.CheckinActuator;
import jakarta.annotation.Resource;
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

    @Resource
    private CheckinActuator checkinActuator;

    @Test
    public void contextLoadTest() {
        try {
            TimeUnit.SECONDS.sleep(RandomUtil.randomInt(30));
        } catch (InterruptedException e) {
            log.error("sleep 出错.", e);
        }

        checkinActuator.checkin();
    }

}
