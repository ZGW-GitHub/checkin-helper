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

package com.code.checkin.helper.enums;

import com.code.checkin.helper.service.CheckinService;
import com.code.checkin.helper.service.FreeGLaDOSCheckinService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Snow
 * @date 2023/12/16 13:05
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum CheckinAccountTypeEnum {

    FREE_GLaDOS("GLaDOS", "[ 自由之路 ] [ GLaDOS ]", FreeGLaDOSCheckinService.class),
    ;

    private final String code;

    private final String desc;

    private final Class<? extends CheckinService> clazz;

    public static CheckinAccountTypeEnum getByCode(String code) {
        return Arrays.stream(CheckinAccountTypeEnum.values())
                .filter(anEnum -> anEnum.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("账号类型枚举不存在: " + code));
    }

}
