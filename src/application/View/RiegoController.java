package application.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.Main;
import application.Dao.ProductoDao;
import application.Dao.RiegoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class RiegoController {
	
	ProductoDao daoP = new ProductoDao();
	RiegoDao daoR = new RiegoDao();
	
	@FXML private Button atrasMenu;
	@FXML private Pane principal;
	@FXML private Pane tablaProd;
	@FXML private Pane tablaRie;
	
	@FXML private TextField idBus;
	@FXML private TextField idRie;
	@FXML private DatePicker fechaRie;
	@FXML private Button buscar;
	@FXML private Button verR;
	@FXML private Button salirR;
	@FXML private Button agregarRie;
	@FXML private Button cancelarRie;
	
	
	Main main;
	int cont = 0;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	// tabla producto
		@FXML private TableView<Producto> tablaProducto;
		@FXML private TableColumn<Producto,String> idP;
		@FXML private TableColumn<Producto,String> nombreP;
		@FXML private TableColumn<Producto,String> idT_Tipo;
		@FXML private TableColumn<Producto,String> fecha_ingreso;
		@FXML private TableColumn<Producto,String> idC_Condicion;
		ObservableList<Producto> tableP = FXCollections.observableArrayList();
			
		@FXML
		public void cargarTablaProducto() {
				
				idP.setCellValueFactory(new PropertyValueFactory<>("IdP"));
				nombreP.setCellValueFactory(new PropertyValueFactory<>("NombreP"));
				idT_Tipo.setCellValueFactory(new PropertyValueFactory<>("idT_Tipo"));
				fecha_ingreso.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));
				idC_Condicion.setCellValueFactory(new PropertyValueFactory<>("idC_Condicion"));
				tablaProducto.setItems(daoP.listarTipo(tableP));
						
			
		}
	
		// tabla Riego
				@FXML private TableView<Riego> tablaRiego;
				@FXML private TableColumn<Riego,String> idR;
				@FXML private TableColumn<Riego,String> fecha_riego;
				@FXML private TableColumn<Riego,String> idP_Producto;
				ObservableList<Riego> tableR = FXCollections.observableArrayList();
					
				@FXML
				public void cargarTablaRiego() {
					
								
						idR.setCellValueFactory(new PropertyValueFactory<>("IdR"));
						idP_Producto.setCellValueFactory(new PropertyValueFactory<>("idP_Producto"));
						fecha_riego.setCellValueFactory(new PropertyValueFactory<>("fecha_riego"));
						tablaRiego.setItems(daoR.listarTipo(tableR));
								
					
				}	
	
	@FXML
	private void acciones() {
		
		buscar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idBus.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								String ID = idBus.getText();
								ResultSet resul = star.executeQuery("Select * From Producto");
								
								while(resul.next()) {
									if(resul.getString(1).equals(ID)) {
										idBus.setText(resul.getString(1));
										buscar.setDisable(true);
										idBus.setDisable(true);
										idRie.setDisable(false);
										fechaRie.setDisable(false);
										cancelarRie.setDisable(false);
										agregarRie.setDisable(false);	
									}
								}
							}catch(Exception e) {
								Alert aler = new Alert(AlertType.INFORMATION);
								aler.setTitle("ID invalidad");
								aler.setContentText("Ingrese una ID valida");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}
						}
					}
				});
		
		agregarRie.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idRie.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else if(fechaRie.getValue() == null) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la fecha");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								ResultSet resul = star.executeQuery("Select count(idR) from Riego where idR = '"+idRie.getText()+"'");
								try {
									while(resul.next()) {
										cont = resul.getInt(1);
									}
									
									if(cont >=1) {
										Alert aler = new Alert(AlertType.INFORMATION);
										aler.setTitle("Error de Ingreso");
										aler.setContentText("Ingresa una ID diferente de 0");
										aler.initStyle(StageStyle.UTILITY);
										aler.showAndWait();
									}else {
										
										Riego riego = new Riego(idRie.getText(),String.valueOf(fechaRie.getValue()),idBus.getText());
										Connection con = Conexion.getConexion();
										daoR.AgregarRiego(con, riego);
										idBus.clear();
										idRie.clear();
										fechaRie.setValue(null);
										buscar.setDisable(false);
										idBus.setDisable(false);
										agregarRie.setDisable(true);
										cancelarRie.setDisable(true);
									}
								}catch(SQLException e) {
									Alert aler1 = new Alert(AlertType.ERROR);
									aler1.setContentText(e.getMessage());
									aler1.showAndWait();
									e.printStackTrace();
								}
							}catch(Exception e) {
								Alert aler1 = new Alert(AlertType.ERROR);
								aler1.setContentText(e.getMessage());
								aler1.showAndWait();
								e.printStackTrace();
							}
						}
					}
				});
		
		cancelarRie.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						buscar.setDisable(false);
						idBus.setDisable(false);
						agregarRie.setDisable(true);
						cancelarRie.setDisable(true);
						idRie.clear();
						idBus.clear();
						fechaRie.setTooltip(null);
						idRie.setDisable(true);
						fechaRie.setDisable(true);
					}
				});
		
		verR.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						tablaProd.setVisible(false);
						tablaRie.setVisible(true);
						cargarTablaRiego();
						verR.setDisable(true);
						salirR.setDisable(false);
					}
				});
		
		salirR.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						tablaProd.setVisible(true);
						tablaRie.setVisible(false);
						cargarTablaRiego();
						verR.setDisable(false);
						salirR.setDisable(true);
					}
				});
	}
	
	@FXML
	private void salirMenu() {
		main.ventanaMenu();
		main.getStageRiego().close();
	}
	
	@FXML
	private void initialize() {
		tablaProd.setVisible(true);
		cargarTablaProducto();
		principal.setVisible(true);
		tablaRie.setVisible(false);
		cargarTablaRiego();
	}
}
