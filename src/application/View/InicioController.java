package application.View;

import application.Conexion2;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class InicioController {
	
	Main main;
	
	@FXML private TextField usuario;
	@FXML private TextField contrasena;
	@FXML private Button inicio;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void abrirMenu() {
		if(usuario.getText().equals("fran") && contrasena.getText().equals("sql123")) {
			Alert aler = new Alert(AlertType.INFORMATION);
			aler.setTitle("Sistema");
			aler.setContentText("Bienvenido");
			aler.initStyle(StageStyle.UTILITY);
			aler.showAndWait();
			
			main.ventanaMenu();
			main.getStageInicio().close();
		}else {
			if(usuario.getText().isEmpty()) {
				Alert aler = new Alert(AlertType.INFORMATION);
				aler.setTitle("Sistema");
				aler.setContentText("Introduzca un Usuario");
				aler.initStyle(StageStyle.UTILITY);
				aler.showAndWait();
			}else if(contrasena.getText().isEmpty()) {
				Alert aler = new Alert(AlertType.INFORMATION);
				aler.setTitle("Sistema");
				aler.setContentText("Introduzca la contrasena");
				aler.initStyle(StageStyle.UTILITY);
				aler.showAndWait();
			}else {
				Alert aler = new Alert(AlertType.INFORMATION);
				aler.setTitle("Sistema");
				aler.setContentText("Usuario o contraseña invalidos");
				aler.initStyle(StageStyle.UTILITY);
				aler.showAndWait();
			}
		}
	}
}
