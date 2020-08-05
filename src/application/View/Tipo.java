package application.View;

import java.io.Serializable;

public class Tipo implements Serializable {
	
	private String idT;
	private String tipoT;
	private String utilidadT;
	

	
	public Tipo(String idT, String tipoT, String utilidadT) {
		this.idT = idT;
		this.tipoT = tipoT;
		this.utilidadT = utilidadT;
	}

	public String getIdT() {
		return idT;
	}
	
	public void setIdT(String idT) {
		this.idT = idT;
	}
	
	public String getTipoT() {
		return tipoT;
	}
	
	public void setTipoT(String tipoT) {
		this.tipoT = tipoT;
	}
	
	public String getUtilidadT() {
		return utilidadT;
	}
	
	public void setUtilidadT(String utilidadT) {
		this.utilidadT = utilidadT;
	}
}
