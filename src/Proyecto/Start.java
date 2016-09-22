package Proyecto;
import java.util.Scanner;

public class Start {
	Scanner scan = new Scanner(System.in);
	int dificultad = 1;
	boolean modoDeJuegoRandom = true;
	boolean elim = false;
	Match arrMatch[];
	
	public void matchArray(){
		arrMatch = new Match[10];
		for(int i = 0; i<arrMatch.length; i++){
			arrMatch[i] = new Match(0);
		}
	}
	
	public int Starts(){
		System.out.print("--------------------------\n1- Login\n2- Crear Jugador\n3- Salir\n-");
		return scan.nextInt();
	}
	
	public void MainMens(){
		int opcionMainMenu; 	
		do{						
			System.out.print("--------------------------\n1-Jugar!\n2-Configuracion\n3-Reportes\n4-Mi Perfil\n5-Cerrar Sesion\n-");
			opcionMainMenu = scan.nextInt();
			switch(opcionMainMenu){
				case 1:
					MainPro.game.Juegos();
					break;
				case 2:
					Configuracion();
					break;
				case 3:
					Reportes();
					break;
				case 4:
					Perfil();
					if(elim){
						elim = false;
						return;
					}
					break;
				case 5:
					MainPro.logs.CerrarSes(true);
					return;
				default:
					System.out.println("Error: Comando no valido");
				
			}		
		}while(opcionMainMenu != 5);
	}
	
	public void Configuracion(){
		int opcionConfiguracion;
		do{
			System.out.print("--------------------------\n1-Dificultad\n2-Modo de Juego\n3-Creditos :)\n4-Regresar\n-");
			opcionConfiguracion = scan.nextInt();
			switch(opcionConfiguracion){
				case 1:
					System.out.print("--------------------------\n1-Normal\n2-Experto\n3-Maestro\n-");
					int opcionDificultad = scan.nextInt();
					System.out.println("--------------------------");
					switch(opcionDificultad){
						case 1:
							dificultad = 1;
							System.out.println("Dificultad: Normal");
							break;
						case 2:
							dificultad = 2;
							System.out.println("Dificultad: Experto");
							break;
						case 3: 
							dificultad = 3;
							System.out.println("Dificultad: Maestro");
							break;
						default:
							System.out.println("Error: Comando no valido");
							break;
					}
					break;
				case 2:
					System.out.print("--------------------------\n1-Aleatorio\n2-Manual\n-");
					int opcionModo = scan.nextInt();
					System.out.println("--------------------------");
					switch(opcionModo){
						case 1:
							modoDeJuegoRandom = true;
							System.out.println("Modo de juego: Aleatorio");
							break;
						case 2:
							modoDeJuegoRandom = false;
							System.out.println("Modo de juego: Manual");
							break;
						default:
							System.out.println("Error: Comando no valido");
							break;
					}
					break;
				case 3:
					System.out.println("----------Ghosts----------\n-----------Devs-----------\n-Diego Montes\n-Ivan Carcamos\n"
							+ "-Raul Alcantara\n------Special Thanks------\n-Kamil Segebre\n-Jonathan Salazar");
				case 4: 
					return;
				default:
					System.out.println("Error: Comando no valido");
					break;
			}
		}while(opcionConfiguracion != 3);
	}
	
	public void Reportes(){
		int opcionReportes; 	
		do{						
			System.out.print("--------------------------\n1-Historial de Juegos\n2-Ranking de Jugadores\n3-Regresar\n-");
			opcionReportes = scan.nextInt();
			switch(opcionReportes){
				case 1:
					Historial();
					break;
				case 2:
					Ranking();
					break;
				case 3:
					return;
				default:
					System.out.println("Error: Comando no valido");
				
			}		
		}while(opcionReportes != 5);
	}
	
	public void Perfil(){
		int opcionPerfil; 	
		do{						
			System.out.print("--------------------------\n1-Ver mis datos\n2-Cambiar Contraseña\n3-Eliminar mi cuenta\n4-Regresar\n-");
			opcionPerfil = scan.nextInt();
			switch(opcionPerfil){
				case 1:
					Datos();
					break;
				case 2:
					MainPro.logs.CambiarCont();
					break;
				case 3:
					MainPro.logs.Elim();
					if(elim){
						return;
					}
					break;
				case 4:
					return;
				default:
					System.out.println("Error: Comando no valido");
				
			}		
		}while(opcionPerfil != 4);
	}
	
	public void Historial(){
		System.out.println("--------------------------\nHISTORIAL - ULTIMOS " + arrMatch.length + " JUEGOS\n--------------------------");
		for(Match i : MainPro.inicio.arrMatch){
			if(i.ganador != null)
				System.out.println(i.ganador.toUpperCase() + " GANO - " + i.perdedor.toUpperCase() + " " + i.wincond);
		}
	}
	
	public void Ranking(){
		Usus aux;
		for(int i = 1; i < MainPro.logs.arrUsuarios.length; i++){
			for(int j = 0; j < MainPro.logs.arrUsuarios.length - i; j++){
				if(MainPro.logs.arrUsuarios[j].rank < MainPro.logs.arrUsuarios[j+1].rank){
					aux = MainPro.logs.arrUsuarios[j];
					MainPro.logs.arrUsuarios[j] = MainPro.logs.arrUsuarios[j+1];
					MainPro.logs.arrUsuarios[j+1] = aux;
				}
			}
		}
		System.out.println("--------------------------\nRANKING\n--------------------------");
		for(Usus i : MainPro.logs.arrUsuarios){
			if(i.usuario != null)
				System.out.println(i.usuario.toUpperCase() + " - Rank: " + i.rank);
		}
	}
	
	public void Datos(){
		for(int i = 0; i<MainPro.logs.arrUsuarios.length; i++){
			if(MainPro.logs.arrUsuarios[i].logged.equals("main")){
				System.out.println("--------------------------\nTus Datos:\nNombre: " + MainPro.logs.arrUsuarios[i].usuario + 
						"\nPassword: *****\nRank: " + MainPro.logs.arrUsuarios[i].rank);
			}
		}
	}
	
	
	
}
