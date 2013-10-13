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

import cn.seu.cose.entity.Upload;
import cn.seu.cose.service.UploadService;

@Controller
public class AdminUploadController extends AbstractController {
	
	public static final int PAGE_SIZE = 10;
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping("/admin/upload_list")
	public String uploadList(Model model) {
		putAdmin(model);
		List<Upload> list = uploadService.getUploads();
		model.addAttribute("upload_list", list.subList(0, getToIndex(list, PAGE_SIZE)));
		model.addAttribute("pageIndex", 1);
		model.addAttribute("prePageIndex", 0);
		model.addAttribute("nextPageIndex", 2);
		model.addAttribute("pageCount", getCount(list.size()));
		return "admin_uploads";
	}
	
	@RequestMapping("/admin/upload_list-{pageIndex}")
	public String uploadList(@PathVariable("pageIndex")String pageIndexStr, Model model) {
		putAdmin(model);
		int pageIndex = Integer.parseInt(pageIndexStr);
		List<Upload> list = uploadService.getUploads();
		model.addAttribute("upload_list", list.subList((pageIndex-1)*PAGE_SIZE, getToIndex(list, pageIndex*PAGE_SIZE)));
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("prePageIndex", pageIndex-1);
		model.addAttribute("nextPageIndex", pageIndex+1);
		model.addAttribute("pageCount", getCount(list.size()));
		return "admin_uploads";
	}
	
	@RequestMapping(value="/admin/add_upload", method=RequestMethod.POST)
	public void postAdd(@RequestParam("initialName")String initialName, @RequestParam("uploadName") String uploadName, 
			HttpServletResponse response) {
		Upload upload = new Upload();
		upload.setInitialName(initialName);
		upload.setUploadName(uploadName);
		upload.setUploadTime(new Date());
		uploadService.addUpload(upload);
	}
	
	@RequestMapping(value="/admin/add_upload", method=RequestMethod.GET)
	public String getAdd(Model model) {
		putAdmin(model);
		return "admin_uploads_add";
	}
	
	@RequestMapping("/admin/del_upload")
	public void postDel(@RequestParam("id") String idStr,  HttpServletResponse response) {
		try {
			int id = Integer.parseInt(idStr);
			uploadService.deleteUpload(id);
			response.sendRedirect("/admin/upload_list");  //******
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
	
	@RequestMapping(value="/admin/alt_upload", method=RequestMethod.POST)
	public void postAlt(@RequestParam("id")String idStr, @RequestParam("uploadName") String uploadName, 
			@RequestParam("initialName") String url, HttpServletResponse response) {
		Upload upload = new Upload();
		upload.setId(Integer.parseInt(idStr));
		upload.setInitialName(url);
		upload.setUploadName(uploadName);
		upload.setUploadTime(new Date());
		uploadService.updateUpload(upload);
		try {
			response.sendRedirect("admin/upload_list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/alt_upload-{id}", method=RequestMethod.GET)
	public String getAlt(@PathVariable("id")String idStr, Model model) {
		putAdmin(model);
		Upload upload = uploadService.getUpload(Integer.parseInt(idStr));
		model.addAttribute("upload", upload);
		return "admin_uploads_alt";
	}
	
	public int getToIndex(List<Upload> list, int originalTo) {
		if (list.size() < originalTo) {
			return list.size();
		} else {
			return originalTo;
		}
	}
	
	public int getCount(int total) {
		if (total <= PAGE_SIZE) {
			return 1;
		} else if (total%PAGE_SIZE == 0) {
			return total/PAGE_SIZE;
		} else {
			return total/PAGE_SIZE + 1;
		}
	}
}
