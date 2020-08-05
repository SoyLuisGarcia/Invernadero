package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Tipo;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;


public class TipoDao {
	
	private String mensaje = "";
	
	public String AgregarTipo(Connection con, Tipo tipo) {
		try {
			String sql = "INSERT INTO Tipo(idT,tipoT,utilidadT) "
							+"VALUES (?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, tipo.getIdT());
			start.setString(2, tipo.getTipoT());
			start.setString(3, tipo.getUtilidadT());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String ModificarTipo(Connection con, Tipo tipo) {
		try {
			String sql = "UPDATE Tipo set tipoT = ?, utilidadT = ? where idT = ?;";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, tipo.getTipoT());
			start.setString(2, tipo.getUtilidadT());
			start.setString(3, tipo.getIdT());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String EliminarTipoId(Connection con, String id) {
		try {
			String sql = "DELETE FROM Tipo where idT = ?";
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
	
	public String EliminarTipoTipo(Connection con, String tipo) {
		try {
			String sql = "DELETE FROM Tipo where tipoT = ?";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, tipo);
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public ObservableList<Tipo> listarTipo(ObservableList<Tipo> tableT) {
		tableT.clear();
		try {
			String sql = "Select * From Tipo;";
			Statement star = Conexion.getConexion().createStatement();
			ResultSet rs = star.executeQuery(sql);
			
			while(rs.next()) {
				tableT.add(new Tipo(rs.getString("IdT"), rs.getString("TipoT"), rs.getString("UtilidadT")));
			}
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return tableT;
		
	}

}
