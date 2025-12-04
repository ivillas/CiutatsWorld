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

	

	public static List<String> llistarPerNom (String cadena) throws SQLException{
	    Connection con = Configuracio.getConnection();
	    cadena = "'%" + cadena + "%'";
	    String sql = "SELECT name FROM city WHERE name LIKE" + cadena;
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
	
	
	
	public static String ultimId() throws SQLException {
	    Connection con = Configuracio.getConnection();
	    String sql = "SELECT MAX(ID) + 1 AS proximId FROM city"; 
	    String id = null;

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {
	        
	        // Mover el cursor al primer resultado
	        if (resultSet.next()) {
	            // Obtener el nuevo ID usando el alias 'new_id'
	            id = resultSet.getString("proximId");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (con != null) {
	            con.close(); 
	        }
	    }
	    return id;
	}

	
	
	public static List<String> llistaPaisos() throws SQLException {
	    Connection con = Configuracio.getConnection();
	    String sql = "SELECT name FROM city";
	    List<String> paisos = new ArrayList<>();

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            paisos.add(resultSet.getString("name"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (con != null) {
	            con.close(); 
	        }
	    }

	    return paisos;
	}
	
	
	public static List<String> llistaDistrictes (String code) throws SQLException{
	    Connection con = Configuracio.getConnection();
	    code = "'%" + code + "%'";
	    String sql = "SELECT name FROM city WHERE name LIKE" + code;
	    List<String> districtes = new ArrayList<>();

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            districtes.add(resultSet.getString("name"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (con != null) {
	            con.close(); 
	        }
	    }

	    return districtes;
	}
	
	
	
	
}
