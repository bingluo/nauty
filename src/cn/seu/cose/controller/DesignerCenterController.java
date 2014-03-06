package cn.seu.cose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.seu.cose.entity.CommentPojo;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.Work;
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
		List<Work> works = workService.getWorkByUser(designerId);
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
		List<Work> works = workService.getWorkByUser(designerId);

		model.addAttribute("designer", designer);
		model.addAttribute("works", works);

		return "designer/works";
	}

	/**
	 * view work
	 * 
	 * @return
	 */
	@RequestMapping("/designer/{designerId}/works/{workId}")
	public String viewWork(Model model,
			@PathVariable("designerId") int designerId,
			@PathVariable("workId") int workId) {
		basicIssue(model);
		setIfOwnCenter(model, designerId);
		Designer designer = designerService.getDesignerById(designerId);
		Work work = workService.getWorkViaId(workId);
		List<CommentPojo> comments = commentService.getCommentsByRefAndType(
				workId, CommentType.WORK.ordinal());

		model.addAttribute("designer", designer);
		model.addAttribute("work", work);
		model.addAttribute("comments", comments);

		return "designer/viewWork";
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
