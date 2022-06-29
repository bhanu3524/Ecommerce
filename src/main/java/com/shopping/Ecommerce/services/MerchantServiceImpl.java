package com.shopping.Ecommerce.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.Ecommerce.dao.MerchantDAO;
import com.shopping.Ecommerce.entity.Merchant;

import javax.mail.*;
import javax.mail.internet.*;

@Service
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	private MerchantDAO merchantdao;

	@Override
	public List<Merchant> getMerchants() {
		return merchantdao.findAll();
	}

	@Override
	public Merchant findMerchant(long merchant_id) {
		return merchantdao.findById(merchant_id).get();
	}

	@Override
	public Merchant addMerchant(Merchant merchant) {


		return merchantdao.save(merchant.getProduct());
	} 
	
	@Override
	public Merchant updateMerchant(Merchant merchant) {
		return merchantdao.save(merchant);
			
	}

	@Override
	public void deleteMerchant(long parseLong) {
		Merchant entity =merchantdao.getOne(parseLong);
		
		merchantdao.delete(entity);
	}


	@Override
	public void sendmail() throws MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("cherukuribhanu5@gmail.com", "Bhanu8253844g");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("cherukuribhanu5@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ch.bhanu825@gmail.com"));
		msg.setSubject("Tutorials point email");
		msg.setContent("Tutorials point email", "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Tutorials point email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		MimeBodyPart attachPart = new MimeBodyPart();

		attachPart.attachFile("/var/tmp/image19.png");
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);

	}




	
	

}
