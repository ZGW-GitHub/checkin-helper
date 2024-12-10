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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snow
 * @date 2023/12/16 13:04
 */
@Slf4j
@Data
public class CheckinAccount {

    private String type;

    private String user;

    private String url;

    private String token;

    private String cookie;

    private Boolean checkin;

}
