package com.shopping.Ecommerce.services;

import java.io.IOException;
import java.util.List;

import com.shopping.Ecommerce.entity.Merchant;

import javax.mail.MessagingException;


public interface MerchantService {
	
	 public List<Merchant> getMerchants();
	
	 public Merchant findMerchant(long merchant_id);
	 
	 public Merchant addMerchant(Merchant merchant);

	 public Merchant updateMerchant(Merchant merchant);

	 public void deleteMerchant(long parseLong);

	public void sendmail() throws MessagingException, IOException;

}
