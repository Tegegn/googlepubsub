package com.techli.googlepubsub.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DBResponse {
	
	private int id;
	private String notify;
	private HttpStatus status;

}
