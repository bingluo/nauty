package cn.seu.cose.entity;

import java.util.Date;

public class CommentPojo {
	private Comment comment;
	private Designer designer;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public int getId() {
		return comment.getId();
	}

	public int getUserId() {
		return comment.getUserId();
	}

	public String getContent() {
		return comment.getContent();
	}

	public Date getCommentTime() {
		return comment.getCommentTime();
	}

	public int getReferenceId() {
		return comment.getReferenceId();
	}

	public int getCommentType() {
		return comment.getCommentType();
	}

	public String getUserName() {
		return designer.getUserName();
	}

	public String getEmail() {
		return designer.getEmail();
	}

	public String getIntro() {
		return designer.getIntro();
	}

	public String getAvatar() {
		return designer.getAvatar();
	}
}
