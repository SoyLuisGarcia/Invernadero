package application.View;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Conexion;
import application.Main;
import application.Dao.HistorialDao;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

public class HistorialController {
	
	HistorialDao daoH = new HistorialDao();
	int cont;
	Main main;
	String ur;
	
	public void setMain(Main main) {
		this. main = main;
	}
	
	@FXML private Button Regresar;
	@FXML private Button Cancelar;
	
	@FXML private Button Guardar;
	@FXML private Button insertarNueva;
	
	@FXML private TextField idNuevo;
	@FXML private TextField id_ProductoNuevo;
	@FXML private TextField Url;
	@FXML private DatePicker date;
	
	  @FXML
	    private ImageView imagen;
	  
	//table
	@FXML private TableView<Imagenes> mostrarTable;
	@FXML private TableColumn<Imagenes,String> id;
	@FXML private TableColumn<Imagenes,String> id_Producto;
	@FXML private TableColumn<Imagenes,String> fecha;
	@FXML private TableColumn<Imagenes,String> imagenes;
	ObservableList<Imagenes> table = FXCollections.observableArrayList();	
	
	
	@FXML
	void Insertar() throws MalformedURLException {

		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File imgFile = fileChooser.showOpenDialog(null);
        System.out.println(String.valueOf(imgFile));
        ur = String.valueOf(imgFile);
        Url.setText(imgFile.toURL().toString());
        // Mostar la imagen
        if (imgFile != null) {
        
            Path origenPath = FileSystems.getDefault().getPath(String.valueOf(imgFile));
            Path destinoPath = FileSystems.getDefault().getPath(String.valueOf(imgFile));
            try {
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Error");
            }
            
        	File f = new File(String.valueOf(imgFile));
            Image image = new Image("file:" + f.getAbsolutePath());
            imagen.setImage(image);
        }

	}

	@FXML
	private void guardarN() {
		
		if(!idNuevo.getText().isEmpty() || !id_ProductoNuevo.getText().isEmpty() || date.getPromptText().isEmpty() ||
				Url.getText().isEmpty()) {
			
			
			try {
				
				Statement start = Conexion.getConexion().createStatement();
				ResultSet resul = start.executeQuery("select count(idH) from Historial where idH = '"+idNuevo.getText()+"';");
				
				
				
		         try {
		        	 while(resul.next()) {
		        		 cont = resul.getInt(1);
		        	 }
		        	 
		        	 if(cont >= 1) {
			        	 Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("pase por aqui 3");
				    		alert.showAndWait();
			         }
			         else {
			        	 
							Imagenes imagenes = new Imagenes(idNuevo.getText(),id_ProductoNuevo.getText(),String.valueOf(date.getValue()),Url.getText());
							Connection con = Conexion.getConexion();
							daoH.AgregarHistorial(con, imagenes);
							
							idNuevo.clear();
							id_ProductoNuevo.clear();
							date.setValue(null);
							Url.clear();
							imagen.setImage(null);
						initialize();
				    	
			         }
		         }catch(SQLException e){
		        	 Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("pase por aqui 2");
			    		alert.setHeaderText(e.getMessage());
			    		alert.setContentText(e.getLocalizedMessage());
			    		alert.showAndWait();
		         }
		         
			}catch(Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("pase por aqui 1");
	    		alert.setHeaderText(e.getMessage());
	    		alert.setContentText(e.getLocalizedMessage()+"\n"+e.getCause());
	    		alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Datos faltantes");
    		alert.setHeaderText("Campos vacios");
    		alert.setContentText("Rellene todos los campos requeridos");
    		alert.showAndWait();
		}
	}

	@FXML
	private void cargarTable() {
	           
	           
	           id.setCellValueFactory(new PropertyValueFactory<>("idH"));
		        id_Producto.setCellValueFactory(new PropertyValueFactory<>("idP"));
		        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha_Foto"));
		        imagenes.setCellValueFactory(new PropertyValueFactory<>("Imagen"));
		        mostrarTable.setItems(daoH.listarTipo(table));
	          
	       
		
	}
	
	@FXML
	private void Cancelar() {
		idNuevo.clear();
		id_ProductoNuevo.clear();
		date.setValue(null);
		Url.clear();
		imagen.setImage(null);
	}
	
	@FXML
	private void Regresar() {
		main.ventanaMenu();
		main.getStageHistorial().close();
	}
	
	@FXML
    private void initialize() {

		cargarTable();
		
    }
}
