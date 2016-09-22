package Proyecto;
import java.util.Scanner;


import java.util.Random;


public class Juego {
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();					
	
	char arrBoard[][];
	Fichas arrFichas[];
	
	int lowlim = 0, maxlim = 0, lowp2 = 0, maxp2 = 0;
	int nposx, nposy;
	int wcory = 0;
	int matchCount = 1;
	boolean repeat = true;
	boolean currentPlayer = true;
	boolean whowon;
	String player1 = "";
	String player2 = "";
	String arriba, abajo, izquierda, derecha;
	String currentP, idleP;
	String wincond, winner, loser;
	boolean barriba, babajo, bizquierda, bderecha, ongoing;
	
	
	public void FichasArray(){
		arrFichas = new Fichas[16];
		for(int i = 0; i<arrFichas.length/2; i++){
			arrFichas[i] = new Fichas(-1, -1, false, true, true);
			if(i%2 == 0){
				arrFichas[i].afinidad = false;
			}
		}
		for(int i = arrFichas.length/2; i<arrFichas.length; i++){
			arrFichas[i] = new Fichas(-1 , -1, true, true, true);
			if(i%2 == 0){
				arrFichas[i].afinidad = false;
			}
		}
		arrBoard = new char[6][6];
		for(int y = 0; y<arrBoard[0].length; y++){
			for(int x = 0; x<arrBoard[1].length; x++){
				arrBoard[x][y] = '_';
			}
		}
	}
	
	
	
	public void Juegos(){
		System.out.println("--------------------------\nPLAYER 2");
		if(!MainPro.logs.Logins("p2")){
			return;
		}
		ongoing = true;
		SetSettings();
		for(Usus i : MainPro.logs.arrUsuarios){
			if(i.logged.equals("main")){
				player1 = i.usuario;
			}
			if(i.logged.equals("p2")){
				player2 = i.usuario;
			}
		}
		currentPlayer = true;
		int icorx, icory;
		while(ongoing){
			arriba = "no disponible";
			abajo = "no disponible";
			izquierda = "no disponible";
			derecha = "no disponible";
			nposx = -1; nposy = -1;
			boolean movio = false;
			char friend;
			if(currentPlayer){
				currentP = player1;
				lowlim = 8;
				maxlim = 16;
				lowp2 = 0;
				maxp2 = 8;
				wcory = 0;
				friend = 'A';
			}
			else{
				currentP = player2;
				lowlim = 0;
				maxlim = 8;
				lowp2 = 8;
				maxp2 = 16;
				wcory = 5;
				friend = 'B';
			}
			PrintBoard();
			System.out.println("-Turno de " + currentP.toUpperCase() + " - FICHAS: " + friend);
			System.out.print("Ingrese la coordenada X de la pieza que desea mover: ");
			icorx = scan.nextInt();
			System.out.print("Ingrese la coordenada Y de la pieza que desea mover: ");
			icory = scan.nextInt();
			for(int i = lowlim; i<maxlim; i++){
				if(arrFichas[i].status){
					if(arrFichas[i].corx == icorx && arrFichas[i].cory == icory){
						System.out.println("-Donde desea mover?-");
						switch(Movement(arrFichas[i].corx, arrFichas[i].cory, friend)){
							case 1:
								if(arriba.equals("ARRIBA")){
									arrFichas[i].cory--;
									movio = true;
								}
								break;
							case 2:
								if(abajo.equals("ABAJO")){
									arrFichas[i].cory++;
									movio = true;
								}
								break;
							case 3:
								if(izquierda.equals("IZQUIERDA")){
									arrFichas[i].corx--;
									movio = true;
								}
								break;
							case 4:
								if(derecha.equals("DERECHA")){
									arrFichas[i].corx++;
									movio = true;
								}
								break;
							case 5:
								wincond = "CONCEDIO";
								printWinner();
								saveFosforo();
								MainPro.logs.CerrarSes(false);
								return;
						}
						nposx = arrFichas[i].corx;
						nposy = arrFichas[i].cory;
						PlacePieces();
					}
				}	
			}
			if(movio){
				//check stuff
				checkOverlaps();
				if(checkWinConditions()){
					wincond = "PERDIO";
					printWinner();
					saveFosforo();
					MainPro.logs.CerrarSes(false);
					return;
				}
				currentPlayer = !currentPlayer;
				continue;
			}
			System.out.println("Movimiento invalido - repita");
		}
	}
	
