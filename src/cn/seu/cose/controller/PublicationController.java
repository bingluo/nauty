package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.PublicationPojo;
import cn.seu.cose.service.PublicationService;

@Controller
public class PublicationController extends AbstractController {

	@Autowired
	PublicationService publicationService;

	@RequestMapping("/publication/")
	public String publicationList(Model model) {
		addCategories(model);
		List<PublicationPojo> publications = publicationService
				.getAllPublications();
		List<String> years = publicationService.classify(publications);
		model.addAttribute("publications", publications);
		model.addAttribute("years", years);

		return "publication_list";
	}
}
