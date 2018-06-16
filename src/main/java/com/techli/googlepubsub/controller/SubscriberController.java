package com.techli.googlepubsub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.techli.googlepubsub.GooglepubsubApplication.PubsubOutboundGateway;
import com.techli.googlepubsub.dto.SMSMessage;

@RestController
@RequestMapping("/html")
public class SubscriberController {
	
	  @Autowired
	  private PubsubOutboundGateway messageGateway;
	
	@RequestMapping(value = "/pullMessage", method = RequestMethod.GET)
	public String pullMessagefromTopic() {
		return "Message pulling successfully";
		
	}
	
	  @RequestMapping(value = "/publishMessage", method = RequestMethod.POST)
	  public String publishMessage(@RequestBody SMSMessage message) {
		  messageGateway.sendToPubsub(message.toString());
	    return "Message published to pubsub";
	  }

}
