package menu;

import java.io.IOException;
import java.util.Scanner;


public abstract class menu {

	public void menu() throws IOException {
		Scanner teclado = new Scanner(System.in);

		int opcion;
	    do {
	        System.out.println("");
	
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
	}
	
}
