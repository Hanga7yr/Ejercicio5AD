package menu;

import java.util.Scanner;

import basedatos.Basedatos;

import java.sql.*;

public abstract class menu {
	
	private static ResultSet row;

	public static void menu() {
		
		row = Basedatos.getCurrent();
		
		try {
			Scanner teclado = new Scanner(System.in);

			int opcion;
		    do {
		    	
		    	System.out.println("[" + Basedatos.getRow() + "]");
		    	System.out.println("[" + row.getString("DNI") + "]");
		    	System.out.println("[" + row.getString("APELLIDO") + "]");
		    	System.out.println("[" + row.getString("CP") + "]");
		
		        opcion = teclado.nextInt();
		        teclado.nextLine();
		
		        switch (opcion) {
		            case 1:
		                System.out.println("");
		                break;
		            case 0:
		                System.out.println("HASTA LA PROXIMA AMIGO");
		                break;
		            default:
		                System.out.println("!- OPCION INCORRECTA -¡");
		                break;
		        }
		    } while (opcion != 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
