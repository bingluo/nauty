package cn.seu.cose.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.PublicationPojo;
import cn.seu.cose.service.PublicationService;

@Controller
public class PublicationController extends AbstractController {

	@Autowired
	PublicationService publicationService;

	// publication-navi
	@RequestMapping("/publication-navi")
	public String viewPublicationCatIndex(Model model,
			HttpServletRequest request, HttpServletResponse response) {
		basicIssue(model);
		List<PublicationPojo> publicationsList = publicationService
				.getRecentPublicationsByType(1);
		List<PublicationPojo> companyPublicationsList = publicationService
				.getRecentPublicationsByType(2);
		List<PublicationPojo> culturePublicationsList = publicationService
				.getRecentPublicationsByType(3);
		model.addAttribute("publicationsList", publicationsList);
		model.addAttribute("companyPublicationsList", companyPublicationsList);
		model.addAttribute("culturePublicationsList", culturePublicationsList);
		return "publication_navi";
	}

	@RequestMapping("/publication")
	public String publicationList(Model model) {
		basicIssue(model);
		List<PublicationPojo> publications = publicationService
				.getAllPublicationsByType(1);
		List<String> years = publicationService.classify(publications);
		model.addAttribute("publications", publications);
		model.addAttribute("years", years);
		model.addAttribute("publicationTypeUrl", "publication");
		model.addAttribute("publicationTypeName", "协会刊物");
		model.addAttribute("titleName", "协会刊物");
		return "publication_list";
	}

	@RequestMapping("/publication-com")
	public String publicationCompanyList(Model model) {
		basicIssue(model);
		List<PublicationPojo> publications = publicationService
				.getAllPublicationsByType(2);
		List<String> years = publicationService.classify(publications);
		model.addAttribute("publications", publications);
		model.addAttribute("years", years);
		model.addAttribute("publicationTypeUrl", "publication-com");
		model.addAttribute("publicationTypeName", "企业内刊");
		model.addAttribute("titleName", "企业内刊");

		return "publication_list";
	}

	@RequestMapping("/publication-cul")
	public String publicationCultureList(Model model) {
		basicIssue(model);
		List<PublicationPojo> publications = publicationService
				.getAllPublicationsByType(3);
		List<String> years = publicationService.classify(publications);
		model.addAttribute("publications", publications);
		model.addAttribute("years", years);
		model.addAttribute("publicationTypeUrl", "publication-url");
		model.addAttribute("publicationTypeName", "文化交流");
		model.addAttribute("titleName", "文化交流");

		return "publication_list";
	}

	@RequestMapping("/publication/{id}.html")
	public String publication(@PathVariable("id") int id, Model model) {
		basicIssue(model);
		PublicationPojo publication = publicationService.getPublicationById(id);
		publicationService.clickUp(id);
		List<PublicationPojo> recentPublications = publicationService
				.getRecentPublications();
		model.addAttribute("recentPublications", recentPublications);
		model.addAttribute("publication", publication);
		model.addAttribute("publicationTypeUrl", "publication");
		model.addAttribute("publicationTypeName", "协会刊物");
		model.addAttribute("titleName", publication.getTitle());
		return "publication";
	}

	@RequestMapping("/publication-com/{id}.html")
	public String publicationCompany(@PathVariable("id") int id, Model model) {
		basicIssue(model);
		PublicationPojo publication = publicationService.getPublicationById(id);
		publicationService.clickUp(id);
		List<PublicationPojo> recentPublications = publicationService
				.getRecentPublications();
		model.addAttribute("recentPublications", recentPublications);
		model.addAttribute("publication", publication);
		model.addAttribute("publicationTypeUrl", "publication-com");
		model.addAttribute("publicationTypeName", "企业内刊");
		model.addAttribute("titleName", publication.getTitle());
		return "publication";
	}

	@RequestMapping("/publication-cul/{id}.html")
	public String publicationCulture(@PathVariable("id") int id, Model model) {
		basicIssue(model);
		PublicationPojo publication = publicationService.getPublicationById(id);
		publicationService.clickUp(id);
		List<PublicationPojo> recentPublications = publicationService
				.getRecentPublications();
		model.addAttribute("recentPublications", recentPublications);
		model.addAttribute("publication", publication);
		model.addAttribute("publicationTypeUrl", "publication-url");
		model.addAttribute("publicationTypeName", "文化交流");
		model.addAttribute("titleName", publication.getTitle());
		return "publication";
	}
}
