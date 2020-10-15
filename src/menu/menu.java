package menu;

import java.util.Scanner;

import basedatos.Basedatos;

import java.sql.*;

public abstract class menu {
	
	private static ResultSet row;
	private static int maxRow = -1;

	public static void init() {

		
		try {
			Scanner entrada = new Scanner(System.in);
			
			row = Basedatos.getCurrent();
			maxRow = Basedatos.getMaxRow();
			
			String opcion = "";
			
			Boolean salir = false;
			Boolean error = false;
			
		    do {
				row.next();
				
		    	System.out.println("[" + (Basedatos.getRow() + 1) + "]");
		    	System.out.println("[DNI: " + row.getString("DNI") + "]");
		    	System.out.println("[Apellido: " + row.getString("APELLIDOS") + "]");
		    	System.out.println("[CP: " + row.getString("CP") + "]");
		
		        opcion = entrada.nextLine().toLowerCase();
		        
		        if(opcion.length() == 0)
		        	opcion = " ";
		
		        switch (opcion.charAt(0)) {
		            case 'k':
		            	if(Basedatos.getRow() == (maxRow-1)) {
		            		System.out.println("Error: Estas en la última fila");
		            		error = true;
		            	}else
		            		row = Basedatos.getNext();
		                break;
		            case 'd':
		            	if(Basedatos.getRow() == 0) {
		            		System.out.println("Error: Estas en la primera fila");
		            		error = true;
		            	}else
		            		row = Basedatos.getLast();
		                break;
		            case '.':
		            	salir = true;
		            	break;
		            default:
		                try {
		                	int number = Integer.parseInt(opcion)-1;
		                	
		                	if(number < 0) {
		                		System.out.println("Error: Esa es una fila incorrecta");
		                		error = true;
		                	}else if(number >= maxRow) {
		                		System.out.println("Error: Esa es una fila incorrecta");
		                		error = true;
		                	}else
		                		row = Basedatos.getRow(number);
		                }catch(NumberFormatException e) {
		                	System.out.println("No se ha introducido un número correcto.");
		                	error = true;
		                }
		                break;
		        }
		        
		        if(error) {
		        	error = false;
		        	row = Basedatos.getCurrent();
		        }
		        
		    } while (!salir);
		    
		    entrada.close();
		    Basedatos.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
