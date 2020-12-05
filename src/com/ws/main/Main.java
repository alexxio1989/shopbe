package com.ws.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.models.Utente;

public class Main {

	public static void main(String[] args) {
		Utente utente = new Utente();
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
