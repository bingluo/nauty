package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Upload;
import cn.seu.cose.service.UploadService;

@Controller
public class AdminUploadController {
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping("/admin/upload_list")
	public String uploadList(Model model) {
		List<Upload> list = uploadService.getUploads();
		model.addAttribute("upload_list", list);
		return "admin_uploads";
	}
	
	@RequestMapping("/admin/add_upload")
	public void postAdd(HttpServletResponse response) {
		/* 
		 * TODO
		 */
		try {
			response.sendRedirect("/admin/upload_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/del_upload")
	public void postDel(@RequestParam("id") String idStr,  HttpServletResponse response) {
		try {
			int id = Integer.parseInt(idStr);
			uploadService.deleteUpload(id);
			response.sendRedirect("admin/upload_list");  //******
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/alt_upload")
	public void postAlt(Upload altedUp, HttpServletResponse response) {
		/*
		 * TODO
		 */
		try {
			response.sendRedirect("admin/upload_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/get_upload", method=RequestMethod.POST) 
	public void fetchUpload(@RequestParam("id") int id, HttpServletResponse response) {
		Upload upload = uploadService.getUpload(id);
		JSONObject jo = new JSONObject();
		jo.put("id", upload.getId());
		jo.put("initialName", upload.getInitialName());
		jo.put("uploadName", upload.getUploadName());
		jo.put("uploadTime", upload.getUploadTime());
		response.setContentType("UTF-8");
		try {
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
