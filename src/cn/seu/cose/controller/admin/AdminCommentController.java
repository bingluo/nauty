package cn.seu.cose.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.seu.cose.entity.CommentPojo;
import cn.seu.cose.service.CommentService;
import cn.seu.cose.util.Constant.CommentType;
import cn.seu.cose.view.util.ViewUtil;

@Controller
public class AdminCommentController extends AbstractController {

	@Autowired
	CommentService commtService;

	// list all comments of an article
	@RequestMapping(value = "/admin/comment_list-{refId}", method = RequestMethod.GET)
	public String articleList(@PathVariable(value = "refId") int refId,
			Model model, HttpServletResponse response) {
		putAdmin(model, response);
		List<CommentPojo> list = commtService.getCommentsByRefAndType(refId,
				CommentType.ARTICLE.ordinal());
		model.addAttribute("commet_list", list);
		return "admin_comments";
	}

	// delete one comment
	@RequestMapping(value = "/admin/del_comment", method = RequestMethod.POST)
	public void postDel(@RequestParam("refId") int refId,
			@RequestParam("commtId") int commtId, Model model,
			HttpServletResponse response) {
		try {
			commtService.deleteComment(commtId);
			response.sendRedirect(ViewUtil.getContextPath()
					+ "/admin/comment_list-" + refId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
