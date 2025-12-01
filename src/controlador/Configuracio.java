package controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Configuracio
 * Clase per a la configuracio de la conexio
 * amb la base de dades.
 * @author Ivillas
 * @version 1.0
 */

public class Configuracio {
	
	public static final String PROTOCOL = "jdbc:mysql://";
	public static final String IP_SERVIDOR = "127.0.0.1";
	public static final String PORT_BBDD = "3307";
	public static final String NOM_BBDD = "world";
	public static final String NO_USE_SSL = "?useSSL=false";
	public static final String USER_BBDD = "root";
	public static final String PASWORD_BBDD = "root";
	public static final String NOM_DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";
	public static final String IP_SERVIDOR_BBDD = PROTOCOL + IP_SERVIDOR + ":" + PORT_BBDD + "/" + NOM_BBDD;
	

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(IP_SERVIDOR_BBDD, USER_BBDD, PASWORD_BBDD);
	}

	
}
