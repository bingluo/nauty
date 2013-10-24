package cn.seu.cose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.service.PublicationService;

@Controller
public class PublicationController extends AbstractController {

	@Autowired
	PublicationService publicationService;

	@RequestMapping("/publication/")
	public String publicationList(Model model) {
		addCategories(model);
		return "publication_list";
	}
}
