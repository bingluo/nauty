package cn.seu.cose.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.seu.cose.command.ContactCommand;
import cn.seu.cose.service.ParameterService;
import cn.seu.cose.util.Constant;
import cn.seu.cose.util.MailIssue;

@Controller
public class ContactController extends AbstractController {

	@Autowired
	ParameterService parameterService;

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public void contact(Model model, ContactCommand command,
			HttpServletResponse response) {
		StringBuilder title = new StringBuilder();
		title.append("【JIDA】‘").append(command.getName()).append("’给网站留言了！");

		StringBuilder content = new StringBuilder();
		content.append("<html>");
		content.append("<h3>标题：【").append(command.getSubject())
				.append("】——来自：").append(command.getName()).append("</h3>");
		content.append("<p>内容：")
				.append(command.getMessage().replaceAll("\n", "<\br>"))
				.append("</p>");
		content.append("<p>他（她）的邮箱：").append(command.getEmail()).append("</p>");

		String toAddress = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_EMAIL).getParameterValue();
		try {
			MailIssue.send(title.toString(), content.toString(), toAddress);
			PrintWriter out = response.getWriter();
			out.write("1");
		} catch (Exception e) {
		}
	}

	@RequestMapping(value = "/contact.html")
	public String contact(Model model) {
		addCategories(model);
		String location = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_LOCATION).getParameterValue();
		String gpslocation = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_GPS_LOCATION).getParameterValue();
		String contactIntro = parameterService.getParameterByKey(
				Constant.PARAMETER_KEY_CONTACT).getParameterValue();

		model.addAttribute("location", location);
		model.addAttribute("gpslocation", gpslocation);
		model.addAttribute("contactIntro", contactIntro);
		return "contact";
	}
}
