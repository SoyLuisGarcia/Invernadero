package application.View;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
	
	Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML private Button inventario;
	@FXML private Button calendario;
	@FXML private Button riego;
	@FXML private Button historial;
	@FXML private Button grafica;
	@FXML private Button video;
	@FXML private Button atras;
	
	@FXML
	public void abrirVideo() {
		main.ventanaVideo();
		main.getStageMenu().close();
	}
	
	@FXML
	public void abrirInvenatario() {
		main.ventanaInventario();
		main.getStageMenu().close();
	}
	
	@FXML
	public void abrirCalendario() {
		main.ventanaCalendario();
		main.getStageMenu().close();
	}
	
	@FXML
	public void abrirRiego() {
		main.ventanaRiego();
		main.getStageMenu().close();
	}
	
	@FXML
	public void abrirHistorial() {
		main.ventanaHistorial();
		main.getStageMenu().close();
	}
	
	@FXML
	public void abrirMenuGraficas() {
		main.ventanaMenuGraficasM();
		main.getStageMenu().close();
	}
	
	@FXML
	public void desconectar() {
		main.getStageMenu().close();
	}
}
