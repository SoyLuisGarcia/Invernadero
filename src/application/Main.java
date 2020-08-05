package application;
	
import application.View.BaseController;
import application.View.CalendarioController;
import application.View.HistorialController;
import application.View.InicioController;
import application.View.InventarioController;
import application.View.MenuController;
import application.View.MenuGraficasController;
import application.View.RiegoController;
import application.View.TablaFotoController;
import application.View.TablaProductoController;
import application.View.TablaRiegoDiaController;
import application.View.TablaRiegoProDiaController;
import application.View.TablaRiegoTipoController;
import application.View.TablaTipoController;
import application.View.VideoController;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Conexion2 con;
	
	private Stage stage;
	private Stage inicioStage;
	private Stage menuStage;
	private Stage videoStage;
	private Stage inventarioStage;
	private Stage calendarioStage;
	private Stage riegoStage;
	private Stage fotoStage;
	
	private Stage menuGraficasStage;
	private Stage gTipoStage;
	private Stage gProductoStage;
	private Stage gRiegoTStage;
	private Stage gRiegoDStage;
	private Stage gRiegoPDStage;
	private Stage gFotoStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Base.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,247,248);
			primaryStage.setScene(scene);
			BaseController base = loader.getController();
			base.setMain(this);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void conexion(Conexion2 con) {
		this.con = con;
	}
	
	public Stage getStageBase() {
		return stage;
	}
	
	public void ventanaInicio() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Inicio.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageInicio = new Stage();
			inicioStage = stageInicio;
			Scene scene = new Scene(root,287,255);
			stageInicio.initModality(Modality.APPLICATION_MODAL);
			stageInicio.setScene(scene);
			stageInicio.setResizable(false);
			stageInicio.initOwner(stage);
			InicioController iniciar = loader.getController();
			iniciar.setMain(this);
			stageInicio.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageInicio() {
		return inicioStage;
	}
	
	public void ventanaMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Menu.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageMenu = new Stage();
			menuStage = stageMenu;
			Scene scene = new Scene(root,272,567);
			stageMenu.initModality(Modality.APPLICATION_MODAL);
			stageMenu.setScene(scene);
			stageMenu.setResizable(false);
			MenuController menu = loader.getController();
			menu.setMain(this);
			stageMenu.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageMenu() {
		return menuStage;
	}
	
	public void ventanaInventario() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Inventario.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageInventario = new Stage();
			inventarioStage = stageInventario;
			Scene scene = new Scene(root,940,490);
			stageInventario.initModality(Modality.APPLICATION_MODAL);
			stageInventario.setScene(scene);
			stageInventario.setResizable(false);
			stageInventario.initOwner(stage);
			InventarioController inventario = loader.getController();
			inventario.setMain(this,con);
			stageInventario.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageInventario() {
		return inventarioStage;
	}
	
	public void ventanaVideo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Video.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageVideo = new Stage();
			videoStage = stageVideo;
			Scene scene = new Scene(root,390,390);
			stageVideo.initModality(Modality.APPLICATION_MODAL);
			stageVideo.setScene(scene);
			stageVideo.setResizable(false);
			stageVideo.initOwner(stage);
			VideoController video = loader.getController();
			video.setMain(this);
			stageVideo.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageVideo() {
		return videoStage;
	}
	
	public void ventanaCalendario() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Calendario.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageCalendario = new Stage();
			calendarioStage = stageCalendario;
			Scene scene = new Scene(root,777,435);
			stageCalendario.initModality(Modality.APPLICATION_MODAL);
			stageCalendario.setScene(scene);
			stageCalendario.setResizable(false);
			stageCalendario.initOwner(stage);
			CalendarioController calendario = loader.getController();
			calendario.setMain(this);
			stageCalendario.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageCalendario() {
		return calendarioStage;
	}
	
	public void ventanaRiego() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Riego.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageRiego = new Stage();
			riegoStage = stageRiego;
			Scene scene = new Scene(root,777,435);
			stageRiego.initModality(Modality.APPLICATION_MODAL);
			stageRiego.setScene(scene);
			stageRiego.setResizable(false);
			stageRiego.initOwner(stage);
			RiegoController riego = loader.getController();
			riego.setMain(this);
			stageRiego.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageRiego() {
		return riegoStage;
	}
	
	public void ventanaHistorial() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/HistorialFoto.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageHistorial = new Stage();
			fotoStage = stageHistorial;
			Scene scene = new Scene(root,790,490);
			stageHistorial.initModality(Modality.APPLICATION_MODAL);
			stageHistorial.setScene(scene);
			stageHistorial.setResizable(false);
			stageHistorial.initOwner(stage);
			HistorialController historial = loader.getController();
			historial.setMain(this);
			stageHistorial.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageHistorial() {
		return fotoStage;
	}
	
	public void ventanaMenuGraficasM() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/MenuGraficas.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficasM = new Stage();
			menuGraficasStage = stageGraficasM;
			Scene scene = new Scene(root,269,491);
			stageGraficasM.initModality(Modality.APPLICATION_MODAL);
			stageGraficasM.setScene(scene);
			stageGraficasM.setResizable(false);
			stageGraficasM.initOwner(stage);
			MenuGraficasController graficaM = loader.getController();
			graficaM.setMain(this);
			stageGraficasM.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficasM() {
		return menuGraficasStage;
	}
	
	public void ventanaGraficaTipo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaTipo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaTipo = new Stage();
			gTipoStage = stageGraficaTipo;
			Scene scene = new Scene(root,722,456);
			stageGraficaTipo.initModality(Modality.APPLICATION_MODAL);
			stageGraficaTipo.setScene(scene);
			stageGraficaTipo.setResizable(false);
			stageGraficaTipo.initOwner(stage);
			TablaTipoController graficaTipo = loader.getController();
			graficaTipo.setMain(this);
			stageGraficaTipo.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaTipo() {
		return gTipoStage;
	}
	
	public void ventanaGraficaProducto() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaProducto.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaPro = new Stage();
			gProductoStage = stageGraficaPro;
			Scene scene = new Scene(root,722,456);
			stageGraficaPro.initModality(Modality.APPLICATION_MODAL);
			stageGraficaPro.setScene(scene);
			stageGraficaPro.setResizable(false);
			stageGraficaPro.initOwner(stage);
			TablaProductoController graficaPro = loader.getController();
			graficaPro.setMain(this);
			stageGraficaPro.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaProducto() {
		return gProductoStage;
	}
	
	public void ventanaGraficaRiegoTipo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaRiegoTipo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaRiegoT = new Stage();
			gRiegoTStage = stageGraficaRiegoT;
			Scene scene = new Scene(root,722,456);
			stageGraficaRiegoT.initModality(Modality.APPLICATION_MODAL);
			stageGraficaRiegoT.setScene(scene);
			stageGraficaRiegoT.setResizable(false);
			stageGraficaRiegoT.initOwner(stage);
			TablaRiegoTipoController graficaRiegoT = loader.getController();
			graficaRiegoT.setMain(this);
			stageGraficaRiegoT.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaRiegoTipo() {
		return gRiegoTStage;
	}
	
	public void ventanaGraficaRiegoDia() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaRiegoDia.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaRiegoD = new Stage();
			gRiegoDStage = stageGraficaRiegoD;
			Scene scene = new Scene(root,722,456);
			stageGraficaRiegoD.initModality(Modality.APPLICATION_MODAL);
			stageGraficaRiegoD.setScene(scene);
			stageGraficaRiegoD.setResizable(false);
			stageGraficaRiegoD.initOwner(stage);
			TablaRiegoDiaController graficaRiegoD = loader.getController();
			graficaRiegoD.setMain(this);
			stageGraficaRiegoD.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaRiegoDia() {
		return gRiegoDStage;
	}
	
	public void ventanaGraficaRiegoProDia() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaRiegoProDia.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaRiegoPD = new Stage();
			gRiegoPDStage = stageGraficaRiegoPD;
			Scene scene = new Scene(root,722,456);
			stageGraficaRiegoPD.initModality(Modality.APPLICATION_MODAL);
			stageGraficaRiegoPD.setScene(scene);
			stageGraficaRiegoPD.setResizable(false);
			stageGraficaRiegoPD.initOwner(stage);
			TablaRiegoProDiaController graficaRiegoPD = loader.getController();
			graficaRiegoPD.setMain(this);
			stageGraficaRiegoPD.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaRiegoProDia() {
		return gRiegoPDStage;
	}
	
	public void ventanaGraficaFoto() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/TablaFoto.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Stage stageGraficaFoto = new Stage();
			gFotoStage = stageGraficaFoto;
			Scene scene = new Scene(root,722,456);
			stageGraficaFoto.initModality(Modality.APPLICATION_MODAL);
			stageGraficaFoto.setScene(scene);
			stageGraficaFoto.setResizable(false);
			stageGraficaFoto.initOwner(stage);
			TablaFotoController graficaFoto = loader.getController();
			graficaFoto.setMain(this);
			stageGraficaFoto.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getStageGraficaFoto() {
		return gFotoStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
