package com.ws.enums;

import java.util.ArrayList;
import java.util.List;

import com.ws.models.Status;

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
		
		public static Status getStatus(String code) {
			for (EnumStatusAcquisto enumStatus : EnumStatusAcquisto.values()) {
				if(code.equalsIgnoreCase(enumStatus.getCode())) {
					Status status = new Status();
					status.setCodice(enumStatus.getCode());
					status.setDescrizione(enumStatus.getDescrizione());
					return status;
				}
				
			}
			return null;
		}
		
		public static List<Status> getAll(){
			List<Status> list = new ArrayList<Status>();
			for (EnumStatusAcquisto enumStatus : EnumStatusAcquisto.values()) {
				
					Status status = new Status();
					status.setCodice(enumStatus.getCode());
					status.setDescrizione(enumStatus.getDescrizione());
					list.add(status);
				
			}
			return list;
		}
		
		
		
	}

}


