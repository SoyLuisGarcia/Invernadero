package application.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.Conexion2;
import application.Main;
import application.Dao.CondicionDao;
import application.Dao.ProductoDao;
import application.Dao.TipoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

public class InventarioController {
	private ProductoDao daoP = new ProductoDao();
	private TipoDao daoT = new TipoDao();
	private CondicionDao daoC = new CondicionDao();
	
	Main main;
	Conexion2 conexion;
	int cont = 0;
	private ObservableList<String> items = FXCollections.observableArrayList();
	private ObservableList<String> itemsS = FXCollections.observableArrayList();
	
	public void setMain(Main main,Conexion2 con) {
		this.conexion = con;
		this.main = main;
	}
	
	/*Panel Principal*/
	@FXML private Pane principal;
	@FXML private Button productos;
	@FXML private Button tipos;
	@FXML private Button condiciones;
	@FXML private Button volver;
	
	/*Panel MenuProducto*/
	@FXML private Pane paneMenuProducto;
	@FXML private Pane paneProductoNuevo;
	@FXML private Pane paneProductoModificar;
	@FXML private Pane paneProductoEliminar;
	@FXML private Button productoNuevo;
	@FXML private Button productoNuevoAgregar;
	@FXML private Button productoModificar;
	@FXML private Button productoModificarBuscar;
	@FXML private Button productoModificarAgregar;
	@FXML private Button productoEliminar;
	@FXML private Button productoEliminarEli;
	@FXML private Button productoRegresarMenu;
	@FXML private Button productoNuevoCancelar;
	@FXML private Button productoModificarCancelar;
	@FXML private Button productoEliminarCancelar;
	
	/*Datos ProductoNuevo*/
	@FXML private TextField idPAgregar;
	@FXML private TextField nombrePAgregar;
	@FXML private TextField idTPAgregar;
	@FXML private DatePicker fechaPAgregar;
	@FXML private TextField idCAgregar;
	@FXML private TextField idMod;
	@FXML private TextField nombreMod;
	@FXML private TextField idTMod;
	@FXML private TextField idCMod;
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
		
		@FXML
		public void botonesProducto() {
			
			productoNuevoAgregar.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if(idPAgregar.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(nombrePAgregar.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Ingrese nombre");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(idTPAgregar.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID Tipo");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(fechaPAgregar.getValue() == null) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la Fecha");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(idCAgregar.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID Condicion");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else {
								try {
									Statement star = Conexion.getConexion().createStatement();
									ResultSet resul = star.executeQuery("Select count(idP) from Producto where idP = '"+idPAgregar.getText()+"'");
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
											
											Producto producto = new Producto(idPAgregar.getText(),nombrePAgregar.getText(),idTPAgregar.getText(),String.valueOf(fechaPAgregar.getValue()),idCAgregar.getText());
											Connection con = Conexion.getConexion();
											daoP.AgregarProducto(con, producto);
											
											paneMenuProducto.setDisable(false);
											paneProductoNuevo.setVisible(false);
											cargarTablaProducto();
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
			
			productoModificarBuscar.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if(idMod.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else {
								try {
									Statement star = Conexion.getConexion().createStatement();
									String ID = idMod.getText();
									ResultSet resul = star.executeQuery("Select * from Producto");
									
									while(resul.next()) {
											if(resul.getString(1).equals(ID)) {
												idMod.setText(resul.getString(1));
												nombreMod.setText(resul.getString(2));
												idTMod.setText(resul.getString(3));
												idCMod.setText(resul.getString(5));
												idMod.setDisable(true);
												productoModificarBuscar.setDisable(true);
												nombreMod.setDisable(false);
												idTMod.setDisable(false);
												idCMod.setDisable(false);
												productoModificarAgregar.setDisable(false);
											}
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
			
			productoModificarAgregar.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if(nombreMod.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Ingrese nombre");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(idTMod.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID Tipo");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else if(idMod.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.ERROR);
								aler.setTitle("Datos Faltantes");
								aler.setContentText("Rellene la ID Condicion");
								aler.initStyle(StageStyle.UTILITY);
								aler.showAndWait();
							}else {
								Alert aler = new Alert(AlertType.CONFIRMATION);
								aler.showAndWait().ifPresent(opcion ->{
									if(opcion == ButtonType.OK) {
										Producto producto = new Producto(idMod.getText(),nombreMod.getText(),idTMod.getText(),"",idCMod.getText());
										Connection con = Conexion.getConexion();
										daoP.ModificarProducto(con, producto);
										
										paneMenuProducto.setDisable(false);
										paneProductoModificar.setVisible(false);

										cargarTablaProducto();
									}
								});
							}
						}
					});
			
			productoEliminarEli.setOnAction(
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if(!idEli.getText().isEmpty()) {
								Alert aler = new Alert(AlertType.CONFIRMATION);
								aler.showAndWait().ifPresent(opcion ->{
									if(opcion == ButtonType.OK) {
										Connection con = Conexion.getConexion();
										daoP.EliminarProducto(con, idEli.getText());
										
										cargarTablaProducto();
										paneMenuProducto.setDisable(false);
										paneProductoEliminar.setVisible(false);
									}
								});
							}
						}
					});
		}
		
