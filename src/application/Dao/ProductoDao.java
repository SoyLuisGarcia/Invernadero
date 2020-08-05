package application.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Producto;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class ProductoDao {
	
private String mensaje = "";
	
	public String AgregarProducto(Connection con, Producto producto) {
		try {
			String sql = "Insert into Producto(idP,nombreP,idT_Tipo,fecha_ingreso,idC_Condicion) "
							+"VALUES (?,?,?,?,?);";
			PreparedStatement start = con.prepareStatement(sql);
			start.setString(1, producto.getIdP());
			start.setString(2, producto.getNombreP());
			start.setString(3, producto.getIdT_Tipo());
			start.setString(4, producto.getFecha_ingreso());
			start.setString(5, producto.getIdC_Condicion());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String ModificarProducto(Connection con, Producto producto) {
		try {
			String sql = "UPDATE Producto set nombreP = ?, idT_Tipo = ?, idC_Condicion = ? where idP = ?";
			PreparedStatement start = con.prepareStatement(sql);
			
			start.setString(1, producto.getNombreP());
			start.setString(2, producto.getIdT_Tipo());
			
			start.setString(3, producto.getIdC_Condicion());
			start.setString(4, producto.getIdP());
			mensaje = "Guardado";
			start.execute();
			start.close();
		} catch (SQLException e) {
			mensaje = e.getLocalizedMessage();
		}
		return mensaje;
	}
	
	public String EliminarProducto(Connection con, String id) {
		try {
			String sql = "DELETE FROM Producto where idP = ?";
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
	
	
	public ObservableList<Producto> listarTipo(ObservableList<Producto> tableP) {
		tableP.clear();
		try {
			String sql = "SELECT idP, nombreP, t.tipoT, fecha_ingreso, c.saludC FROM Producto p\r\n" + 
					"INNER JOIN Condicion c\r\n" + 
					"on c.idC = p.idC_condicion\r\n" + 
					"INNER JOIN Tipo t\r\n" + 
					"on t.idT = p.idT_Tipo";
			Statement star = Conexion.getConexion().createStatement();
			ResultSet rs = star.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println (":V");
				tableP.add(new Producto(rs.getString("IdP"), rs.getString("NombreP"), rs.getString("TipoT"), rs.getString("fecha_ingreso"), rs.getString("SaludC")));
			}
			
		}catch(SQLException e){
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Error al cargar la tabla");
			aler.setContentText(e.getLocalizedMessage());
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
		}
		return tableP;
		
	}

}