	public void RandSpawn(){
		for(int i = 0; i<arrFichas.length/2; i++){
			if(arrFichas[i].status){
				while(repeat){
					int rcorx = ran.nextInt(4) + 1;
					int rcory = ran.nextInt(2);
					repeat = false;
					for(int p = 0; p<arrFichas.length/2; p++){
						if(rcorx == arrFichas[p].corx && rcory == arrFichas[p].cory){
							repeat = true;
						}
					}
					arrFichas[i].corx = rcorx;
					arrFichas[i].cory = rcory;
				}
				repeat = true;
			}
		}
		for(int i = arrFichas.length/2; i<arrFichas.length; i++){
			if(arrFichas[i].status){
				do{
					int rcorx = ran.nextInt(4) + 1;
					int rcory = ran.nextInt(2) + 4;
					repeat = false;
					for(int p = arrFichas.length/2; p<arrFichas.length; p++){
						if(rcorx == arrFichas[p].corx && rcory == arrFichas[p].cory){
							repeat = true;
						}
					}
					arrFichas[i].corx = rcorx;
					arrFichas[i].cory = rcory;
				}while(repeat);
				repeat = true;
			}
		}
	}
			
	public void PlacePieces(){
		
		for(int x = 0; x<arrBoard[0].length; x++){
			for(int y = 0; y<arrBoard[1].length; y++){
				boolean found = false;
				for(Fichas i : arrFichas){
					if(i.status){
						if(i.corx == x && i.cory == y){
							arrBoard[x][y] = i.player ? 'A' : 'B';
							found = true;
						}
					}
				}
				if(!found){
					arrBoard[x][y] = '_';
				}
			}
		}
	}
	
