package cn.seu.cose.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Article;
import cn.seu.cose.entity.ArticlePojo;
import cn.seu.cose.entity.CategoryPojo;
import cn.seu.cose.service.ArticleService;
import cn.seu.cose.service.CategoryService;
import cn.seu.cose.service.ReporterService;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class AdminArticleController extends AbstractController{
	@Autowired
	private ArticleService articleService; 
	@Autowired
	private CategoryService catService;
	@Autowired
	private ReporterService reporterService;
	
	
	public static final int PAGE_SIZE = 10;
	
	@RequestMapping("/admin/article_list-{topCatId}-{subCatId}-{pageIndex}")
	public String articleList(@PathVariable(value="topCatId") String topCatIdStr, @PathVariable(value="subCatId") String subCatIdStr, 
			@PathVariable(value="pageIndex") String pageIndexStr, Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putAdmin(model, response);
		
		int topCatId = Integer.parseInt(topCatIdStr);
		int subCatId = Integer.parseInt(subCatIdStr);
		int pageIndex = Integer.parseInt(pageIndexStr);
		
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(topCatId);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		int catId = subCatId<=0 ? topCatId : subCatId;
		int index = pageIndex<=0 ? 1 : pageIndex;
		List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(catId, index, PAGE_SIZE);
		model.addAttribute("article_list", list);
		
		model.addAttribute("topCatId", topCatId);
		model.addAttribute("subCatId", subCatId);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(topCatId,subCatId)));
		
		return "admin_articles";
	}
	
	@RequestMapping("/admin/article_list_search-{searchInput}")
	public String searchArticle(@PathVariable("searchInput")String searchInput, Model model, HttpServletRequest request, HttpServletResponse response) {
		putAdmin(model, response);
		List<ArticlePojo> list = articleService.searchArticle(searchInput);
		model.addAttribute("article_list", list);
		model.addAttribute("searchInput", searchInput);
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(2);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		model.addAttribute("topCatId", 2);
		model.addAttribute("subCatId", 0);
		model.addAttribute("pageIndex", 1);
		model.addAttribute("nextPageIndex", 2);
		model.addAttribute("prePageIndex", 0);
		model.addAttribute("pageCount", getPageCount(list.size()));
		return "admin_articles";
	}
	
	
	@RequestMapping("/admin/article_list")
	public String articleList(Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putAdmin(model, response);
		
		List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(2, 1, PAGE_SIZE); // init
		model.addAttribute("article_list", list);
		
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(2);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		model.addAttribute("topCatId", 2);
		model.addAttribute("subCatId", 0);
		model.addAttribute("pageIndex", 1);
		model.addAttribute("nextPageIndex", 2);
		model.addAttribute("prePageIndex", 0);
		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(2, 0)));
		return "admin_articles";
	}
	
	@RequestMapping(value="/admin/add_article", method=RequestMethod.GET)
	public String getAdd(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(1);
		model.addAttribute("init_sub_cat_list" ,categories);
		
		return "admin_articles_add";
	}
	
	@RequestMapping(value="/admin/add_article", method=RequestMethod.POST)
	public void postAdd(@RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("catId") String catIdStr, @RequestParam("rootCatId") String rootCatIdStr,
			@RequestParam("content") String content, @RequestParam("from") String from, @RequestParam("pure") String pure,
			HttpServletResponse response) {
		
		CategoryPojo cat = catService.getCategoryById(Integer.parseInt(catIdStr));
		
		ArticlePojo article = new ArticlePojo();
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(Integer.parseInt(catIdStr));
		article.setRootCatId(Integer.parseInt(rootCatIdStr));
		article.setContent(content);
		article.setFrom(from);
		article.setPostTime(new Date());
		pure = wrapPure(pure);
		int briefLength = pure.length() > 100 ? 100 : pure.length();
		article.setPureText(pure.substring(0, briefLength) + "……");
		
		if (cat.isExclusiveArticle()) {
			List<ArticlePojo> list = articleService.getArticlesByCatId(Integer.parseInt(catIdStr));
			if (list != null && !list.isEmpty()) {
				ArticlePojo temp = list.get(0);
				article.setId(temp.getId());
				articleService.updateArticle(article);
				return;
			}
		}
		articleService.addArticle(article);
	}
	
	@RequestMapping(value="/admin/del_article", method=RequestMethod.POST)
	public void postDel(@RequestParam("id") String idStr, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(idStr);
			articleService.deleteArticle(id);
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/article_list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/del_articles", method=RequestMethod.POST)
	public void postDels(@RequestParam("ids") String idsStr, HttpServletResponse response) {
		if(idsStr == "") {
				//
		} else {
			for (String idStr : idsStr.split("-")) {
				articleService.deleteArticle(Integer.parseInt(idStr));
			}
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/article_list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/admin/alt_article-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id") String idStr, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		
		Article article = articleService.getArticleById(Integer.parseInt(idStr));
		model.addAttribute("article", article);
		/* get cats */
		List<CategoryPojo> categories = catService.getRootCategories();
		model.addAttribute("top_cat_list", categories);
		categories = catService.getCategoriesByParentId(article.getRootCatId());
		model.addAttribute("init_sub_cat_list" ,categories);
		
		return "admin_articles_alt";
	}
	
	@RequestMapping(value="/admin/alt_article", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id") String idStr, @RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("catId") String catIdStr, @RequestParam("rootCatId") String rootCatIdStr,
			@RequestParam("content") String content, @RequestParam("from") String from, @RequestParam("pure") String pure,
			HttpServletResponse response) {
		ArticlePojo article = new ArticlePojo();
		article.setId(Integer.parseInt(idStr));
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(Integer.parseInt(catIdStr));
		article.setRootCatId(Integer.parseInt(rootCatIdStr));
		article.setContent(content);
		article.setFrom(from);
		article.setPostTime(new Date());
		pure = wrapPure(pure);
		int briefLength = pure.length() > 100 ? 100 : pure.length();
		article.setPureText(pure.substring(0, briefLength) + "……");
		articleService.updateArticle(article);
	}
	
	private int getPageCount(int count) {
		if (count <= PAGE_SIZE) {
			return 1;
		} else if (count%PAGE_SIZE == 0) {
			return count/PAGE_SIZE;
		} else {
			return count/PAGE_SIZE +1;
		}
	}
	
	private static String wrapPure(String pure) {
		Pattern p_image;
		String regEx_img = "<img.*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = p_image.matcher(pure);
		while(matcher.find()) {
			System.out.println(matcher.group());
			pure = matcher.replaceAll("");
		}
		return pure;
	}
	
//***************************通讯员相关的文章操作*******************************//
	@RequestMapping(value="/reporter/article_list-{id}")
	public String getArticleListOfMe(@PathVariable("id") int reporterId, Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putReporter(model,response);
		
		//List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(2, 1, PAGE_SIZE); // init
		List<ArticlePojo> list = articleService.getContributedArticlesOfReporter(reporterId);
		model.addAttribute("el_list", list);
		
//		model.addAttribute("pageIndex", 1);
//		model.addAttribute("nextPageIndex", 2);
//		model.addAttribute("prePageIndex", 0);
//		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(2, 0)));
		return "reporter/article_list";
	}
	@RequestMapping(value="/reporter/article_list_acc-{id}")
	public String getAccArticleListOfMe(@PathVariable("id") int reporterId, Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putReporter(model,response);
		
		//List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(2, 1, PAGE_SIZE); // init
		List<ArticlePojo> list = articleService.getAcceptArticlesOfReporter(reporterId);
		model.addAttribute("el_list", list);
		
//		model.addAttribute("pageIndex", 1);
//		model.addAttribute("nextPageIndex", 2);
//		model.addAttribute("prePageIndex", 0);
//		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(2, 0)));
		return "reporter/article_list";
	}
	@RequestMapping(value="/reporter/article_list_waiting-{id}")
	public String getWaitArticleListOfMe(@PathVariable("id") int reporterId, Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putReporter(model,response);
		
		//List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(2, 1, PAGE_SIZE); // init
		List<ArticlePojo> list = articleService.getWaitArticlesOfReporter(reporterId);
		model.addAttribute("el_list", list);
		
//		model.addAttribute("pageIndex", 1);
//		model.addAttribute("nextPageIndex", 2);
//		model.addAttribute("prePageIndex", 0);
//		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(2, 0)));
		return "reporter/article_list";
	}
	@RequestMapping(value="/reporter/article_list_rej-{id}")
	public String getRejArticleListOfMe(@PathVariable("id") int reporterId, Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", "");
		putReporter(model,response);
		
		//List<ArticlePojo> list = articleService.getArticleByCatIdAndPageIndexAndPageSize(2, 1, PAGE_SIZE); // init
		List<ArticlePojo> list = articleService.getRejectArticlesOfReporter(reporterId);
		model.addAttribute("el_list", list);
		
//		model.addAttribute("pageIndex", 1);
//		model.addAttribute("nextPageIndex", 2);
//		model.addAttribute("prePageIndex", 0);
//		model.addAttribute("pageCount", getPageCount(articleService.getArticleCountByCatId(2, 0)));
		return "reporter/article_list";
	}
	
	@RequestMapping(value="/reporter/reporter-{id}articles_search-{searchInput}")
	public String searchArticleListOfMe(@PathVariable("id") int reporterId, @PathVariable("searchInput") String searchInput,
			Model model, HttpServletResponse response) {
		model.addAttribute("searchInput", searchInput);
		putReporter(model,response);
		List<ArticlePojo> list = articleService.searchArticleOfReporter(reporterId, searchInput);
		model.addAttribute("el_list", list);
		return "reporter/article_list";
	}
	
	
	@RequestMapping(value="/reporter/contribute", method=RequestMethod.GET)
	public String getContribute(Model model, HttpServletResponse response) {
		putReporter(model, response);
		return "reporter/contribute_article";
	}
	@RequestMapping(value="/reporter/contribute", method=RequestMethod.POST)
	public void postContribute(@RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("content") String content, @RequestParam("pure") String pure, @RequestParam("contributed_from") int reporterId,
			HttpServletResponse response) {
		
		int catId = 16;
		int rootCatId = 2;
		
		ArticlePojo article = new ArticlePojo();
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(catId);
		article.setRootCatId(rootCatId);
		article.setContent(content);
		article.setPostTime(new Date());
		pure = wrapPure(pure);
		int briefLength = pure.length() > 100 ? 100 : pure.length();
		article.setPureText(pure.substring(0, briefLength) + "……");
		article.setContributedFrom(reporterId);	// reporter贡献的文章
		articleService.contributeArticle(article);
		
		// 增加reporter的贡献率
		reporterService.incrContribute(reporterId);
	}
	
	@RequestMapping(value="/reporter/alt_contribute-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id") int id, Model model, HttpServletResponse response) {
		putReporter(model, response);
		ArticlePojo article = articleService.getArticleById(id);
		model.addAttribute("el", article);
		return "/reporter/alt_article";
	}
	@RequestMapping(value="/reporter/alt_contribute", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("subhead") String subhead,
			@RequestParam("content") String content, @RequestParam("pure") String pure, @RequestParam("contributed_from") int reporterId,
			HttpServletResponse response) {
		
		int catId = 17;
		int rootCatId = 2;
		
		ArticlePojo article = new ArticlePojo();
		article.setId(id);
		article.setTitle(title);
		article.setSubhead(subhead);
		article.setCatId(catId);
		article.setRootCatId(rootCatId);
		article.setContent(content);
		article.setPostTime(new Date());
		pure = wrapPure(pure);
		int briefLength = pure.length() > 100 ? 100 : pure.length();
		article.setPureText(pure.substring(0, briefLength) + "……");
		article.setContributedFrom(reporterId);	// reporter贡献的文章
		articleService.updateContributedArticle(article);
	}
	
	
}
