package com.hiekn.metaboot.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public abstract class CommonUtils {

	private static final DateTimeFormatter sdf0 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter sdfDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter sdfTime = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static String formatNowDate() {
		return formatDateTime(LocalDateTime.now());
	}

	public static String formatDateTime(TemporalAccessor date) {
		return sdf.format(date);
	}

	public static String formatDate(TemporalAccessor date) {
		return sdfDate.format(date);
	}

	public static String formatTime(TemporalAccessor date) {
		return sdfTime.format(date);
	}

	public static LocalDateTime parseDate(String dateTimeString) {
		return LocalDateTime.from(sdfDate.parse(dateTimeString));
	}

	public static LocalDateTime parseDateTime(String dateTimeString) {
		return LocalDateTime.from(sdf.parse(dateTimeString));
	}

	public static String timeToString(Long time) {
		return sdf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
	}

	public static String timeToString(Date date) {
		return sdf0.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()));
	}
}
