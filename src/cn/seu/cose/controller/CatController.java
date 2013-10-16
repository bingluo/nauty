package cn.seu.cose.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.core.CategoryCache;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.entity.Upload;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;
import cn.seu.cose.service.UploadService;

@Controller
public class CatController extends AbstractController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ArticleService articleService;
	@Autowired
	UploadService uploadService;

	// about
	@RequestMapping("/about/")
	public String viewAboutCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 9, index);
	}

	@RequestMapping("/about/cat-{catId}/")
	public String viewAboutCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// new
	@RequestMapping("/new/")
	public String viewNewCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 2, index);
	}

	@RequestMapping("/new/cat-{catId}/")
	public String viewNewCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// work
	@RequestMapping("/work/")
	public String viewWorkCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 3, index);
	}

	@RequestMapping("/work/cat-{catId}/")
	public String viewWorkCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// policy
	@RequestMapping("/policy/")
	public String viewPolicyCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 4, index);
	}

	@RequestMapping("/policy/cat-{catId}/")
	public String viewPolicyCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// train
	@RequestMapping("/train/")
	public String viewTrainCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 28, index);
	}

	@RequestMapping("/train/cat-{catId}/")
	public String viewTrainCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// events
	@RequestMapping("/events/")
	public String viewEventsCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 6, index);
	}

	@RequestMapping("/events/cat-{catId}/")
	public String viewEventsCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	// space
	@RequestMapping("/space/")
	public String viewSpaceCatIndex(Model model, HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, 7, index);
	}

	@RequestMapping("/space/cat-{catId}/")
	public String viewSpaceCat(Model model, @PathVariable("catId") int catId,
			HttpServletRequest request) {
		model.addAttribute("url", request.getServletPath());
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		return view(model, catId, index);
	}

	@RequestMapping("/download.html")
	public String downloadCenter(Model model, HttpServletRequest request) {
		addCategories(model);
		int sumCount = uploadService.getUploadCount();
		String pageIndex = (String) request.getParameter("pn");
		int index = pageIndexResolve(pageIndex);
		List<Upload> uploads = uploadService.getUploadsByIndexAndPagesize(
				index, 20);
		model.addAttribute("pageCount", (int) Math.ceil((double) sumCount / 20));
		model.addAttribute("uploads", uploads);
		model.addAttribute("sumCount", sumCount);
		model.addAttribute("pageIndex", index);
		return "download";
	}

	private String view(Model model, int catId, int pageIndex) {
		addCategories(model);
		CategoryPojo categoryPojo = CategoryCache.get(catId);
		model.addAttribute("curCat", categoryPojo);

		List<CategoryPojo> cats = null;
		int rootCatId;
		if (categoryPojo.getCatLevel() == 1) {
			rootCatId = categoryPojo.getId();
			model.addAttribute("rootCat", categoryPojo);
			cats = categoryService
					.getCategoriesByParentId(categoryPojo.getId());
		} else {
			rootCatId = CategoryCache.get(categoryPojo.getParentCatId())
					.getId();
			model.addAttribute("rootCat",
					CategoryCache.get(categoryPojo.getParentCatId()));
			model.addAttribute("curCat", categoryPojo);
			cats = categoryService.getCategoriesByParentId(categoryPojo
					.getParentCatId());
		}
		model.addAttribute("childrenCats", cats);

		// concerns
		List<ArticlePojo> concerns = articleService.getConcerns();
		model.addAttribute("concerns", concerns);
		// events
		List<ArticlePojo> events = articleService.getEvents();
		model.addAttribute("events", events);
		// trains
		List<ArticlePojo> trains = articleService.getTrains();
		model.addAttribute("trains", trains);

		if (categoryPojo.isExclusiveArticle()) {
			model.addAttribute("exclusive", true);
			model.addAttribute("exclusiveArticle",
					articleService.getExclusiveArticleByCatId(catId));
		} else {
			List<ArticlePojo> articles = articleService
					.getArticleByCatIdAndPageIndexAndPageSize(catId, pageIndex,
							10);
			int articleCount = articleService.getArticleCountByCatId(rootCatId,
					catId);

			model.addAttribute("articles", articles);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("pageCount",
					(int) Math.ceil((double) articleCount / 10));
			model.addAttribute("articleCount", articleCount);
		}
		return "viewCat";
	}

	private int pageIndexResolve(String pageIndex) {
		int index = 1;
		if (pageIndex != null && !pageIndex.trim().isEmpty()) {
			index = Integer.valueOf(pageIndex.trim());
		}
		return index;
	}
}
