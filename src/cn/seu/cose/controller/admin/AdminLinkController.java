package cn.seu.cose.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Link;
import cn.seu.cose.service.LinkService;
import cn.seu.cose.view.util.ViewUtil;


@Controller
public class AdminLinkController extends AbstractController{

	@Autowired
	LinkService linkService;
	
	@RequestMapping(value="/admin/links_list", method=RequestMethod.GET)
	public String getLinkslist(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		model.addAttribute("el_list", linkService.getAllLinks());
		return "admin_links";
	}
	
	@RequestMapping(value="/admin/del_link", method=RequestMethod.POST)
	public void delLink(@RequestParam("id")int id, Model model, HttpServletResponse response) {
		try {
			linkService.deleteLink(id);
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/links_list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/admin/add_link", method=RequestMethod.GET)
	public String getAdd(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		return "admin_link_add";
	}
	
	@RequestMapping(value="/admin/add_link", method=RequestMethod.POST) 
	public void postAdd(@RequestParam("name") String name, @RequestParam("url") String url, 
			Model model, HttpServletResponse response) {
		Link link = new Link();
		link.setName(name);
		link.setUrl(url);
		linkService.insertLink(link);
	}
	
	
}
