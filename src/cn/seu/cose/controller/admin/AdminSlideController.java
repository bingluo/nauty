package cn.seu.cose.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.Slide;
import cn.seu.cose.service.SlideService;

@Controller
public class AdminSlideController {
	@Autowired
	private SlideService slideService;
	
	@RequestMapping("/admin/slide_list")
	public String slideList(Model model) {
		List<Slide> list = slideService.getSlides();
		model.addAttribute("slide_list", list);
		return "admin_slides";
	}
}
