package com.techli.googlepubsub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.techli.googlepubsub.GooglepubsubApplication.PubsubOutboundGateway;

@RestController
@RequestMapping("/html")
public class SubscriberController {
	
	  @Autowired
	  private PubsubOutboundGateway messagingGateway;
	
	@RequestMapping(value = "/pullMessage", method = RequestMethod.GET)
	public String pullMessagefromTopic() {
		return "Message pulling successfully";
	}
	
	@PostMapping("/publishMessage")
	  public RedirectView publishMessage(@RequestParam("message") String message) {
	    messagingGateway.sendToPubsub(message);
	    return new RedirectView("/");
	  }

}