	public void PrintBoard(){
		System.out.println("--------------------------\n  0 1 2 3 4 5");
		for(int x = 0; x<arrBoard[0].length; x++){
			System.out.print(x + " ");
			for(int y = 0; y<arrBoard[1].length; y++){
				System.out.print(arrBoard[y][x] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("--------------------------");
	}
	
	public void SetSettings(){
		switch(MainPro.inicio.dificultad){
			case 2:
				for(int i = 0; i<4; i++){
					arrFichas[i].status = true;
				}
				for(int i = 4; i<8; i++){
					arrFichas[i].status = false;
				}
				for(int i = 8; i<12; i++){
					arrFichas[i].status = true;
				}
				for(int i = 12; i<16; i++){
					arrFichas[i].status = false;
				}
				break;
			case 3:
				for(int i = 0; i<2; i++){
					arrFichas[i].status = true;
				}
				for(int i = 2; i<8; i++){
					arrFichas[i].status = false;
				}
				for(int i = 8; i<10; i++){
					arrFichas[i].status = true;
				}
				for(int i = 10; i<16; i++){
					arrFichas[i].status = false;
				}
				
				break;
			default:
				for(Fichas i : arrFichas){
					i.status = true;
				}
		}
		if(MainPro.inicio.modoDeJuegoRandom){
			RandSpawn();
			PlacePieces();
		}
		else{
			RandSpawn();
			PlacePieces();
			PrintBoard();
			int fichasBuenasP1;
			int fichasMalasP1;
			int fichasBuenasP2;
			int fichasMalasP2;
			while(true){
				fichasBuenasP1 = 0;
				fichasMalasP1 = 0;
				fichasBuenasP2 = 0;
				fichasMalasP2 = 0;
				int ingr;
				for(Fichas i : arrFichas){
					if(i.player && i.status){
						System.out.println("~ Player 1 ~ Fantasma en coordeanada (" + i.corx + "," + i.cory + ")");
						System.out.print("Decidir afinidad:\n1-Bueno\n2-Malo\n-");
						ingr = scan.nextInt();
						i.afinidad = ingr == 1;
					}
					else if(i.status){
						System.out.println("~ Player 2 ~ Fantasma en coordeanada (" + i.corx + "," + i.cory + ")");
						System.out.print("Decidir afinidad:\n1-Bueno\n2-Malo\n-");
						ingr = scan.nextInt();
						i.afinidad = ingr == 1;
					}
					if(i.player){
						if(i.afinidad){
							fichasBuenasP1++;
						}
						else{
							fichasMalasP1++;
						}
					}
					else{
						if(!i.afinidad){
							fichasBuenasP2++;
						}
						else{
							fichasMalasP2++;
						}
					}
				}
				if(fichasBuenasP1 == fichasMalasP1 && fichasBuenasP2 == fichasMalasP2){
					System.out.println("Juego inciado exitosamente!");
					break;
				}
				else{
					System.out.println("Numero invalido de fichas buenas y malas");
				}
			}
		}
	}
	
	public int Movement(int mcorx, int mcory, char fre){
		for(int x = 0; x<arrBoard[0].length; x++){
			for(int y = 0; y<arrBoard[1].length; y++){
				if(mcorx == x && mcory - 1 == y && arrBoard[x][y] != fre){
					arriba = "ARRIBA";
				}
				if(mcorx == x && mcory + 1 == y && arrBoard[x][y] != fre){
					abajo = "ABAJO";
				}
				if(mcorx - 1 == x && mcory == y && arrBoard[x][y] != fre){
					izquierda = "IZQUIERDA";
				}
				if(mcorx + 1 == x && mcory == y && arrBoard[x][y] != fre){
					derecha = "DERECHA";
				}
			}
		}
		System.out.print("1-" + arriba + "\n2-" + abajo + "\n3-" + izquierda + "\n4-" + derecha + "\n5-Conceder el juego\n-");
		return scan.nextInt();
	}
	
	public void checkOverlaps(){
		for(int i = lowp2; i<maxp2; i++){
			if(arrFichas[i].status && nposx == arrFichas[i].corx && nposy == arrFichas[i].cory){
				arrFichas[i].status = false;
				String afin = arrFichas[i].afinidad ? "BUENA" : "MALA";
				String oponente = !currentPlayer ? player1 : player2;
				System.out.println("--------------------------\nHa muerto una ficha " + afin + " del jugador " + oponente.toUpperCase());
			}
		}
	}
	
	public boolean checkWinConditions(){
		int buenas = 0, malas = 0;
		for(int i = lowp2; i<maxp2; i++){
			if(arrFichas[i].status && arrFichas[i].afinidad)
				buenas++;
			if(arrFichas[i].status && !arrFichas[i].afinidad)
				malas++;
		}
		if(buenas == 0){
			whowon = true;
			return true;
		}
		if(malas == 0){
			whowon = false;
			return true;
		}
		for(int i = lowlim; i<maxlim; i++){
			boolean checkcorx = arrFichas[i].corx == 0 || arrFichas[i].corx == 5;
			if(arrFichas[i].afinidad && checkcorx && arrFichas[i].cory == wcory){
				whowon = true;
				return true;
			}
		}
		return false;
	}
	
	public void saveFosforo(){
		String wins = whowon ? currentPlayer ? "main" : "p2" : !currentPlayer ? "main" : "p2";
		String loss = !whowon ? currentPlayer ? "main" : "p2" : !currentPlayer ? "main" : "p2";
		for(Usus i : MainPro.logs.arrUsuarios){
			if(i.logged.equals(wins)){
				winner = i.usuario;
			}
			if(i.logged.equals(loss)){
				loser = i.usuario;
			}
		}
		MainPro.inicio.arrMatch[9] = new Match(winner, loser, wincond, matchCount);
		matchCount++;
		Match aux;
		for(int i = 1; i < MainPro.inicio.arrMatch.length; i++){
			for(int j = 0; j < MainPro.inicio.arrMatch.length - i; j++){
				if(MainPro.inicio.arrMatch[j].num < MainPro.inicio.arrMatch[j+1].num){
					aux = MainPro.inicio.arrMatch[j];
					MainPro.inicio.arrMatch[j] = MainPro.inicio.arrMatch[j+1];
					MainPro.inicio.arrMatch[j+1] = aux;
				}
			}
		}
	}
	
	public void printWinner(){
		String wins = whowon ? currentPlayer ? "main" : "p2" : !currentPlayer ? "main" : "p2";
		for(Usus i : MainPro.logs.arrUsuarios){
			if(i.logged.equals(wins)){
				System.out.println("--------------------------\nFELICIDADES " + i.usuario.toUpperCase() + ", HAS GANADO EL JUEGO");
				int rankp = i.rank;
				i.rank = i.rank + 3;
				System.out.println("--------------------------\nSe te ha otorgado +3 de ranking!\nRanking previo: " + rankp 
						+ "\nNuevo ranking: " + i.rank);
			}
		}
	}
	
	public void PrintFichas(){
		for(int i = 0; i<arrFichas.length; i++){
			if(arrFichas[i].status){
				System.out.println(i + "-corx,cory: " + arrFichas[i].corx + ", " + arrFichas[i].cory + 
						" -player: " + arrFichas[i].player + " -afinidad: " + arrFichas[i].afinidad);
			}
		}
	}
}