	/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*Tablas pane*/
	@FXML private Pane paneTablaTipo;
	@FXML private Pane paneTablaCondicion;
	@FXML private Pane paneTablaProducto;
	
	/*---------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*Panel MenuTipo*/
	@FXML private Pane paneMenuTipo;
	@FXML private Pane paneTipoNuevo;
	@FXML private Pane paneTipoModificar;
	@FXML private Pane paneTipoEliminar;
	@FXML private Button tipoNuevo;
	@FXML private Button tipoModificar;
	@FXML private Button tipoEliminar;
	@FXML private Button tipoEliminarAceptar;
	@FXML private Button tipoNuevoAgregar;
	@FXML private Button tipoNuevoBuscar;
	@FXML private Button tipoModificarAgregar;
	@FXML private Button tipoRegresarMenu;
	@FXML private Button tipoNuevoCancelar;
	@FXML private Button tipoModificarCancelar;
	@FXML private Button tipoEliminarCancelar;
	
	/*Dato tipoNuevo*/
	@FXML private TextField idNuevo;
	@FXML private ComboBox<String> tipo1;
	@FXML private TextField utilidadNuevo;
	@FXML private TextField idModi;
	@FXML private ComboBox<String> tipo2;
	@FXML private TextField utilidadModi;
	@FXML private TextField idElimi;
	@FXML private ComboBox<String> tipo3;
	
	/*Tabla Tipo*/
	@FXML private TableView<Tipo> tablaTipo;
	@FXML private TableColumn<Tipo, String> idT;
	@FXML private TableColumn<Tipo, String> tipoT;
	@FXML private TableColumn<Tipo, String> utilidadT;
	ObservableList<Tipo> tableT = FXCollections.observableArrayList();
	
	@FXML
	public void cargarTablaTipo() {
			
			
			idT.setCellValueFactory(new PropertyValueFactory<>("IdT"));
			tipoT.setCellValueFactory(new PropertyValueFactory<>("TipoT"));
			utilidadT.setCellValueFactory(new PropertyValueFactory<>("UtilidadT"));
			tablaTipo.setItems(daoT.listarTipo(tableT));
			
		
	}
	
