package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Reporter;
import cn.seu.cose.service.ReporterService;
import cn.seu.cose.view.util.ViewUtil;


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
	
	@RequestMapping(value="/admin/reporter_list_search-{searchInput}", method=RequestMethod.GET)
	public String searchDesignerByName(@PathVariable("searchInput") String searchInput, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Reporter> list = reporterService.searchReporter(searchInput);
		model.addAttribute("el_list", list);
		return "admin_reporter";
	}
	
	@RequestMapping(value="/admin/del_reporter", method=RequestMethod.POST)
	public void postDelDesigner(@RequestParam("id") int id, HttpServletResponse response) {
		reporterService.deleteReporter(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/reporter_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/reporter_list_certificated", method=RequestMethod.GET)
	public String getCertificatedDesigners(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Reporter> list = reporterService.getCertificatedReportersList();
		model.addAttribute("el_list", list);
		return "admin_reporter";
	}
	
	@RequestMapping(value="/admin/reporter_list_uncertificated", method=RequestMethod.GET)
	public String getUncertificatedDesigners(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Reporter> list = reporterService.getUncertificatedReportersList();
		model.addAttribute("el_list", list);
		return "admin_reporter";
	}
	
	@RequestMapping("/admin/cer_reporter")
	public void certificateReporter(@RequestParam("id") int id, Model model, HttpServletResponse response) {
		reporterService.certificate(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/reporter_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/uncer_reporter")
	public void uncertificateReporter(@RequestParam("id") int id, Model model, HttpServletResponse response) {
		reporterService.uncertificate(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/reporter_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
