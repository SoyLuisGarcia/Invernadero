package application.View;


import application.Conexion2;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BaseController {
	
	Main main;
	
	@FXML private Button sql;
	@FXML private Button mysql;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void abrirLogin() {
		
		Conexion2 con = new Conexion2();
		main.ventanaInicio();
	}
}
