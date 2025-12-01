package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ciutat;

public class CiutatDAO {
	
	
	
	public static Ciutat obtenirCiutat() {
		
		return null;
	}
	
	public static void afegirCiutat(Ciutat ciutat) {
		
	}
	
	
	public static void editarCiutat(Ciutat ciutat) {
		
		
	}
	
	public static void esborrarCiutat(String id) {
		
	}
	
	
	public static List<String> llistarCiutats() throws SQLException {
	    Connection con = Configuracio.getConnection();
	    String sql = "SELECT name FROM city";
	    List<String> ciutats = new ArrayList<>();

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            ciutats.add(resultSet.getString("name"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (con != null) {
	            con.close(); 
	        }
	    }

	    return ciutats;
	}

	

	public static List<String> llistarPerNom (String cadena){
		
		return null;
	}
	
	
	
	
}
