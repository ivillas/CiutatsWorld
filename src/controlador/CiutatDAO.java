package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

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
	        
	        if (resultSet.next()) {
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
	    String sql = "SELECT Name  FROM country";
	    List<String> paisos = new ArrayList<>();

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            paisos.add(resultSet.getString("Name"));
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
	    code = "'" + code + "'";
	    String sql = "SELECT District FROM city WHERE CountryCode = " + code;
	    
	    List<String> districtes = new ArrayList<>();

	    try (PreparedStatement statement = con.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            districtes.add(resultSet.getString("District"));
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
	
	
	public static String codiPais (String pais) throws SQLException{
		String code = "'%" + pais + "%'";
	    Connection con = Configuracio.getConnection();
		
	    String sql = "SELECT code FROM country WHERE NAME like " + code;
	    try (PreparedStatement statement = con.prepareStatement(sql);
		         ResultSet resultSet = statement.executeQuery()) {

		        while (resultSet.next()) {
		           code = resultSet.getString("code");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    } finally {
		        if (con != null) {
		            con.close(); 
		        }
		    }

	    return code;
	
	}
	
	public static boolean existeixCiutat (String nom, String code) throws SQLException{
		Connection con = Configuracio.getConnection();
		code = "'" + code + "'";
		nom = "'" + nom + "'";
		int resultat = 0;
		String sql = "SELECT COUNT(*) FROM city WHERE name = " + nom + " AND CountryCode = " + code;
		 try (PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resulset = statement.executeQuery()) {
			 if (resulset.next()) {
			 resultat = resulset.getInt("COUNT(*)");
			 }
		}
		
		 return (resultat >0);
		}
		
	public static void addCiutat(String nom, String code, String district, long population) throws SQLException{
		int populationInt = (int) population;
	    if (population < Integer.MIN_VALUE || population > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException("La població exedeis el rang d'un enter.");
	    }
		 Connection con = Configuracio.getConnection();
		    String sql = "INSERT INTO city (name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";

		    try (PreparedStatement statement = con.prepareStatement(sql)) {
		        statement.setString(1, nom);
		        statement.setString(2, code);
		        statement.setString(3, district);
		        statement.setInt(4, populationInt);
		        statement.executeUpdate(); 
		        JOptionPane.showMessageDialog(null, "Ciutat afegida correctament");
		    }
	}
	
	public static void editarCiutat(String nom, String code, String district, long population) throws SQLException {
	    int populationInt = (int) population;
	    if (population < Integer.MIN_VALUE || population > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException("La población exedeis el rang d'un enter.");
	    }
	    
	    Connection con = Configuracio.getConnection();
	    String sql = "UPDATE city SET District = ?, Population = ? WHERE name = ? AND CountryCode = ?";

	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setString(1, district);
	        statement.setInt(2, populationInt);
	        statement.setString(3, nom);
	        statement.setString(4, code);
	        int rowsUpdated = statement.executeUpdate();

	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(null, "Ciutat editada correctament");
	        } else {
	            JOptionPane.showMessageDialog(null, "No s'ha trobat la ciutat per editar");
	        }
	    }
	}
	
}
