package cn.seu.cose.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.command.MessageCommand;
import cn.seu.cose.entity.Comment;
import cn.seu.cose.entity.CommentPojo;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.WorkPojo;
import cn.seu.cose.filter.SecurityContextHolder;
import cn.seu.cose.service.CommentService;
import cn.seu.cose.service.DesignerService;
import cn.seu.cose.service.WorkService;
import cn.seu.cose.util.Constant.CommentType;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class DesignerCenterController extends AbstractController {

	@Autowired
	DesignerService designerService;
	@Autowired
	WorkService workService;
	@Autowired
	CommentService commentService;

	@RequestMapping("/designer/{designerId}")
	public String designerCenterIndex(Model model,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);

		Designer designer = designerService.getDesignerById(designerId);
		List<WorkPojo> works = workService.getWorkByUser(designerId);
		List<CommentPojo> comments = commentService.getCommentsByRefAndType(
				designerId, CommentType.DESIGNER.ordinal());

		model.addAttribute("designer", designer);
		model.addAttribute("works", works);
		model.addAttribute("comments", comments);

		return "designer/designerCenterIndex";
	}

	/**
	 * works page -> browse works
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/works")
	public String designerCenterWorks(Model model,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);

		Designer designer = designerService.getDesignerById(designerId);
		List<WorkPojo> works = workService.getWorkByUser(designerId);

		model.addAttribute("designer", designer);
		model.addAttribute("works", works);

		return "designer/works";
	}

	/**
	 * view work
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/works/{workId}.html")
	public String viewWork(Model model,
			@PathVariable("designerId") int designerId,
			@PathVariable("workId") int workId) {
		basicIssue(model);

		Designer designer = designerService.getDesignerById(designerId);
		WorkPojo work = workService.getWorkViaId(workId);
		List<CommentPojo> comments = commentService.getCommentsByRefAndType(
				workId, CommentType.WORK.ordinal());

		model.addAttribute("designer", designer);
		model.addAttribute("work", work);
		model.addAttribute("comments", comments);

		return "designer/viewWork";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public String signInPage(Model model, HttpServletResponse response)
			throws IOException {
		if (designerService.isSignIn()) {
			response.sendRedirect("/");
			return "index";
		} else {
			basicIssue(model);
			return "signIn";
		}
	}

	/**
	 * register page
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model, HttpServletResponse response)
			throws IOException {
		if (designerService.isSignIn()) {
			response.sendRedirect("/");
			return "index";
		} else {
			basicIssue(model);
			return "register";
		}
	}

	/**
	 * register submit
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registerPage(Model model, HttpServletResponse response,
			@RequestParam("name") String name,
			@RequestParam("pswd") String pswd,
			@RequestParam("email") String email) throws IOException {
		PrintWriter out = response.getWriter();
		if (designerService.isSignIn()) {
			out.write("0");
		} else if (designerService.getDesignerByName(name) != null) {
			out.write("2");
		} else {
			Designer designer = new Designer();
			designer.setUserName(name);
			designer.setPassword(pswd);
			designer.setEmail(email);
			designerService.insertDesigner(designer);
			designer = designerService.getDesignerByName(name);
			designerService.signIn(name, pswd);
			out.write("1");
		}
	}

	/**
	 * leave a message to designer
	 */
	@RequestMapping(value = "/designer/{designerId}/message", method = RequestMethod.POST)
	public void leaveMessage(Model model, MessageCommand command,
			HttpServletResponse response,
			@PathVariable("designerId") int designerId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer != null) {
			Comment comment = new Comment();
			comment.setUserId(designer.getId());
			comment.setContent(command.getMessage());
			comment.setReferenceId(designerId);
			comment.setCommentType(CommentType.DESIGNER.ordinal());
			commentService.insertComment(comment);
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/designer/"
					+ designerId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * comment to work in designer center
	 */
	@RequestMapping(value = "/designer/{designerId}/works/{workId}/comment", method = RequestMethod.POST)
	public void commentWorkInDesignerCenter(Model model,
			MessageCommand command, HttpServletResponse response,
			@PathVariable("designerId") int designerId,
			@PathVariable("workId") int workId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer != null) {
			Comment comment = new Comment();
			comment.setUserId(designer.getId());
			comment.setContent(command.getMessage());
			comment.setReferenceId(workId);
			comment.setCommentType(CommentType.WORK.ordinal());
			commentService.insertComment(comment);
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/designer/"
					+ designerId + "/works/" + workId + ".html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * comment to work in activity
	 */
	@RequestMapping(value = "/activity/{activityId}/works/{workId}/comment", method = RequestMethod.POST)
	public void commentWorkInActivity(Model model, MessageCommand command,
			HttpServletResponse response,
			@PathVariable("activityId") int activityId,
			@PathVariable("workId") int workId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer != null) {
			Comment comment = new Comment();
			comment.setUserId(designer.getId());
			comment.setContent(command.getMessage());
			comment.setReferenceId(workId);
			comment.setCommentType(CommentType.WORK.ordinal());
			commentService.insertComment(comment);
		}
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/activity/"
					+ activityId + "/works/" + workId + ".html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * edit designer's profile
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/admin/profile")
	public String designerCenterEditProfile() {
		return "designer/editProfile";
	}

	/**
	 * post a new work
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/admin/new-work")
	public String newWork() {
		return "designer/newWork";
	}

	/**
	 * post a new blog
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/admin/new-blog")
	public String newBlog() {
		return "designer/newBlog";
	}
}
