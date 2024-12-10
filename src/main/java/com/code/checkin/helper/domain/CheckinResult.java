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

package com.code.checkin.helper.domain;

import cn.hutool.core.util.StrUtil;
import com.code.checkin.helper.enums.CheckinAccountTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snow
 * @date 2023/12/16 14:47
 */
@Slf4j
@Data
@Accessors(chain = true)
public class CheckinResult {

    private boolean success;

    private String msg;

    public String buildMailContent(CheckinAccount account) {
        String type = account.getType();
        String user = account.getUser();

        CheckinAccountTypeEnum accountTypeEnum = CheckinAccountTypeEnum.getByCode(type);

        String statusMsg = success ? "成功" : "失败";
        return StrUtil.format("{} 签到{}\n账号：{}\n详情：{}\n", accountTypeEnum.getDesc(), statusMsg, user, msg);
    }

}
