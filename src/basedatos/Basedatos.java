package basedatos;
import java.sql.*;

public abstract class Basedatos {
	//Function to get the next row of the table
	public static ResultSet getNext() { return null;}
	//Function to get the last row of the table
	public static ResultSet getLast() { return null;}
	//Function to get a determined row
	public static ResultSet getRow(int i) { return null;}
}
