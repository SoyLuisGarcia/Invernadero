package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Producto;
import application.View.Riego;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class RiegoDao {
	
private String mensaje = "";
	
	public String AgregarRiego(Connection con, Riego riego) {
		try {
			String sql = "Insert into Riego(idR, fecha_riego, idP_Producto) "
							+"VALUES (?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, riego.getIdR());
			start.setString(2, riego.getFecha_riego());
			start.setString(3, riego.getIdP_Producto());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public ObservableList<Riego> listarTipo(ObservableList<Riego> tableR) {
		tableR.clear();
		try {
			String sql = "SELECT r.idR, p.nombreP, fecha_riego FROM Riego r\r\n" + 
					"INNER JOIN Producto p\r\n" + 
					"on p.idP = r.idP_Producto";
			Statement star = Conexion.getConexion().createStatement();
			ResultSet rs = star.executeQuery(sql);
					
			while(rs.next()) {
				tableR.add(new Riego(rs.getNString("IdR"), rs.getString("nombreP"), rs.getString("fecha_riego")));
			}
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return tableR;
		
	}

}
