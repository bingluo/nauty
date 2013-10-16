package cn.seu.cose.view.util;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.CategoryPojo;

public class ViewUtil {
	public static String getBreadcrumb(int catId) {
		CategoryPojo curCat = CategoryCache.get(catId);
		if (curCat.getCatLevel() == 1) {
			return "<h2 style='padding: 10px 0 10px 0;'><a href='/"
					+ curCat.getUriName() + "/' style='color:#333'>"
					+ curCat.getCatName() + "</a></h2>";
		} else {
			CategoryPojo parent = CategoryCache.get(curCat.getParentCatId());
			return "<h2 style='padding: 10px 0 10px 0;'><a href='/"
					+ parent.getUriName() + "/' style='color:#333'>"
					+ CategoryCache.get(curCat.getParentCatId()).getCatName()
					+ "</a><span>/ <a href='/" + parent.getUriName() + "/cat-"
					+ catId + "/' style='color:#aaa'>" + curCat.getCatName()
					+ "</a></span></h2>";
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
