package application.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.Main;
import application.Dao.CalendarioDao;
import application.Dao.ProductoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class CalendarioController {
	ProductoDao daoP = new ProductoDao();
	CalendarioDao daoC = new CalendarioDao();
	
	Main main;
	int cont =0;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML private Pane tablaProd;
	@FXML private Pane tablaCal;
	
	@FXML private Pane principal;
	@FXML private Button agregar;
	@FXML private Button eliminar;
	@FXML private Button buscar;
	@FXML private Button atras;
	@FXML private Button cancelar;
	@FXML private Button verC;
	@FXML private Button salirC;
	@FXML private TextField idBus;
	
	@FXML private Pane paneAgregar;
	@FXML private Button agregarCal;
	@FXML private Button atrasCal;
	@FXML private TextField idCal;
	@FXML private TextField nombreCal;
	
	@FXML private Pane paneEliminar;
	@FXML private Button agregarEli;
	@FXML private Button atrasEli;
	@FXML private TextField idEli;
	
	
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

	/*Tabla Calendario*/
	@FXML private TableView<Calendario> tablaCalendario;
	@FXML private TableColumn<Calendario,String> idC;
	@FXML private TableColumn<Calendario,String> idP_Producto;
	@FXML private TableColumn<Calendario,String> diaC;
	ObservableList<Calendario> tableCal = FXCollections.observableArrayList();
	
	@FXML
	public void cargarTablaCalendario() {
		
			
			idC.setCellValueFactory(new PropertyValueFactory<>("idC"));
			idP_Producto.setCellValueFactory(new PropertyValueFactory<>("idP_Producto"));
			diaC.setCellValueFactory(new PropertyValueFactory<>("diaC"));
			tablaCalendario.setItems(daoC.listarTipo(tableCal));
			
		
	}
	
	@FXML
	private void acciones() {
		
		agregar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneAgregar.setVisible(true);
						
						idCal.clear();
						nombreCal.clear();
					}
				});
		
		eliminar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneEliminar.setVisible(true);
					}
				});
		
		cancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						buscar.setDisable(false);
						idBus.setDisable(false);
						agregar.setDisable(true);
						eliminar.setDisable(true);
						cancelar.setDisable(true);
						idBus.clear();
					}
				});
		
		atrasCal.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneAgregar.setVisible(false);
						buscar.setDisable(true);
						idBus.setDisable(true);
					}
				});
		
		atrasEli.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneEliminar.setVisible(false);
						buscar.setDisable(true);
						idBus.setDisable(true);
					}
				});
		
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
								ResultSet resul = star.executeQuery("SELECT p.idP, c.idC FROM Calendario c, producto p");
								
								while(resul.next()) {
									if(resul.getString(1).equals(ID)) {
										idBus.setText(resul.getString(1));
										buscar.setDisable(true);
										idBus.setDisable(true);
										cancelar.setDisable(false);
										agregar.setDisable(false);
										
									}
									else if(resul.getString(2).equals(ID)) {
										idBus.setText(resul.getString(2));
										buscar.setDisable(true);
										idBus.setDisable(true);
										cancelar.setDisable(false);
										eliminar.setDisable(false);
										
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
		
		verC.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						tablaProd.setVisible(false);
						tablaCal.setVisible(true);
						cargarTablaCalendario();
						verC.setDisable(true);
						salirC.setDisable(false);
					}
				});
		
		salirC.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						tablaProd.setVisible(true);
						tablaCal.setVisible(false);
						cargarTablaProducto();
						verC.setDisable(false);
						salirC.setDisable(true);
					}
				});
		
		agregarCal.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idCal.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else if(nombreCal.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene el dia");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								ResultSet resul = star.executeQuery("Select count(idC) from Calendario where idC = '"+idCal.getText()+"'");
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
										
										Calendario calendario = new Calendario(idCal.getText(),idBus.getText(),nombreCal.getText());
										Connection con = Conexion.getConexion();
										daoC.AgregarCalendario(con, calendario);
										
										paneAgregar.setVisible(false);
										buscar.setDisable(false);
										idBus.setDisable(false);
										cancelar.setDisable(true);
										agregar.setDisable(true);
										eliminar.setDisable(true);
										cargarTablaCalendario();
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
		
		agregarEli.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(!idEli.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								if(opcion == ButtonType.OK) {
									Connection con = Conexion.getConexion();
									daoC.EliminarCalendario(con, idEli.getText());
									idBus.clear();
									idEli.clear();
									cargarTablaCalendario();
									paneEliminar.setVisible(false);
									buscar.setDisable(false);
									idBus.setDisable(false);
									cancelar.setDisable(true);
									agregar.setDisable(true);
									eliminar.setDisable(true);
								}
							});
						}
					}
				});
		
		
	}
	
	@FXML
	private void salirMenu() {
		main.ventanaMenu();
		main.getStageCalendario().close();
	}
	
	@FXML
	private void initialize() {
		tablaProd.setVisible(true);
		cargarTablaProducto();
		principal.setVisible(true);
		tablaCal.setVisible(false);
	}
}
