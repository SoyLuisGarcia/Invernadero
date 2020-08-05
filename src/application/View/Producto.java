package application.View;

import java.io.Serializable;

public class Producto implements Serializable {
	
	private String idP;
	private String nombreP;
	private String idT_Tipo;
	private String fecha_ingreso;
	private String idC_Condicion;
	
	public Producto(String idP, String nombreP, String idT_Tipo, String fecha_ingreso, String idC_Condicion) {
		this.idP = idP;
		this.nombreP = nombreP;
		this.idT_Tipo = idT_Tipo;
		this.fecha_ingreso = fecha_ingreso;
		this.idC_Condicion = idC_Condicion;
	}
	
	public String getIdP() {
		return idP;
	}
	
	public void setIdP(String idP) {
		this.idP = idP;
	}
	
	public String getNombreP() {
		return nombreP;
	}
	
	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}
	
	public String getIdT_Tipo() {
		return idT_Tipo;
	}
	
	public void setId_Tipo(String idT_Tipo) {
		this.idT_Tipo = idT_Tipo;
	}
	
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	
	public String getIdC_Condicion() {
		return idC_Condicion;
	}
	
	public void setIdC_Condicion(String idC_Condicion) {
		this.idC_Condicion = idC_Condicion;
	}
}
