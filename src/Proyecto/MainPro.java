package Proyecto;

public class MainPro {
	public static Start inicio = new Start();
	public static Login logs = new Login();
	public static Juego game = new Juego();
	
	public static void main(String[] args){
		logs.Array();
		game.FichasArray();
		inicio.matchArray();
		
		System.out.println("----------Ghosts----------");
		int opcionInicio;
		do{
			opcionInicio = inicio.Starts();
			switch(opcionInicio){
				case 1:
					if(logs.Logins("main")){			
						inicio.MainMens();
					}
					break;
				case 2:
					logs.CrearUsu();
					break;
				case 3:
					System.out.println("Adios :)");			
					break;
				default:
					System.out.println("Error: Comando no valido");
			}
		}while(opcionInicio != 3);
	}
}
