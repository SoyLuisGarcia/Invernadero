package application.View;

import java.sql.ResultSet;
import java.sql.Statement;

import application.Conexion;
import application.Main;
import application.Dao.GraficasDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class TablaRiegoTipoController {
	GraficasDao daoG = new GraficasDao();

	Main main;
	
	@FXML private Button generarG;
	@FXML private Button atras;
	@FXML private BarChart<String, Integer> grafica;
	@FXML private CategoryAxis xAxis;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	ObservableList<String> leyenda;
	int valor;
	
	@FXML
	private void generar(ActionEvent event) throws Exception {
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
		
		xAxis.setCategories(daoG.listarRiegoTipo1(leyenda));
		grafica.getData().addAll(daoG.listarRiegoTipo2(leyenda));
		
		generarG.setDisable(true);
	}
	
	@FXML
	public void atras() {
		main.ventanaMenuGraficasM();
		main.getStageGraficaRiegoTipo().close();;
	}
}
