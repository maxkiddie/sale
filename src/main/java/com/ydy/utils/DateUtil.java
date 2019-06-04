/**
 * 
 */
package com.ydy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuzhaojie
 *
 *         2018年9月20日 上午10:24:20
 */
public class DateUtil {
	private final static Logger log = LoggerFactory.getLogger(DateUtil.class);

	public final static String pattern_id = "yyyyMMddHHmmss";
	public final static String pattern_full = "yyyy-MM-dd HH:mm:ss";
	public final static String pattern_date = "yyyy-MM-dd";
	public final static String pattern_file_date = "yyyy年MM月dd日HH时mm分ss秒";

	private static final ThreadLocal<DateFormat> df_id = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_id);// 常用的一种类型
		}
	};
	private static final ThreadLocal<DateFormat> df_full = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_full);// 常用的一种类型
		}
	};

	private static final ThreadLocal<DateFormat> df_date = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_date);// 常用的一种类型
		}
	};

	private static final ThreadLocal<DateFormat> df_file_date = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(pattern_file_date);// 常用的一种类型
		}
	};

	private static final DateFormat getDateFormat(ThreadLocal<DateFormat> format) {
		return (DateFormat) format.get();
	}

	public static String produceId() {
		return df_id.get().format(new Date());
	}

	// 转化数据类型,string->date
	public static Date getDateByDateFormat(String date_str, DateFormat df) {
		if (StringUtils.isEmpty(date_str))
			return null;
		try {
			// 转化数据格式
			return df.parse(date_str);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	// date->string
	public static String getDateStrByDateFormat(Date date, DateFormat df) {
		return df.format(date);
	}

	public static String getDateStrByDateFormat(Date date) {
		return df_full.get().format(date);
	}

	// 获取当前Date
	public static Date getCurrentDate() {
		Instant instant = Instant.now();
		return Date.from(instant);
	}

	// 获取DateStr[yyyy-MM-dd HH:mm:ss]
	public static String getCurrentTimeFullStr() {
		return getDateStrByDateFormat(getCurrentDate(), getDateFormat(df_full));
	}

	public static String getFileCurrentTime() {
		return getDateStrByDateFormat(getCurrentDate(), getDateFormat(df_file_date));
	}

	public static String getTodatDate() {
		DateFormat df = df_date.get();
		return df.format(new Date());
	}

	public static String getDateFile(Date date) {
		DateFormat df = df_date.get();
		return df.format(date);
	}

	public static long formatTimeLong(String fmt) {
		if (fmt == null || "".equals(fmt)) {
			throw new NullPointerException("时间格式为空");
		}
		fmt = fmt.toLowerCase();
		long timeLong = 0;
		char c = '0';
		StringBuilder builder = null;
		for (int i = 0; i < fmt.length(); i++) {
			c = fmt.charAt(i);
			if (c >= '0' && c <= '9') {
				if (builder == null)
					builder = new StringBuilder();
				builder.append(c);
			} else if (c == 'd' || c == 'h' || c == 'm' || c == 's') {
				long num = Long.parseLong(builder.toString());
				builder = null;
				switch (c) {
				case 'd':
					timeLong = timeLong + (num * 24 * 60 * 60 * 1000L);
					break;
				case 'h':
					timeLong = timeLong + (num * 60 * 60 * 1000L);
					break;
				case 'm':
					timeLong = timeLong + (num * 60 * 1000L);
					break;
				case 's':
					timeLong = timeLong + (num * 1000L);
					break;
				}
			}
		}
		return timeLong;
	}

}
