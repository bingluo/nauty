package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;

@Controller
public class CatController extends AbstractController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ArticleService articleService;

	// about
	@RequestMapping("/about/")
	public String viewAboutCatIndex(Model model) {
		return view(model, 9, 1);
	}

	@RequestMapping("/about/cat-{catId}/")
	public String viewAboutCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/about/cat-{catId}/page-{pageIndex}/")
	public String viewAboutCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// new
	@RequestMapping("/new/")
	public String viewNewCatIndex(Model model) {
		return view(model, 2, 1);
	}

	@RequestMapping("/new/cat-{catId}/")
	public String viewNewCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/new/cat-{catId}/page-{pageIndex}/")
	public String viewNewCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// work
	@RequestMapping("/work/")
	public String viewWorkCatIndex(Model model) {
		return view(model, 3, 1);
	}

	@RequestMapping("/work/cat-{catId}/")
	public String viewWorkCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/work/cat-{catId}/page-{pageIndex}/")
	public String viewWorkCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// policy
	@RequestMapping("/policy/")
	public String viewPolicyCatIndex(Model model) {
		return view(model, 4, 1);
	}

	@RequestMapping("/policy/cat-{catId}/")
	public String viewPolicyCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/policy/cat-{catId}/page-{pageIndex}/")
	public String viewPolicyCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// train
	@RequestMapping("/train/")
	public String viewTrainCatIndex(Model model) {
		return view(model, 28, 1);
	}

	@RequestMapping("/train/cat-{catId}/")
	public String viewTrainCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/train/cat-{catId}/page-{pageIndex}/")
	public String viewTrainCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// events
	@RequestMapping("/events/")
	public String viewEventsCatIndex(Model model) {
		return view(model, 6, 1);
	}

	@RequestMapping("/events/cat-{catId}/")
	public String viewEventsCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/events/cat-{catId}/page-{pageIndex}/")
	public String viewEventsCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	// space
	@RequestMapping("/space/")
	public String viewSpaceCatIndex(Model model) {
		return view(model, 6, 1);
	}

	@RequestMapping("/space/cat-{catId}/")
	public String viewSpaceCat(Model model, @PathVariable("catId") int catId) {
		return view(model, catId, 1);
	}

	@RequestMapping("/space/cat-{catId}/page-{pageIndex}/")
	public String viewSpaceCat(Model model, @PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex) {
		return view(model, catId, pageIndex);
	}

	@RequestMapping("/download.html/")
	public String downloadCenter(Model model) {
		return "downloadCenter";
	}

	private String view(Model model, int catId, int pageIndex) {
		addCategories(model);
		CategoryPojo categoryPojo = CategoryCache.get(catId);
		model.addAttribute("curCat", categoryPojo);

		List<CategoryPojo> cats = null;
		if (categoryPojo.getCatLevel() == 1) {
			model.addAttribute("rootCat", categoryPojo);
			cats = categoryService
					.getCategoriesByParentId(categoryPojo.getId());
		} else {
			model.addAttribute("rootCat",
					CategoryCache.get(categoryPojo.getParentCatId()));
			model.addAttribute("curCat", categoryPojo);
			cats = categoryService.getCategoriesByParentId(categoryPojo
					.getParentCatId());
		}
		model.addAttribute("childrenCats", cats);

		if (categoryPojo.isExclusiveArticle()) {
			model.addAttribute("exclusive", true);
			model.addAttribute("exclusiveArticle",
					articleService.getExclusiveArticleByCatId(catId));
		} else {
			List<ArticlePojo> articles = articleService
					.getArticleByCatIdAndPageIndexAndPageSize(catId, pageIndex,
							10);
		}
		return "viewCat";
	}
}
