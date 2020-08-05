package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Imagenes;
import application.View.Producto;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class HistorialDao {
	
private String mensaje = "";
	
	public String AgregarHistorial(Connection con, Imagenes imagenes) {
		try {
			String sql = "INSERT INTO Historial(idH,idP_Producto,fecha_Foto,imagen) "
							+"VALUES (?,?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, imagenes.getIdH());
			start.setString(2, imagenes.getIdP());
			start.setString(3, imagenes.getFecha_Foto());
			start.setString(4, imagenes.getImagen());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	
	
	public ObservableList<Imagenes> listarTipo(ObservableList<Imagenes> table) {
		table.clear();
		try { 
	       	   
	       	Statement start = Conexion.getConexion().createStatement();
	       	ResultSet rs = start.executeQuery("SELECT h.idH,p.idP,h.fecha_Foto,h.imagen FROM Historial h INNER JOIN Producto p " + 
	       			"ON p.idP = h.idP_Producto;");
	           
	           
	           while (rs.next()) {
	                           
	                           table.add(new Imagenes(rs.getString("IdH"), rs.getString("IdP"),
	                           		rs.getString("fecha_Foto"), rs.getString("Imagen")));

	                           
	        } 
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return table;
		
	}

}
