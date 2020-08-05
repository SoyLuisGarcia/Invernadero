package application.View;

public class Imagenes {

	private String idH;
	private String idP;
	private String fecha_Foto;
	private String imagen;
	
	public Imagenes(String idH, String idP_Producto, String fecha_Foto, String imagen) {
		this.idH = idH;
		this.idP = idP_Producto;
		this.fecha_Foto = fecha_Foto;
		this.imagen = imagen;
	}
	
	public String getIdH() {
		return idH;
	}
	
	public void setIdH(String idH) {
		this.idH = idH;
	}
	
	public String getIdP() {
		return idP;
	}
	
	public void setIdP(String idP_Producto) {
		this.idP = idP_Producto;
	}
	
	public String getFecha_Foto() {
		return fecha_Foto;
	}
	
	public void setFecha_Foto(String fecha_Foto) {
		this.fecha_Foto = fecha_Foto;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
