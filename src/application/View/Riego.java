package application.View;

public class Riego {
	
	private String idR;
	private String fecha_riego;
	private String idP_Producto;
	
	public Riego(String idR,String fecha_riego, String idP_Producto) {
		this.idR = idR;
		this.fecha_riego = fecha_riego;
		this.idP_Producto = idP_Producto;
	}
	
	public String getIdR() {
		return idR;
	}
	
	public void setIdR(String idR) {
		this.idR = idR;
	}
	
	public String getFecha_riego() {
		return fecha_riego;
	}
	
	public void setFecha_riego(String fecha_riego) {
		this.fecha_riego = fecha_riego;
	}
	
	public String getIdP_Producto() {
		return idP_Producto;
	}
	
	public void setIdP_producto(String idP_Producto) {
		this.idP_Producto = idP_Producto;
	}
}
