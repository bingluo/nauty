package cn.seu.cose.view.util;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;

public class ViewUtil {
	public static String getBreadcrumb(int catId) {
		CategoryPojo curCat = CategoryCache.get(catId);
		if (curCat.getCatLevel() == 1) {
			return "<h2 style='padding: 10px 0 10px 0;'>" + curCat.getCatName()
					+ "</h2>";
		} else {
			return "<h2 style='padding: 10px 0 10px 0;'>"
					+ CategoryCache.get(curCat.getParentCatId()).getCatName()
					+ "<span>/ " + curCat.getCatName() + "</span></h2>";
		}
	}

	public static String getParentCatName(int catId) {
		CategoryPojo curCat = CategoryCache.get(catId);
		if (curCat.getCatLevel() == 1) {
			return curCat.getCatName();
		} else {
			return CategoryCache.get(curCat.getParentCatId()).getCatName();
		}
	}
}
