package com.techli.googlepubsub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/html")
public class SubscriberController {
	
	@RequestMapping(value = "/pullMessage", method = RequestMethod.GET)
	public String pullMessagefromTopic() {
		return "Message pulling successfully";
	}

}
