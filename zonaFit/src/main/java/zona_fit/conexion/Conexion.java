package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public static Connection getConnection() {
		Connection conexion = null;
		String baseDatos = "zona_fit_db";
		String url = "jdbc:postgresql://localhost:5432/"+baseDatos;
		String usuario = "postgres";
		String password= "42576661";
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url,usuario,password);
		}catch(Exception ex) {
			System.out.println("Error al intentar conectarse a la base de datos: "+ ex.getMessage());
		}
		return conexion;
	}
	
	public static void main(String[] args) {
		Connection conexion = Conexion.getConnection();
		if(conexion != null) {
			System.out.println("Conexion exitosa " + conexion);
			
		}else {
			System.out.println("Error al conectarse");
		}
	}
}
