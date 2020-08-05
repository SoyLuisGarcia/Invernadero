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

public class TablaProductoController {
	GraficasDao daoG = new GraficasDao();
	
	Main main;
	
	@FXML private Button generarP;
	@FXML private Button atrasP;
	@FXML private BarChart <String, Integer> graficaP;
	@FXML private CategoryAxis xAxis;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	ObservableList<String> leyenda;
	int valor;
	
	@FXML
	private void generar(ActionEvent event) throws Exception {
		
		xAxis.setCategories(daoG.listarProducto1(leyenda));
		graficaP.getData().addAll(daoG.listarProducto2(leyenda));
		
		generarP.setDisable(true);
	}
	
	@FXML
	public void atras() {
		main.ventanaMenuGraficasM();
		main.getStageGraficaProducto().close();
	}
}
