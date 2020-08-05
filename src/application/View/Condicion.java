package application.View;

import java.io.Serializable;

public class Condicion implements Serializable {
	
	private String idC;
	private String condicionC;
	private String saludC;
	

	public Condicion(String idC, String condicionC, String saludC) {
		this.idC = idC;
		this.condicionC = condicionC;
		this.saludC = saludC;
	}

	public String getIdC() {
		return idC;
	}
	
	public void setIdC(String idC) {
		this.idC = idC;
	}
	
	public String getCondicionC() {
		return condicionC;
	}
	
	public void setCondicionC(String codicionC) {
		this.condicionC = codicionC;
	}
	
	public String getSaludC() {
		return saludC;
	}
	
	public void setSaludC(String saludC) {
		this.saludC = saludC;
	}
}
