package application.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.View.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class GraficasDao {
	
	public ObservableList<String> listarProducto1(ObservableList<String> leyenda) throws Exception {
		String sql = "select nombreP, count(*) from Producto \r\n" + 
				"group by nombreP;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarProducto2(ObservableList<String> leyenda) throws Exception {
		String sql = "select nombreP, count(*) from Producto \r\n" + 
				"group by nombreP;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}
	
	public ObservableList<String> listarTipo1(ObservableList<String> leyenda) throws Exception {
		String sql = "select tipoT, count(*) from Tipo \r\n" + 
				"group by tipoT;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarTipo2(ObservableList<String> leyenda) throws Exception {
		String sql = "select tipoT, count(*) from Tipo \r\n" + 
				"group by tipoT;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}
	
	public ObservableList<String> listarfoto1(ObservableList<String> leyenda) throws Exception {
		String sql = "SELECT p.nombreP, COUNT(*) FROM Historial h\r\n" + 
				"INNER JOIN Producto p\r\n" + 
				"on p.idP = h.idP_Producto\r\n" + 
				"group by p.nombreP";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarfoto2(ObservableList<String> leyenda) throws Exception {
		String sql = "SELECT p.nombreP, COUNT(*) FROM Historial h\r\n" + 
				"INNER JOIN Producto p\r\n" + 
				"on p.idP = h.idP_Producto\r\n" + 
				"group by p.nombreP";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}
	
	public ObservableList<String> listarRiegoDia1(ObservableList<String> leyenda) throws Exception {
		String sql = "select fecha_riego, count(*) from Riego\r\n" + 
				"group by fecha_riego;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarRiegoDia2(ObservableList<String> leyenda) throws Exception {
		String sql = "select fecha_riego, count(*) from Riego\r\n" + 
				"group by fecha_riego;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}
	
	public ObservableList<String> listarRiegoPorDia1(ObservableList<String> leyenda) throws Exception {
		String sql = "select diaC, count(*) from Calendario\r\n" + 
				"group by diaC;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarRiegoPorDia2(ObservableList<String> leyenda) throws Exception {
		String sql = "select diaC, count(*) from Calendario\r\n" + 
				"group by diaC;";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}
	
	public ObservableList<String> listarRiegoTipo1(ObservableList<String> leyenda) throws Exception {
		String sql = "SELECT t.tipoT, COUNT(*) FROM Riego r\r\n" + 
				"INNER JOIN Producto p\r\n" + 
				"on p.idP = r.idP_Producto\r\n" + 
				"INNER JOIN Tipo t\r\n" + 
				"on t.idT = p.idT_Tipo\r\n" + 
				"group by t.tipoT";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return leyenda;
		
	}
	
	public XYChart.Series<String, Integer> listarRiegoTipo2(ObservableList<String> leyenda) throws Exception {
		String sql = "SELECT t.tipoT, COUNT(*) FROM Riego r\r\n" + 
				"INNER JOIN Producto p\r\n" + 
				"on p.idP = r.idP_Producto\r\n" + 
				"INNER JOIN Tipo t\r\n" + 
				"on t.idT = p.idT_Tipo\r\n" + 
				"group by t.tipoT";
		
		Statement st = Conexion.getConexion().createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();
		
		leyenda = FXCollections.observableArrayList();
		while(rs.next()) {
			leyenda.add(""+rs.getString(1));
			dataSeries.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));
		}
		return dataSeries;
		
	}

}
