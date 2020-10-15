package basedatos;
import java.sql.*;

public abstract class Basedatos {
	
	private static Connection connection;
	private static String basedatos = "prueba";
	
	private static int currentRow = 0;
	
	private static ResultSet currentData;
	
	static{
		String randomGarbage = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String url = "jdbc:mysql://localhost:3306/" + basedatos + randomGarbage;
		try {
			connection = DriverManager.getConnection(url, "root", "");
			Basedatos.currentData = getRow(currentRow);
		}catch (SQLException e) {	error(e); }
	}
	
	private static void error(SQLException e) {
		System.out.println("SQL mensaje: " + e.getMessage());
      	System.out.println("SQL Estado: " + e.getSQLState());
      	System.out.println("SQL codigo especifico: " + e.getErrorCode());
	}
	
	//Function to get the next row of the table
	public static ResultSet getNext() {
		try {
			Statement stm = connection.createStatement();
		
			currentData = stm.executeQuery("SELECT * FROM clientes LIMIT " + ++currentRow + ", 1");
		} catch(SQLException e) {
			error(e);
		}
		
		return Basedatos.currentData;
	}
	
	public static ResultSet getCurrent() {return getRow(currentRow);}
	//Function to get the last row of the table
	public static ResultSet getLast() { 
		try {
			
			Statement stm = connection.createStatement();
		
			currentData = stm.executeQuery("SELECT * FROM clientes LIMIT " + --currentRow + ", 1");
		} catch(SQLException e) {
			error(e);
		}
		
		return Basedatos.currentData;
	}
	//Function to get a determined row
	public static ResultSet getRow(int i) {
		try {
			
			currentRow = i;
			Statement stm = connection.createStatement();
		
			Basedatos.currentData = stm.executeQuery("SELECT * FROM clientes LIMIT " + currentRow + ", 1");
			
		} catch(SQLException e) {
			error(e);
		}
		
		return Basedatos.currentData;
	}

	public static int getRow() { return currentRow;}
	public static int getMaxRow() {
		int result = -1;
		
		try {
			Statement stm = connection.createStatement();
			
			currentData = stm.executeQuery("SELECT COUNT(DNI) AS count FROM clientes");
			currentData.next();
			
			result = currentData.getInt("count");
		} catch(SQLException e) {
			error(e);
		}
		
		return result;
	}
	
	public static void close() {
		try {
			connection.close();
			currentData.close();
		} catch (SQLException e) {
			error(e);
		}
	}
}
