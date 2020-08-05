package application.View;

import java.io.Serializable;

public class Calendario implements Serializable {
	
	private String idC;
	private String idP_Producto;
	private String diaC;
	
	public Calendario(String idC, String idP_Producto, String diaC) {
		this.idC = idC;
		this.idP_Producto = idP_Producto;
		this.diaC = diaC;
	}
	
	public String getIdC() {
		return idC;
	}
	
	public void setIdC(String idC) {
		this.idC = idC;
	}
	
	public String getIdP_Producto() {
		return idP_Producto;
	}
	
	public void setIdP_Producto(String idP_Producto) {
		this.idP_Producto = idP_Producto;
	}
	
	public String getDiaC() {
		return diaC;
	}
	
	public void setDiaC(String diaC) {
		this.diaC = diaC;
	}
}
