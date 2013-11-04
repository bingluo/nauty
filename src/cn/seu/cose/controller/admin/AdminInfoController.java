package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.Parameter;
import cn.seu.cose.service.ParameterService;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class AdminInfoController extends AbstractController{
	
	@Autowired
	ParameterService paramService;
	
	
	@RequestMapping(value="/admin/info", method=RequestMethod.GET)
	public String getInfos(Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<Parameter> list = paramService.getAllParameters();
		model.addAttribute("info_list", list);
		return "admin_infos";
	}
	
	@RequestMapping(value="/admin/info", method=RequestMethod.POST)
	public void postInfos(@RequestParam("key")String key, @RequestParam("param_value") String value, HttpServletResponse response) {
		if (key != null && !key.isEmpty()) {
			if (value!=null && !value.isEmpty()) {
				paramService.updateParameterValue(key, value);
			}
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/admin/info");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
