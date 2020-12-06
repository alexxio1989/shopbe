package com.ws.repository.repoenums;


public class Metodi {
	
	public enum EnumMetodi{
		
		SAVE("SAVE"),
		SAVE_ERROR("SAVE_ERROR"),
		DELETE("DELETE"),
		DELETE_ERROR("DELETE_ERROR"),
		UPDATE("UPDATE"),
		UPDATE_ERROR("UPDATE_ERROR"),
		GET("GET"),
		GET_ERROR("GET_ERROR"),
		LOGIN("LOGIN"),
		LOGIN_ERROR("LOGIN_ERROR")
		
		
		;
		
		private EnumMetodi(String code ){
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


