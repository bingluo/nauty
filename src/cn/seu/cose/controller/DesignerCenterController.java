package cn.seu.cose.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.seu.cose.command.MessageCommand;
import cn.seu.cose.entity.Comment;
import cn.seu.cose.entity.CommentPojo;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.entity.Work;
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
			response.sendRedirect(ViewUtil.getContextPath() + "/");
			return "index";
		} else {
			basicIssue(model);
			return "signIn";
		}
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public void signIn(Model model, HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {
		Designer designer = designerService.signIn(username, password);
		if (designer == null) {
			response.getWriter().write("0");
		} else {
			basicIssue(model);
			response.getWriter().write("1");
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
			response.sendRedirect(ViewUtil.getContextPath() + "/");
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

	@RequestMapping(value = "/log-off", method = RequestMethod.GET)
	public void logOff(Model model, HttpServletResponse response) {
		designerService.logOff();
		try {
			response.sendRedirect(ViewUtil.getContextPath() + "/");
		} catch (IOException e) {
			e.printStackTrace();
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
	@RequestMapping(value = "/designer/{designerId}/admin/profile", method = RequestMethod.GET)
	public String designerCenterEditProfile(Model model,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);
		Designer curUser = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (curUser == null || curUser.getId() != designerId) {
			return "index";
		}
		model.addAttribute("designer", curUser);
		return "designer/editProfile";
	}

	@RequestMapping(value = "/designer/{designerId}/admin/profile", method = RequestMethod.POST)
	public void editProfile(HttpServletResponse response, Model model,
			@RequestParam("email") String email,
			@RequestParam("intro") String intro,
			@PathVariable("designerId") int designerId) throws IOException {
		basicIssue(model);
		Designer curUser = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		Writer writer = response.getWriter();
		if (curUser == null || curUser.getId() != designerId) {
			writer.write("0");
		} else {
			curUser.setEmail(email);
			curUser.setIntro(intro);
			designerService.updateDesigner(curUser);
			model.addAttribute("curUser", curUser);
			model.addAttribute("designer", curUser);
			writer.write("1");
		}
	}

	/**
	 * change password
	 */
	@RequestMapping(value = "/designer/{designerId}/admin/pswd", method = RequestMethod.GET)
	public String changePswd(Model model, HttpServletResponse response,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);
		Designer designer = designerService.getDesignerById(designerId);
		model.addAttribute("designer", designer);
		if (designerService.isTheSignInOne(designerId)) {
			return "designer/changePswd";
		} else {
			return "designer/designerCenterIndex";
		}
	}

	/**
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = "/designer/{designerId}/admin/pswd", method = RequestMethod.POST)
	public void changePswdPost(Model model, HttpServletResponse response,
			@PathVariable("designerId") int designerId,
			@RequestParam("currentPswd") String currentPswd,
			@RequestParam("newPswd") String newPswd) throws IOException {
		Writer writer = response.getWriter();
		if (designerService.isTheSignInOne(designerId)) {
			Designer designer = designerService.getCurrentUser();
			if (designer.getPassword().equals(currentPswd)) {
				designer.setPassword(newPswd);
				designerService.updateDesigner(designer);
				writer.write("1");
				return;
			}
		}
		writer.write("0");
		return;
	}

	/**
	 * post a new work
	 * 
	 * @return
	 */
	@RequestMapping(value = "/designer/{designerId}/admin/new-work", method = RequestMethod.GET)
	public String newWorkPage(Model model, HttpServletResponse response,
			@PathVariable("designerId") int designerId) {
		basicIssue(model);
		Designer designer = designerService.getDesignerById(designerId);
		model.addAttribute("designer", designer);
		if (designerService.isTheSignInOne(designerId)) {
			return "designer/newWork";
		} else {
			return "designer/designerCenterIndex";
		}

	}

	@RequestMapping(value = "/designer/{designerId}/admin/new-work", method = RequestMethod.POST)
	public void postWork(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam MultipartFile[] file,
			@RequestParam("title") String title,
			@RequestParam("intro") String intro,
			@PathVariable("designerId") int designerId) throws IOException {
		basicIssue(model);
		Designer designer = designerService.getDesignerById(designerId);
		model.addAttribute("designer", designer);
		if (!designerService.isTheSignInOne(designerId)
				|| !designerService.getCurrentUser().isCertificated()) {
			response.sendRedirect(ViewUtil.getContextPath() + "/designer/"
					+ designerId);
			return;
		}
		Work work = new Work();
		work.setWorkName(title);
		work.setIntro(intro);
		work.setUserId(designerId);
		StringBuilder workPics = new StringBuilder();
		String path = request.getSession().getServletContext()
				.getRealPath("static/works");
		boolean notFirst = false;
		for (MultipartFile f : file) {
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = format.format(new Date())
					+ f.getOriginalFilename();
			File targetFile = new File(path, fileName);
			if (f.getContentType().equals("image/jpeg") && !targetFile.exists()) {
				// 保存
				try {
					f.transferTo(targetFile);
					if (notFirst) {
						workPics.append(";");
					}
					workPics.append(fileName);
					notFirst = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		work.setWorkPics(workPics.toString());
		workService.insertWork(work);
		response.sendRedirect(ViewUtil.getContextPath() + "/designer/"
				+ designerId);
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
