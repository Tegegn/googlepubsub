package com.techli.googlepubsub;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.cloud.gcp.pubsub.support.GcpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.google.cloud.pubsub.v1.AckReplyConsumer;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class GooglepubsubApplication {
	Logger logger = LoggerFactory.getLogger(GooglepubsubApplication.class);

	public static void main(String[] args) throws IOException{
		SpringApplication.run(GooglepubsubApplication.class, args);
	}
	
	  @Bean
	  public PubSubInboundChannelAdapter messageChannelAdapter(
	      @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
	      PubSubOperations pubSubTemplate) {
	    PubSubInboundChannelAdapter adapter =
	        new PubSubInboundChannelAdapter(pubSubTemplate, "testSubscription");
	    adapter.setOutputChannel(inputChannel);
	    adapter.setAckMode(AckMode.MANUAL);

	    return adapter;
	  }
	  
	  @Bean
	  public MessageChannel pubsubInputChannel() {
	    return new DirectChannel();
	  }
	  
	  @Bean
	  @ServiceActivator(inputChannel = "pubsubInputChannel")
	  public MessageHandler messageReceiver() {
	    return message -> {
	    	logger.info("Message arrived! Payload: " + message.getPayload());
	      AckReplyConsumer consumer =
	          (AckReplyConsumer) message.getHeaders().get(GcpHeaders.ACKNOWLEDGEMENT);
	      consumer.ack();
	    };
	  }
	  @Bean
	  @ServiceActivator(inputChannel = "pubsubOutputChannel")
	  public MessageHandler messageSender(PubSubOperations pubsubTemplate) {
	    return new PubSubMessageHandler(pubsubTemplate, "testTopic");
	  }
	  
	  @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
	  public interface PubsubOutboundGateway {

	    void sendToPubsub(String text);
	  }
}
