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

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.code.checkin.helper.domain.CheckinAccount;
import com.code.checkin.helper.domain.CheckinResult;
import com.code.checkin.helper.framework.exception.CheckinException;
import com.code.checkin.helper.framework.exception.ParseResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author Snow
 * @date 2023/12/16 14:58
 */
@Slf4j
@Service
public class FreeGLaDOSCheckinService implements CheckinService<String, String> {

    @Override
    public HttpEntity<String> buildRequest(CheckinAccount account) {
        JSONObject data = new JSONObject();
        data.set("token", account.getToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", account.getCookie());

        // 创建请求参数
        return new HttpEntity<>(data.toString(), headers);
    }

    @Override
    public String doCheckin(CheckinAccount account, HttpEntity<String> httpEntity) throws CheckinException {
        try {
            String url = account.getUrl();

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, httpEntity, String.class);
        } catch (Throwable t) {
            throw new CheckinException(t);
        }
    }

    @Override
    public CheckinResult parseResult(String result) throws ParseResultException {
        try {
            JSONObject resultJson = JSONUtil.parseObj(result);
            JSONArray checkinRecordArray = resultJson.getJSONArray("list");

            String message = resultJson.getStr("message");
            String balance = Optional.ofNullable(checkinRecordArray)
                    .map(r -> r.getJSONObject(0))
                    .map(r -> r.getStr("balance"))
                    .map(r -> StrUtil.subBefore(r, ".", false))
                    .orElse("not find");

            return new CheckinResult().setSuccess(true).setMsg("现有点数：" + balance + " 点（ " + message + " ）");
        } catch (Throwable t) {
            throw new ParseResultException(t);
        }
    }

}
