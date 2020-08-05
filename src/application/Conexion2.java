package application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Conexion2 {
	private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
	
	public Conexion2() {
		
		try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-MySQL.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
            System.out.print(factory);
        } catch (Throwable ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error a surgido");
            alert.setContentText("No se ha podidom conectar a la base de datos "+ex.getLocalizedMessage());
            alert.showAndWait();
        }
		
	}

}
