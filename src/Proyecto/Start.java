package Proyecto;
import java.util.Scanner;

public class Start {
	Scanner scan = new Scanner(System.in);
	int choice = 0, choice2 = 0;
	
	public int Starts(){
		System.out.print("--------------------------\n1- Login\n2- Crear Jugador\n3- Salir\n-");
		choice = scan.nextInt();
		return choice;
		
	}
	
	public int MainMens(){
		System.out.print("--------------------------\n1-Jugar!\n2-Configuracion\n3-Data\n4-Mi Perfil\n5-Salir\n-");
		choice2 = scan.nextInt();
		return choice2;
	}
}
