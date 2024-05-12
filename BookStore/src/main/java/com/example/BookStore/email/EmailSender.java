package com.example.BookStore.email;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.BookStore.entity.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSender {
	private final JavaMailSender mailSender;
	public void sendActivationLink(User user, String siteURL, String code)
	        throws MessagingException, UnsupportedEncodingException {
	    String toAddress = user.getEmail();
	    String fromAddress = "tuine09@gmail.com";
	    String senderName = "Book Store";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getFullName());
	    String verifyURL = siteURL + "/verify?code=" + code;
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	     
	}

	public void sendLinkResetPass(User user, String siteURL, String email)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "tuine09@gmail.com";
		String senderName = "Book Store";
		String subject = "Create a new password";
		String content = "Dear [[name]],<br>"
				+ "Please click the link below to create new password:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">Go to create new password</a></h3>"
				+ "Thank you,<br>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getFullName());
		String verifyURL = siteURL + "/reset-password?email=" + email;

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);

	}
}
