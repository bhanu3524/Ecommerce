package com.shopping.Ecommerce.controller;

import com.shopping.Ecommerce.dto.MerchantOnboarding;
import com.shopping.Ecommerce.entity.AuthenticationRequest;
import com.shopping.Ecommerce.entity.AuthenticationResponse;
import com.shopping.Ecommerce.entity.Merchant;
import com.shopping.Ecommerce.entity.Product;
import com.shopping.Ecommerce.services.MyUserDetailsService;

import java.io.IOException;
import java.util.List;

import com.shopping.Ecommerce.services.MyUserDetailsService;
import com.shopping.Ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import com.shopping.Ecommerce.services.MerchantServiceImpl;
import com.shopping.Ecommerce.services.ProductServiceImpl;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;


@RestController
public class MerchantController {
	
	@Autowired                              
	private MerchantServiceImpl merchantService;
	
	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("/get_merchants")
	public List<Merchant> getMerchants() {
		return this.merchantService.getMerchants();
	}
	
	@PostMapping("/add_merchant")
	public Merchant addMerchant(@RequestBody Merchant merchant) {
		return this.merchantService.addMerchant(merchant);
	}	
	
	@GetMapping("/find_merchant/{merchant_id}")
	public Merchant findMerchant(@PathVariable String merchant_id) {
		return this.merchantService.findMerchant(Long.parseLong(merchant_id));
	} 
	
	@PutMapping("/update_merchant")
	public Merchant updateMerchant(@RequestBody Merchant merchant) {
		return this.merchantService.updateMerchant(merchant);
	}
	
	@DeleteMapping("/delete_merchant/{merchant_id}") 
	 public ResponseEntity<HttpStatus> deleteMerchant(@PathVariable String merchant_id){
		 try {
			 this.merchantService.deleteMerchant(Long.parseLong(merchant_id));
			 return new ResponseEntity<>(HttpStatus.OK);
		 }catch (Exception e ) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	
	@PostMapping("/add_product")
	public Product addProduct(@RequestBody Product product) {
		return this.productService.addProduct(product);
	}
	
	/*
	 * @PostMapping("/add_merchantAndProduct") public Merchant
	 * onboardMerchant(@RequestBody Merchant merchant){ return
	 * MerchantServiceImpl.save(merchant); }
	 */

	@RequestMapping(value = "/sendemail")
	public String sendEmail() {
		return "Email sent successfully";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e){
			throw new Exception("Incorrect user name or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@GetMapping("/export_users")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		String fileName = "users.csv";

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; fileName=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<Merchant> merchantList = merchantService.getMerchants();

		ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = {"Merchant-ID", "Merchant-Account-Number", "Merchant-Name", "Merchant-Type"};
		String[] nameMapping = {"merchant_id", "merchant_accountNumber", "merchant_name", "merchant_type"};

		csvBeanWriter.writeHeader(csvHeader);

		for (Merchant merchant : merchantList){
			csvBeanWriter.write(merchant, nameMapping);
		}

		csvBeanWriter.close();
	}



}
