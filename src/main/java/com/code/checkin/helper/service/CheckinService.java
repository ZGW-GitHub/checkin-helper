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

import com.code.checkin.helper.domain.CheckinAccount;
import com.code.checkin.helper.domain.CheckinResult;
import com.code.checkin.helper.framework.exception.CheckinException;
import com.code.checkin.helper.framework.exception.ParseResultException;
import org.springframework.http.HttpEntity;

/**
 * @author Snow
 * @date 2023/12/16 14:28
 */
public interface CheckinService<REQT, RESPT> {

    HttpEntity<REQT> buildRequest(CheckinAccount account);

    RESPT doCheckin(CheckinAccount account, HttpEntity<REQT> httpEntity) throws CheckinException;

    CheckinResult parseResult(RESPT result) throws ParseResultException;

}
