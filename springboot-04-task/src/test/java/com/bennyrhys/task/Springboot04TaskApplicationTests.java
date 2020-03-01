package com.bennyrhys.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot04TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl javaMailSender;

	@Test
	void contextLoads() {
		//简单邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		//邮件设置
		mailMessage.setSubject("升职记");
		mailMessage.setText("今天敲代码");

		mailMessage.setTo("1755926325@qq.com");
		mailMessage.setFrom("2389992466@qq.com");
		javaMailSender.send(mailMessage);
	}

	@Test
	void test02() throws Exception {

		//创建一个复杂邮件
		MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


		//邮件设置
		helper.setSubject("升职记");
		helper.setText("<b style='color:red'>今天敲代码</b >",true);
		//上传文件
		helper.addAttachment("ic_launcher.png",new File("/Users/bennyrhys/Downloads/ic_launcher.png"));
		helper.addAttachment("未标题-1.jpg",new File("/Users/bennyrhys/Downloads/未标题-1.jpg"));

		helper.setTo("1755926325@qq.com");
		helper.setFrom("2389992466@qq.com");
		javaMailSender.send(mimeMessage);
	}

}
