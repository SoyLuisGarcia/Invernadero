package application;

import java.sql.Connection;
import java.sql.DriverManager;



public class Conexion {
	
	private static Connection con = null;
	
	public static Connection getConexion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/fran","fran","sql123");
		}catch(Exception e) {
			
		}
		return con;
	}
	
}
