package cn.seu.cose.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailIssue {

	public static void send(String title, String content, String toAddress) {
		try {
			// 建立邮件会话
			Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
			// 存储发送邮件服务器的信息
			props.put("mail.smtp.host", Constant.mailServer);
			// 同时通过验证
			props.put("mail.smtp.auth", "true");
			// 根据属性新建一个邮件会话
			Session s = Session.getInstance(props);
			s.setDebug(true); // 有他会打印一些调试信息。

			// 由邮件会话新建一个消息对象
			MimeMessage message = new MimeMessage(s);

			// 设置邮件
			InternetAddress from = new InternetAddress(Constant.mailAddress);
			// 设置发件人的地址
			message.setFrom(from);
			// 设置收件人,并设置其接收类型为TO
			InternetAddress to = new InternetAddress(toAddress);
			message.setRecipient(Message.RecipientType.TO, to);

			// 设置标题
			message.setSubject(title);
			// 设置信件内容
			message.setContent(content, "text/html;charset=utf-8");
			// 设置发信时间
			message.setSentDate(new Date());
			// 存储邮件信息
			message.saveChanges();

			// 发送邮件
			Transport transport = s.getTransport("smtp");
			// 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect(Constant.mailServer, Constant.mailAccount,
					Constant.mailPassword);
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			// Properties p = new Properties();
			// p.put("mail.smtp.host", Constant.mailServer);
			// p.put("mail.smtp.port", "25");
			// p.put("mail.smtp.auth", "true");
			// Authenticator authenticator = new Authenticator() {
			// @Override
			// protected PasswordAuthentication getPasswordAuthentication() {
			// return new PasswordAuthentication(Constant.mailAccount,
			// Constant.mailPassword);
			// }
			// };
			// Session sendMailSession = Session.getDefaultInstance(p,
			// authenticator);
			// Message mailMessage = new MimeMessage(sendMailSession);
			// Address from = new InternetAddress(Constant.mailAddress);
			// mailMessage.setFrom(from);
			// Address to = new InternetAddress(toAddress);// 设置接收人员
			// mailMessage.setRecipient(Message.RecipientType.TO, to);
			// mailMessage.setSubject(title);// 设置邮件标题
			// mailMessage.setText(content); // 设置邮件内容
			// // 发送邮件
			// Transport.send(mailMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
