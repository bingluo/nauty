package cn.seu.cose.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

@Controller
public class DesignerCenterController extends AbstractController {

	@Autowired
	DesignerService designerService;
	@Autowired
	WorkService workService;
	@Autowired
	CommentService commentService;

	/**
	 * common method
	 * 
	 * @param model
	 * @param designerId
	 */
	private void setIfOwnCenter(Model model, int designerId) {
		if (designerService.isTheSignInOne(designerId)) {
			model.addAttribute("ownCenter", true);
		} else {
			model.addAttribute("ownCenter", false);
		}
	}

	@RequestMapping("/designer/{designerId}")
	public String designerCenterIndex(Model model,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);
		setIfOwnCenter(model, designerId);

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
		setIfOwnCenter(model, designerId);

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
		setIfOwnCenter(model, designerId);
		Designer designer = designerService.getDesignerById(designerId);
		WorkPojo work = workService.getWorkViaId(workId);
		List<CommentPojo> comments = commentService.getCommentsByRefAndType(
				workId, CommentType.WORK.ordinal());

		model.addAttribute("designer", designer);
		model.addAttribute("work", work);
		model.addAttribute("comments", comments);

		return "designer/viewWork";
	}

	/**
	 * leave a message
	 */
	@RequestMapping("/designer/{designerId}/message")
	public void leaveMessage(Model model, MessageCommand command,
			HttpServletResponse response,
			@PathVariable("designerId") int designerId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		designer = designerService.getDesignerById(1);
		if (designer != null) {
			Comment comment = new Comment();
			comment.setUserId(designer.getId());
			comment.setContent(command.getMessage());
			comment.setReferenceId(designerId);
			comment.setCommentType(CommentType.DESIGNER.ordinal());
			commentService.insertComment(comment);
		}
		try {
			response.sendRedirect("/designer/" + designerId);
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
