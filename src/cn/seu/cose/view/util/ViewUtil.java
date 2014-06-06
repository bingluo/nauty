package cn.seu.cose.view.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;

public class ViewUtil {
	public static String CONTEXT_PATH = "";

	public static String getContextPath() {
		return CONTEXT_PATH;
	}

	public static String getBreadcrumb(int catId) {
		CategoryPojo curCat = CategoryCache.get(catId);
		if (curCat.getCatLevel() == 1) {
			return "<h2 style='padding: 10px 0 10px 0;'><a href='"
					+ CONTEXT_PATH + "/" + curCat.getUriName()
					+ "' style='color:#333;font-weight:bold;'>"
					+ curCat.getCatName() + "</a></h2>";
		} else {
			CategoryPojo parent = CategoryCache.get(curCat.getParentCatId());
			return "<h2 style='padding: 10px 0 10px 0;'><a href='"
					+ CONTEXT_PATH + "/" + parent.getUriName()
					+ "' style='color:#333;font-weight:bold;'>"
					+ CategoryCache.get(curCat.getParentCatId()).getCatName()
					+ "</a><span> / <a href='" + CONTEXT_PATH + "/"
					+ parent.getUriName() + "/cat-" + catId
					+ "' style='color:#aaa'>" + curCat.getCatName()
					+ "</a></span></h2>";
		}
	}

	public static String fActivityTime(Timestamp time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(new Date(time.getTime()));
	}

	public static String fArticlePostTime(String time) {
		if (time == null || time.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String fTime(String time) {
		if (time == null || time.isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = sdf.parse(time);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final int BRIEF_LENGTH = 10;

	public static String brief(String s) {
		if (s == null || s.isEmpty() || s.length() <= BRIEF_LENGTH)
			return s;
		return s.substring(0, BRIEF_LENGTH);
	}

	public static String getParentCatName(int catId) {
		CategoryPojo curCat = CategoryCache.get(catId);
		if (curCat.getCatLevel() == 1) {
			return curCat.getCatName();
		} else {
			return CategoryCache.get(curCat.getParentCatId()).getCatName();
		}
	}

	// 时间
	public static String today() {
		Calendar today = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(today.getTime());
	}

	public static String weekBefore() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(today.getTime());
	}
}