	@FXML
	public void botonesTipo() {
		
		tipoNuevoAgregar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idNuevo.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else if(tipo1.getValue() == null) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Porfavor eliga un tipo");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								ResultSet resul = star.executeQuery("Select count(idT) from Tipo where idT = '"+idNuevo.getText()+"'");
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
										Tipo tipo = new Tipo(idNuevo.getText(),tipo1.getValue(),utilidadNuevo.getText());
										Connection con = Conexion.getConexion();
										daoT.AgregarTipo(con, tipo);
										paneMenuTipo.setDisable(false);
										paneTipoNuevo.setVisible(false);
										cargarTablaTipo();
									}
								}catch(SQLException e) {
									Alert aler1 = new Alert(AlertType.ERROR);
									aler1.setTitle("aler2");
									aler1.setContentText(e.getMessage());
									aler1.showAndWait();
									e.printStackTrace();
								}
							}catch(Exception e) {
								Alert aler1 = new Alert(AlertType.ERROR);
								aler1.setTitle("aler1");
								aler1.setContentText(e.getMessage());
								aler1.showAndWait();
								e.printStackTrace();
							}
							
						}
					}
				});
		
		tipoNuevoBuscar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idModi.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								String ID = idModi.getText();
								ResultSet resul = star.executeQuery("Select * From Tipo");
								
								while(resul.next()) {
									if(resul.getString(1).equals(ID)) {
										idModi.setText(resul.getString(1));
										tipo2.setValue(resul.getString(2));
										utilidadModi.setText(resul.getString(3));
										idModi.setDisable(true);
										tipoNuevoBuscar.setDisable(true);
										tipo2.setDisable(false);
										utilidadModi.setDisable(false);
										tipoModificarAgregar.setDisable(false);
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
		
		tipoModificarAgregar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(tipo2.getValue() == null) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Porfavor eliga un tipo");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								Tipo tipo = new Tipo(idModi.getText(),tipo2.getValue(),utilidadModi.getText());
								Connection con = Conexion.getConexion();
								daoT.ModificarTipo(con, tipo);
								paneMenuTipo.setDisable(false);
								paneTipoModificar.setVisible(false);
								cargarTablaTipo();
							});
						}
					}
				});
		
		tipoEliminarAceptar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(!idElimi.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								if(opcion == ButtonType.OK) {
									Connection con = Conexion.getConexion();
									daoT.EliminarTipoId(con, idElimi.getText());
									paneMenuTipo.setDisable(false);
									paneTipoEliminar.setVisible(false);
									cargarTablaTipo();
								}
							});
						}else if(!tipo3.getValue().isEmpty()) {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								if(opcion == ButtonType.OK) {
									Connection con = Conexion.getConexion();
									daoT.EliminarTipoTipo(con, tipo3.getValue());
									paneMenuTipo.setDisable(false);
									paneTipoEliminar.setVisible(false);
									cargarTablaTipo();
								}
							});
						}
					}
				});
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------*/
	
	/*Panel MenuCondicion*/
	@FXML private Pane paneMenuCondicion;
	@FXML private Pane paneCondicionNuevo;
	@FXML private Pane paneCondicionModificar;
	@FXML private Pane paneCondicionEliminar;
	@FXML private Button condicionNuevo;
	@FXML private Button condicionNuevoAgregar;
	@FXML private Button condicionModificar;
	@FXML private Button condicionModificarBuscar;
	@FXML private Button condicionModificarAgregar;
	@FXML private Button condicionModificarEliminar;
	@FXML private Button condicionEliminar;
	@FXML private Button condicionRegresarMenu;
	@FXML private Button condicionNuevoCancelar;
	@FXML private Button condicionModificarCancelar;
	@FXML private Button condicionEliminarCancelar;
	
	/*Dato TipoCondicion*/
	@FXML private TextField idNueva;
	@FXML private TextField condicionNueva;
	@FXML private ComboBox<String> tipoS;
	@FXML private TextField idNuevaModi;
	@FXML private ComboBox<String> tipoS2;
	@FXML private TextField idNuevaEli;
	
	/*Tabla Condicion*/
	@FXML private TableView<Condicion> tablaCondicion;
	@FXML private TableColumn<Condicion, String> idC;
	@FXML private TableColumn<Condicion, String> condicionC;
	@FXML private TableColumn<Condicion, String> saludC;
	ObservableList<Condicion> tableC = FXCollections.observableArrayList();
	
	@FXML
	public void cargarTablaCondicion() {
		
			
			idC.setCellValueFactory(new PropertyValueFactory<>("IdC"));
			condicionC.setCellValueFactory(new PropertyValueFactory<>("CondicionC"));
			saludC.setCellValueFactory(new PropertyValueFactory<>("SaludC"));
			tablaCondicion.setItems(daoC.listarTipo(tableC));
			
		
	}
	
	@FXML
	public void botonesCondicion() {
		
		condicionNuevoAgregar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idNueva.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else if(condicionNueva.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la condicion");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else if(tipoS.getValue() == null) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Porfavor eliga la salud");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								ResultSet resul = star.executeQuery("Select count(idC) from Condicion where idC = '"+idNueva.getText()+"'");
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
										
										Condicion condicion = new Condicion(idNueva.getText(),condicionNueva.getText(),tipoS.getValue());
										Connection con = Conexion.getConexion();
										daoC.AgregarCondicion(con, condicion);
										paneMenuCondicion.setDisable(false);
										paneCondicionNuevo.setVisible(false);
										cargarTablaCondicion();
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
		
		condicionModificarBuscar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(idNuevaModi.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Rellene la ID");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							try {
								Statement star = Conexion.getConexion().createStatement();
								String ID = idNuevaModi.getText();
								ResultSet resul = star.executeQuery("Select * From Condicion");
								
								while(resul.next()) {
									if(resul.getString(1).equals(ID)) {
										idNuevaModi.setText(resul.getString(1));
										tipoS2.setValue(resul.getString(3));
										idNuevaModi.setDisable(true);
										condicionModificarBuscar.setDisable(true);
										tipoS2.setDisable(false);
										condicionModificarAgregar.setDisable(false);
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
		
		condicionModificarAgregar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(tipoS2.getValue() == null) {
							Alert aler = new Alert(AlertType.ERROR);
							aler.setTitle("Datos Faltantes");
							aler.setContentText("Porfavor eliga un tipo");
							aler.initStyle(StageStyle.UTILITY);
							aler.showAndWait();
						}else {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								if(opcion == ButtonType.OK) {
									Condicion condicion = new Condicion(idNuevaModi.getText(),"",tipoS2.getValue());
									Connection con = Conexion.getConexion();
									daoC.ModificarCondicion(con, condicion);
									
									paneCondicionModificar.setVisible(false);
									paneMenuCondicion.setDisable(false);
									cargarTablaCondicion();
								}
							});
						}
					}
				});
		
		condicionModificarEliminar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(!idNuevaEli.getText().isEmpty()) {
							Alert aler = new Alert(AlertType.CONFIRMATION);
							aler.showAndWait().ifPresent(opcion ->{
								if(opcion == ButtonType.OK) {
									Connection con = Conexion.getConexion();
									daoC.EliminarCondicionId(con, idNuevaEli.getText());
									cargarTablaCondicion();
									paneMenuCondicion.setDisable(false);
									paneCondicionEliminar.setVisible(false);
								}
							});
						}
					}
				});
	}
	
	/*------------------------------------------------------------------------------------------------------------------------------------------*/
	
	@FXML
	private void Paneles() {
		
		productos.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneMenuProducto.setVisible(true);
						principal.setVisible(false);
						paneTablaProducto.setVisible(true);
						cargarTablaProducto();
					}
				});
		
		tipos.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneMenuTipo.setVisible(true);
						principal.setVisible(false);
						paneTablaTipo.setVisible(true);
						cargarTablaTipo();
					}
				});
		
		condiciones.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneMenuCondicion.setVisible(true);
						principal.setVisible(false);
						paneTablaCondicion.setVisible(true);
						cargarTablaCondicion();
					}
				});
		
		productoNuevo.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoNuevo.setVisible(true);
						paneMenuProducto.setDisable(true);
						idPAgregar.clear();
						nombrePAgregar.clear();
						idTPAgregar.clear();
						fechaPAgregar.setValue(null);
						idCAgregar.clear();
					}
				});
		
		productoModificar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoModificar.setVisible(true);
						paneMenuProducto.setDisable(true);
						idMod.clear();
						nombreMod.clear();
						idTMod.clear();
						idCMod.clear();
						idMod.setDisable(false);
						productoModificarBuscar.setDisable(false);
						nombreMod.setDisable(true);
						idTMod.setDisable(true);
						idCMod.setDisable(true);
						productoModificarAgregar.setDisable(true);
					}
				});
		
		productoEliminar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoEliminar.setVisible(true);
						paneMenuProducto.setDisable(true);
						idEli.clear();
					}
				});
		
		tipoNuevo.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoNuevo.setVisible(true);
						paneMenuTipo.setDisable(true);
						idNuevo.clear();
						items.clear();
						utilidadNuevo.clear();
				    	items.addAll("Arbol", "Arbusto", "Flor", "Planta");
				    	tipo1.setItems(items);
					}
				});
		
		tipoModificar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoModificar.setVisible(true);
						paneMenuTipo.setDisable(true);
						idModi.clear();
						items.clear();
						utilidadModi.clear();
				    	items.addAll("Arbol", "Arbusto", "Flor", "Planta");
				    	tipo2.setItems(items);
				    	idModi.setDisable(false);
						tipoNuevoBuscar.setDisable(false);
						tipo2.setDisable(true);
						utilidadModi.setDisable(true);
						tipoModificarAgregar.setDisable(true);
					}
				});
		
		tipoEliminar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoEliminar.setVisible(true);
						paneMenuTipo.setDisable(true);
						idElimi.clear();
						items.clear();
				    	items.addAll("Arbol", "Arbusto", "Flor", "Planta");
				    	tipo3.setItems(items);
					}
				});
		
		condicionNuevo.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionNuevo.setVisible(true);
						paneMenuCondicion.setDisable(true);
						idNueva.clear();
						itemsS.clear();
				    	itemsS.addAll("Bueno", "Regular", "Malo");
				    	tipoS.setItems(itemsS);
					}
				});
		
		condicionModificar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionModificar.setVisible(true);
						paneMenuCondicion.setDisable(true);
						idNuevaModi.clear();
						itemsS.clear();
				    	itemsS.addAll("Bueno", "Regular", "Malo");
				    	tipoS2.setItems(itemsS);
				    	idNuevaModi.setDisable(false);
						condicionModificarBuscar.setDisable(false);
						tipoS2.setDisable(true);
						condicionModificarAgregar.setDisable(true);
					}
				});
		
		condicionEliminar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionEliminar.setVisible(true);
						paneMenuCondicion.setDisable(true);
						idNuevaEli.clear();
					}
				});
	}
	
	@FXML
	private void Regresar() {
		
		productoRegresarMenu.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						initialize();
					}
				});
		
		tipoRegresarMenu.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						tableT.clear();
						initialize();
					}
				});
		
		condicionRegresarMenu.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						initialize();
					}
				});
		
		productoNuevoCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoNuevo.setVisible(false);
						paneMenuProducto.setDisable(false);
					}
				});
		
		productoModificarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoModificar.setVisible(false);
						paneMenuProducto.setDisable(false);
					}
				});
		
		productoEliminarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneProductoEliminar.setVisible(false);
						paneMenuProducto.setDisable(false);
					}
				});
		
		tipoNuevoCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoNuevo.setVisible(false);
						paneMenuTipo.setDisable(false);
					}
				});
		
		tipoModificarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoModificar.setVisible(false);
						paneMenuTipo.setDisable(false);
					}
				});
		
		tipoEliminarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneTipoEliminar.setVisible(false);
						paneMenuTipo.setDisable(false);
					}
				});
		
		condicionNuevoCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionNuevo.setVisible(false);
						paneMenuCondicion.setDisable(false);
					}
				});
		
		condicionModificarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionModificar.setVisible(false);
						paneMenuCondicion.setDisable(false);
					}
				});
		
		condicionEliminarCancelar.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						paneCondicionEliminar.setVisible(false);
						paneMenuCondicion.setDisable(false);
					}
				});
	}
	
	@FXML
	public void volverMenu() {
		main.ventanaMenu();
		main.getStageInventario().close();
	}
	
	@FXML
    private void initialize() {
    	
		principal.setVisible(true);
		paneMenuProducto.setVisible(false);
		paneProductoNuevo.setVisible(false);
		paneProductoModificar.setVisible(false);
		paneProductoEliminar.setVisible(false);
		paneMenuTipo.setVisible(false);
		paneTipoNuevo.setVisible(false);
		paneTipoModificar.setVisible(false);
		paneTipoEliminar.setVisible(false);
		paneMenuCondicion.setVisible(false);
		paneCondicionNuevo.setVisible(false);
		paneCondicionModificar.setVisible(false);
		paneCondicionEliminar.setVisible(false);
		paneTablaTipo.setVisible(false);
		paneTablaCondicion.setVisible(false);
		paneTablaProducto.setVisible(false);
		
    }
}
