package edu.mum.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestClientController {

	
	@GetMapping("/restClient")
	public String restClient() {	
		return "restClient";
	}
	
	@PostMapping("/processResquest")
	public String processResquest(@ModelAttribute("requestData") RequestData requestData, BindingResult result) {
		RestTemplate restTemplate = new RestTemplate();
		
		Response response = restTemplate.postForObject(requestData.getUrl(), requestData.getBody(), Response.class);
		System.out.println("response : " + response);
		
		return "restClient";
	}
	
	
}
