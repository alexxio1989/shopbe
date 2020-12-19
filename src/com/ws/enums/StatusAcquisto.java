package com.ws.enums;


public class StatusAcquisto {
	
	public enum EnumStatusAcquisto{
		
		DA_CONFERMARE("DC","DA CONFERMARE"),
		CONFERMATO("C","CONFERMATO"),
		IN_CONSEGNA("IC","IN CONSEGNA"),
		CONSEGNATO("CO","CONSEGNATO")
		
		
		;
		
		private EnumStatusAcquisto(String code , String descrizione){
			setCode(code);
			setDescrizione(descrizione);
		}
		
		private String code;
		private String descrizione;
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getDescrizione() {
			return descrizione;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}
		
		
		
	}

}


