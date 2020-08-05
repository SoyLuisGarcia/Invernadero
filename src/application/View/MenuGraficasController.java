package application.View;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuGraficasController {
	
	Main main;
	
	@FXML private Button atras;
	@FXML private Button tipoG;
	@FXML private Button proG;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void abrirMenu() {
		main.ventanaMenu();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaTipo() {
		main.ventanaGraficaTipo();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaProductos() {
		main.ventanaGraficaProducto();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaRiegoTipo() {
		main.ventanaGraficaRiegoTipo();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaRiegoDia() {
		main.ventanaGraficaRiegoDia();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaRiegoProDia() {
		main.ventanaGraficaRiegoProDia();
		main.getStageGraficasM().close();
	}
	
	@FXML
	public void graficaFoto() {
		main.ventanaGraficaFoto();
		main.getStageGraficasM().close();
	}
}
