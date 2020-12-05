package com.ws.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.models.SubDominio;

public class Main {

	public static void main(String[] args) {
		SubDominio utente = new SubDominio();
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(utente);
		  System.out.println("ResultingJSONstring = " + json);
		  //System.out.println(json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}

	}

}
