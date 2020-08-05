package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Condicion;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class CondicionDao {
	
private String mensaje = "";
	
	public String AgregarCondicion(Connection con, Condicion condicion) {
		try {
			String sql = "INSERT INTO Condicion(idC,condicionC,saludC) "
							+"VALUES (?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, condicion.getIdC());
			start.setString(2, condicion.getCondicionC());
			start.setString(3, condicion.getSaludC());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String ModificarCondicion(Connection con, Condicion condicion) {
		try {
			String sql = "UPDATE Condicion set saludC = ? where idC = ?";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, condicion.getSaludC());
			start.setString(2, condicion.getIdC());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String EliminarCondicionId(Connection con, String id) {
		try {
			String sql = "DELETE FROM Condicion where idC = ?";
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
	
	
	public ObservableList<Condicion> listarTipo(ObservableList<Condicion> tableC) {
		tableC.clear();
		try {
			String sql = "Select * From Condicion;";
			Statement star = Conexion.getConexion().createStatement();
			ResultSet rs = star.executeQuery(sql);
			
			while(rs.next()) {
				tableC.add(new Condicion(rs.getString("IdC"), rs.getString("CondicionC"), rs.getString("SaludC")));
			}
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return tableC;
		
	}

}
