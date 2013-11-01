package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.PublicationPojo;
import cn.seu.cose.service.PublicationService;

@Controller
public class AdminPubController extends AbstractController{
	
	@Autowired
	PublicationService pubService;
	
	@RequestMapping(value="/admin/pub_list", method=RequestMethod.GET)
	public String pubList(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<PublicationPojo> list = pubService.getAllPublications();
		model.addAttribute("pub_list", list);
		return "admin_pubs";
	}
	
	@RequestMapping(value="/admin/add_pub", method=RequestMethod.GET)
	public String getAdd(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		return "admin_pubs_add";
	}
	
	@RequestMapping(value="/admin/add_pub", method=RequestMethod.POST)
	public void postAdd(@RequestParam("title")String title, @RequestParam("intro") String intro, 
			@RequestParam("images")String images, Model model, HttpServletResponse response) {
		
		PublicationPojo pub = new PublicationPojo();
		pub.setTitle(title);
		pub.setIntro(intro);
		pub.setImages(images);
		pub.setPostTime(new Date());
		pubService.addPublication(pub);
	}
	
	@RequestMapping(value="/admin/del_pub", method=RequestMethod.POST)
	public void postDel(@RequestParam("id") String idStr,  HttpServletResponse response) {
		try {
			pubService.deletePublication(Integer.parseInt(idStr));
			response.sendRedirect("/admin/pub_list");
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("error", 1);
			try {
				response.getWriter().write(jo.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@RequestMapping(value="/admin/alt_pub-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id") String idStr, Model model, HttpServletResponse response) {
		putAdmin(model, response);
		PublicationPojo pub = pubService.getPublicationById(Integer.parseInt(idStr));
		model.addAttribute("pub", pub);
		return "admin_pubs_alt";
	}
	
	@RequestMapping(value="/admin/alt_pub", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id") String idStr, @RequestParam("title")String title, @RequestParam("intro") String intro, 
			@RequestParam("images")String images, Model model, HttpServletResponse response) {
		PublicationPojo pub = new PublicationPojo();
		pub.setId(Integer.parseInt(idStr));
		pub.setTitle(title);
		pub.setIntro(intro);
		pub.setImages(images);
		pub.setPostTime(new Date());
		pubService.updatePublication(pub);
	}
	
}
