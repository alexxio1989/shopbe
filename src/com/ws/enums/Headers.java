package com.ws.enums;

public class Headers {
	
	public enum EnumHeaders{
		
		JWT_TOKEN("JWT_TOKEN"),
		TYPE_USER("TYPE_USER"),
		AZIENDA("A"),
		UTENTE("U"),
		;
		
		private EnumHeaders(String code ){
			setCode(code);
		}
		
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
}
