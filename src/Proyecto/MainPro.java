package Proyecto;

public class MainPro {
	public static void main(String[] args){
		Start inicio = new Start();
		Login ingre = new Login();
		
		System.out.println("----------Ghosts----------");
		int opcionInicio = 0;
		do{
			opcionInicio = inicio.Starts();
			switch(opcionInicio){
				case 1:
					if(ingre.Logins()){									//ingre.Logins es una funcion que devuelve un booleano, true si hizo login, false si fallo
						int opcionMainMenu = inicio.MainMens();			//el main menu
						if(opcionMainMenu == 5){
						}					
					}
					break;
				case 2:
					ingre.CrearUsu();
					break;
				case 3:
					System.out.println("Adios :)");						//si elige cerrar el programa el loop se rompe
					break;
				default:
					System.out.println("Error: Comando no valido");
			}
		}while(opcionInicio != 3);
	}
}
