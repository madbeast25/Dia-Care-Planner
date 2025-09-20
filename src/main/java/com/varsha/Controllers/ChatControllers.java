package com.varsha.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.varsha.Services.ChatServices;
import com.varshaDTOs.BmiReq;
import com.varshaDTOs.ChatReq;

@RestController
@RequestMapping("/chatbot")
public class ChatControllers {
	
	@Autowired
	private ChatServices service;
    
	@PostMapping("/getDietPlan")
	public ResponseEntity<String> getDietPlan(@RequestBody ChatReq req){
		String res=service.generateResponse(req);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PostMapping("/getBmiAdvice/{bmi}")
	public ResponseEntity<String> getAdvice(@PathVariable double bmi) throws  JsonProcessingException{
		
		String response=service.getAdvice(bmi);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
