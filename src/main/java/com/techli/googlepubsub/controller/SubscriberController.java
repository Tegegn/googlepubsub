package com.techli.googlepubsub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/html")
public class SubscriberController {
	
	@RequestMapping("/pullMessage")
	public String pullMessagefromTopic() {
		return "Message pulled success";
	}
	

}
