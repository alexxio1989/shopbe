package com.ws.response.enumresponse;

import com.ws.repository.repoenums.Metodi.EnumMetodi;

public class ResponseStatus {
	
	public enum EnumResponseStatus{
		
		SAVE("SALVATAGGIO AVVENUTO CON SUCCESSO" , "SAVE"),
		SAVE_ERROR("SALVATAGGIO IN ERRORE", "SAVE_ERROR"),
		UPDATE("MODIFICA AVVENUTA CON SUCCESSO", "UPDATE"),
		UPDATE_ERROR("MODIFICA IN ERRORE", "UPDATE_ERROR"),
		GET("RECUPERO DATI AVVENUTA CON SUCCESSO", "GET"),
		GET_ERROR("RECUPERO DATI IN ERRORE", "GET_ERROR"),
		DELETE("ELIMINAZIONE AVVENUTA CON SUCCESSO", "DELETE"),
		DELETE_ERROR("ELIMINAZIONE IN ERRORE", "DELETE_ERROR"),
		LOGIN("LOGIN AVVENUTO CON SUCCESSO", "LOGIN"),
		LOGIN_ERROR("LOGIN IN ERRORE", "LOGIN_ERROR")
		
		
		;
		
		private EnumResponseStatus(String desc , String metodoAssociato ){
			setDesc(desc);
			setMetodoAssociato(metodoAssociato);
		}
		
		private String desc;
		private String metodoAssociato;
		
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getMetodoAssociato() {
			return metodoAssociato;
		}
		public void setMetodoAssociato(String metodoAssociato) {
			this.metodoAssociato = metodoAssociato;
		}
		
		public static String getStatus(EnumMetodi enumMetodi) {
			
			for (EnumResponseStatus enumResponseStatus : EnumResponseStatus.values()) {
				if(enumMetodi.getCode().equalsIgnoreCase(enumResponseStatus.getMetodoAssociato())) {
					return enumResponseStatus.getDesc();
				}
				
			}
			return "";
			
		}
	}

}


