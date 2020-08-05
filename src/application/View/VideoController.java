package application.View;

import java.io.File;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoController {
	
	Main main;
	
	@FXML private MediaView video;
	private MediaPlayer mp;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	void detener() {
		mp.stop();
	}
	
	@FXML
	void reproducir() {
		mp.play();
	}
	
	@FXML
	void regresar() {
		main.getStageVideo().close();
		main.ventanaMenu();
	}
	
	@FXML
	void initialize() {
		File f = new File("src/application/Images/Presentacion.mp4");
		Media media = new Media(f.toURI().toString());
		mp = new MediaPlayer(media);
		video.setMediaPlayer(mp);
	}
}
