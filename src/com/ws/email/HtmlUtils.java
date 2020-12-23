package com.ws.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import com.ws.enums.StatusAcquisto.EnumStatusAcquisto;
import com.ws.models.Acquisto;

public class HtmlUtils {
	
	
	public static String getHtmlEmailAggiornaAcquisto(Acquisto acquisto) {
		
		String template = "src/main/resources/email-aggiornamento-acquisto.html";
		String content = bufferHtml(template);
		
		Map<String, String> values = new HashMap<>();
		values.put("0", acquisto.getUtente().getNome());
		values.put("1", acquisto.getCodiceAquisto().toUpperCase());
		values.put("2", acquisto.getStatus().getDescrizione());
		if("PAC".equalsIgnoreCase(acquisto.getModalitaPagamento().getCodice()) || 
				"CC".equalsIgnoreCase(acquisto.getModalitaPagamento().getCodice())) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
			
			String consegnatoHtml = "<div class=\"row mt-4\">\n"
					+ "			<div class=\"col-12\">\n"
					+ "				<h6 style=\" font-family: sans-serif;\">Consegnato il: "+ df.format(acquisto.getDataCosegnaPrevista()) +"</h6>\n"
					+ "			</div>\n"
					+ "		</div>";
			
			String indirizzoConsegnaHtml = "<div class=\"row mt-4\">\n"
					+ "			<div class=\"col-12\">\n"
					+ "				<h6 style=\" font-family: sans-serif;\">Al seguente Indirizzo: "+ acquisto.getUtente().getRecapito().getCitta() + " , " + acquisto.getUtente().getRecapito().getIndirizzo() +"</h6>\n"
					+ "			</div>\n"
					+ "		</div>";
	
			String dataConsegnaHtml = "<div class=\"row mt-4\">\n"
					+ "			<div class=\"col-12\">\n"
					+ "				<h6 style=\" font-family: sans-serif;\">Data consegna prevista: "+ df.format(acquisto.getDataCosegnaPrevista()) +"</h6>\n"
					+ "			</div>\n"
					+ "		</div>";
			
			if(EnumStatusAcquisto.CONSEGNATO.getCode().equalsIgnoreCase(acquisto.getStatus().getDescrizione())) {
				values.put("3", consegnatoHtml);
				values.put("4", indirizzoConsegnaHtml);
			} else {
				values.put("3", dataConsegnaHtml);
				values.put("4", "");
				
			}
		}
		
		
		String message = StringSubstitutor.replace(content, values, "{", "}");
		return message;
	}
	

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
