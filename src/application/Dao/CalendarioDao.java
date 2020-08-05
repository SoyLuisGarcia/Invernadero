package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Calendario;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class CalendarioDao {
	
	private String mensaje = "";
	
	public String AgregarCalendario(Connection con, Calendario calendario) {
		try {
			String sql = "Insert into Calendario(idC, idP_Producto, diaC) "
							+"VALUES (?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, calendario.getIdC());
			start.setString(2, calendario.getIdP_Producto());
			start.setString(3, calendario.getDiaC());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	
	
	public String EliminarCalendario(Connection con, String id) {
		try {
			String sql = "DELETE FROM Calendario where idC = ?";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, id);
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	
	public ObservableList<Calendario> listarTipo(ObservableList<Calendario> tableCal) {
		try {
			tableCal.clear();
			String sql = "SELECT c.idC, p.nombreP, diaC FROM Calendario c\r\n" + 
					"INNER JOIN Producto p\r\n" + 
					"on p.idP = c.idP_Producto";
			Statement star = Conexion.getConexion().createStatement();
			ResultSet rs = star.executeQuery(sql);
			
			while(rs.next()) {
				tableCal.add(new Calendario(rs.getString("IdC"),rs.getString("NombreP"),rs.getString("diaC")));
			}
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return tableCal;
		
	}

}
