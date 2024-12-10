/*
 * Copyright (C) <2024> <Snow>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.code.checkin.helper.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.code.checkin.helper.config.CheckinConfig;
import com.code.checkin.helper.domain.CheckinAccount;
import com.code.checkin.helper.domain.CheckinResult;
import com.code.checkin.helper.enums.CheckinAccountTypeEnum;
import com.code.checkin.helper.framework.exception.CheckinException;
import com.code.checkin.helper.framework.exception.ParseResultException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Snow
 * @date 2023/12/16 15:37
 */
@Slf4j
@Component
public class CheckinActuator {

    @Resource
    private CheckinConfig checkinConfig;

    @Resource
    private List<CheckinService> checkinServiceList;

    @Resource
    private JavaMailSender javaMailSender;

    public void checkin() {
        Map<? extends Class<? extends CheckinService>, CheckinService> checkinServiceMap = checkinServiceList.stream().collect(Collectors.toMap(r -> r.getClass(), Function.identity()));

        Map<String, List<CheckinAccount>> accountMap = checkinConfig.getAccountList().stream().collect(Collectors.groupingBy(CheckinAccount::getType));

        List<String> checkinResultList = new ArrayList<>(checkinConfig.getAccountList().size());
        accountMap.forEach((accountType, accountList) -> {
            CheckinAccountTypeEnum accountTypeEnum = CheckinAccountTypeEnum.getByCode(accountType);
            CheckinService<?, ?> checkinService = checkinServiceMap.get(accountTypeEnum.getClazz());

            List<String> resultList = accountList.stream()
                    .filter(CheckinAccount::getCheckin)
                    .map(account -> checkin(account, checkinService).buildMailContent(account))
                    .toList();
            checkinResultList.addAll(resultList);
        });

        log.info("签到结果: \n{}", checkinResultList);

        String mailContent = CollUtil.join(checkinResultList, "\n\n\n");
        sendEmail(checkinConfig.getSenderMail(), checkinConfig.getRecipientMail(), mailContent);
    }

    private void sendEmail(String senderMail, String recipientMail, String mailContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("签到助手" + '<' + senderMail + '>');
        message.setTo(recipientMail);
        message.setSubject("签到结果");
        message.setText(mailContent);

        javaMailSender.send(message);
    }

    private <REQT, RESPT> CheckinResult checkin(CheckinAccount account, CheckinService<REQT, RESPT> checkinService) {
        HttpEntity<REQT> httpEntity = checkinService.buildRequest(account);

        RESPT result = null;
        try {
            result = checkinService.doCheckin(account, httpEntity);

            return checkinService.parseResult(result);
        } catch (CheckinException e) {
            log.error("账号: {}, 签到请求异常: {}", account, e.getMessage(), e);

            return new CheckinResult().setSuccess(false).setMsg("签到请求异常：" + e.getMessage());
        } catch (ParseResultException e) {
            log.error("账号: {}, 签到结果解析异常，结果: {}, 异常: {}", account, result, e.getMessage(), e);

            String msg = StrUtil.format("签到结果解析异常，结果：{}，异常：{}", result, e.getMessage());
            return new CheckinResult().setSuccess(false).setMsg(msg);
        }
    }

}
