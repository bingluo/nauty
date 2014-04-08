package cn.seu.cose.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.Reporter;
import cn.seu.cose.service.ReporterService;


@Controller
public class AdminReporterController extends AbstractController{
	
	@Autowired ReporterService reporterService;
	
	@RequestMapping("/admin/reporter_list")
	public String reporterList(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		model.addAttribute("searchInput", "");
		List<Reporter> list = reporterService.getAllReportersList();
		model.addAttribute("el_list", list);
		return "admin_reporter";
	}
	
}
