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

package com.code.checkin.helper.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Snow
 * @date 2023/8/27 21:26
 */
@Slf4j
// @Component
public class CheckinJob {

	// @Resource
	// private CheckinActuator checkinActuator;
	//
	// @Resource
	// private TaskScheduler taskScheduler;
	//
	// @SneakyThrows
	// // @Scheduled(cron = "0 30 7 * * ?", zone = "Asia/Shanghai")
	// public void checkin() {
	//     ZonedDateTime currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
	//     ZonedDateTime latestTime = currentTime.plusHours(1);
	//
	//     long randomMax = latestTime.toEpochSecond() - currentTime.toEpochSecond();
	//     long random = RandomUtil.randomLong(Math.abs(randomMax));
	//     log.debug("{} latestTime: {}, randomMax: {}, random: {}", currentTime.format(DatePattern.NORM_DATETIME_FORMATTER), latestTime.format(DatePattern.NORM_DATETIME_FORMATTER), randomMax, random);
	//
	//     ZonedDateTime checkInTime = currentTime.plusSeconds(random);
	//     taskScheduler.schedule(() -> checkinActuator.checkin(), checkInTime.toInstant());
	// }

}
