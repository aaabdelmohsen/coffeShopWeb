package edu.mum.coffee.controller;

import static org.mockito.Matchers.booleanThat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestClientController {

	@GetMapping("/restClient")
	public String restClient() {
		return "restClient";
	}

	// @ModelAttribute("requestData") RequestData requestData, BindingResult result
	@PostMapping("/restClient")
	public String restClient(@ModelAttribute("requestData") RequestData requestData, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		// String url = "https://api.github.com/users/mralexgray/repos";
		String response = "";
		if (requestData.getBody() != null && !requestData.getBody().equals(""))
			response = restTemplate.postForObject(requestData.getUrl(), requestData.getBody(), String.class);
		else
			response = restTemplate.getForObject(requestData.getUrl(), String.class);
		System.out.println("response : " + response);

		model.addAttribute("response", response);

		return "restClient";
	}

}
