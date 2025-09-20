package com.varsha.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varshaDTOs.BmiReq;
import com.varshaDTOs.ChatReq;

@Service
public class ChatServices {
	
	@Autowired
	private WebClient client;
	
	@Value("${GEMINI_URL}")
	String url;
	@Value("${GEMINI_API_KEY}")
	String key;
	
	public String generateResponse(ChatReq req) {
		String prompt=buildPrompt(req);
		
		Map<String,Object[]> body=Map.of(
				   "contents",new Object[] {
						   Map.of("parts",new Object[] {
								   Map.of("text",prompt)
						   })
				   }
				);
		
		String response=client.post()
				        .uri(url+key)
				        .bodyValue(body)
				        .header("content-Type","application/json")
				        .retrieve()
				        .bodyToMono(String.class)
				        .block();
		String extracted=extractContext(response);
				        
		
		
		
		return extracted;
	}
	
	public String extractContext(String response) {
		try {
			
			ObjectMapper mapper=new ObjectMapper();
			JsonNode node=mapper.readTree(response);
			
			String res=node.path("candidates")
					   .get(0)
					   .path("content")
					   .path("parts")
					   .get(0)
					   .path("text")
					   .asText();
			
			return res;
			
		}catch(Exception ex) {
			return ex.getMessage();
		}
		
	}

	
	public String buildPrompt(ChatReq req) {
		
		StringBuilder prompt=new StringBuilder();
		
		prompt.append("Can you create a diet plan for a diabetes patient? I will provide the patient's details, including height, weight, sugar levels before and after meals, cholesterol level, and gender. Based on this information, generate a personalized diet plan.");
		
		prompt.append("height: "+req.getHeight());
		prompt.append("weight: "+req.getWeight());
		prompt.append("sex: "+req.getSex());
		prompt.append("Suger level before eating food: "+req.getPre_meals_glucose());
		prompt.append("Suger level after eating foot: "+req.getPost_meals_glucose());
		prompt.append("Cholesterol: "+req.getCholestrol());
		
		return prompt.toString();
	}
	
	public String getAdvice(double bmi) throws JsonMappingException, JsonProcessingException {
		String prompt=getAdvicePrompt(bmi);
		Map<String,Object[]> map=Map.of(
		     "contents",new Object[] {
		    		 Map.of(
		    				 "parts",new Object[] {
		    						 Map.of("text",prompt)
		    				 }
		    		 )
		     }
		);
		
		String response=client.post()
				              .uri(url+key)
				              .bodyValue(map)
				              .header("content-Type", "application/json")
				              .retrieve()
				              .bodyToMono(String.class)
				              .block();
		String extractedAdvice=extractAdvice(response);
		
		return extractedAdvice;
		
	}
	
	public String getAdvicePrompt(double bmi) {
		StringBuilder sb=new StringBuilder();
		
		sb.append("I will provide my BMI value. Please give me a short message about it, such as whether it’s healthy or not.");
		sb.append("Bmi value: "+bmi);
		
		return sb.toString();
	}
	
	public String extractAdvice(String req) {
		  
		try {
			ObjectMapper mapper=new ObjectMapper();
			JsonNode node=mapper.readTree(req);
			
			String response=node.path("candidates")
					            .get(0)
					            .path("content")
					            .path("parts")
					            .get(0)
					            .path("text")
					            .asText();
			
			return response;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			
			return null;
		}
		
		
	}
	
	
}
