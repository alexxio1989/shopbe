package com.ws.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import com.ws.models.Acquisto;

public class HtmlUtils {

	public static String getHtmlAquirenteAcquisto(Acquisto acquisto) {

		String template = "src/main/resources/email-acquirente-acquisto.html";
		String content = bufferHtml(template);

		Map<String, String> values = new HashMap<>();
		values.put("0", acquisto.getUtente().getNome() + " " + acquisto.getUtente().getCognome());
		values.put("1", acquisto.getCodiceAquisto().toUpperCase());
		values.put("2", acquisto.getStatus().getDescrizione());
		values.put("3", String.valueOf(acquisto.getTotale()));

		String message = StringSubstitutor.replace(content, values, "{", "}");
		return message;
	}
	
	

	public static String getHtmlOwnerAcquisto(Acquisto acquisto , boolean isCristian) {

		String template = "src/main/resources/email-owner-acquisto.html";
		String content = bufferHtml(template);

		Map<String, String> values = new HashMap<>();
		values.put("0", isCristian ? "Cristian" : "Alessio");
		values.put("1", acquisto.getUtente().getNome() + " " + acquisto.getUtente().getCognome());
		values.put("2", acquisto.getCodiceAquisto().toUpperCase());
		values.put("3", acquisto.getModalitaPagamento().getDescrizione());
		values.put("4", String.valueOf(acquisto.getTotale()));

		String message = StringSubstitutor.replace(content, values, "{", "}");
		return message;
	}
	

	public static String bufferHtml(String template) {
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(template));
			String str;
			while ((str = in.readLine()) != null) {
				content += str;
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return content;
	}

}
