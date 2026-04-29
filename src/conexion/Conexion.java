package conexion;

import java.sql.Connection;
import java.sql.DriverManager;



public class Conexion {
	Connection cx;

	public Connection conectar() {
		try {
			cx = DriverManager.getConnection("jdbc:mysql://localhost/PuntoDeVenta", "root", "");
			System.out.println("CONEXION EXITOSA");
		} catch (Exception e) {
			System.out.println("ALGO SALIO MAL");// TODO: handle exception
		}
		return cx;

	}

	public static void main(String[] cecyto) {
		Conexion da = new Conexion();
		da.conectar();
	}
}
