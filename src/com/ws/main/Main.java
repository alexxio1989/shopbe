package com.ws.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.models.Magazino;

public class Main {

	public static void main(String[] args) {
		Magazino utente = new Magazino();
		ObjectMapper mapper = new ObjectMapper();
		try {
		  String json = mapper.writeValueAsString(utente);
		  System.out.println(json);
		  //System.out.println(json);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}

	}

}
