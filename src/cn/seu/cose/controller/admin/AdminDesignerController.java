package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Designer;
import cn.seu.cose.service.DesignerService;
import cn.seu.cose.view.util.ViewUtil;


@Controller
public class AdminDesignerController extends AbstractController{
	
	@Autowired
	DesignerService designerService;
	
	
	@RequestMapping(value="/admin/designer_list", method=RequestMethod.GET)
	public String getDesigners(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Designer> list = designerService.getAllDesigners();
		model.addAttribute("designer_list", list);
		return "admin_designers";
	}
	
	@RequestMapping(value="/admin/designer_list_search-{searchInput}", method=RequestMethod.GET)
	public String searchDesignerByName(@PathVariable("searchInput") String searchInput, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		Designer designer = designerService.getDesignerByName(searchInput);
		List<Designer> list = new ArrayList<Designer>();
		list.add(designer);
		model.addAttribute("designer_list", list);
		return "admin_designers";
	}
	
	@RequestMapping(value="/admin/del_designer", method=RequestMethod.POST)
	public void postDelDesigner(@RequestParam("id") int id, HttpServletResponse response) {
		designerService.deleteDesigner(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/designer_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/designer_list_certificated", method=RequestMethod.GET)
	public String getCertificatedDesigners(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Designer> list = designerService.getAllCertificatedDesigners();
		model.addAttribute("designer_list", list);
		return "admin_designers";
	}
	
	@RequestMapping(value="/admin/designer_list_uncertificated", method=RequestMethod.GET)
	public String getUncertificatedDesigners(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Designer> list = designerService.getAllUncertificatedDesigners();
		model.addAttribute("designer_list", list);
		return "admin_designers";
	}
	
	@RequestMapping(value="/admin/cer_designer", method=RequestMethod.POST)
	public void certificateDesigner(@RequestParam("id") int id, HttpServletResponse response) {
		designerService.certificateDesignerById(id);
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/designer_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
