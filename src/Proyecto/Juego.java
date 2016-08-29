package Proyecto;
import java.util.Scanner;


import java.util.Random;


public class Juego {
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();					//aqui fue donde iba a intentar programar el juego y me rendi
	Login logs = new Login();
	
	char arrBoard[][];
	Fichas arrFichas[];
	
	int fichasVivas = 0;
	boolean repeat = true;
	boolean ongoing = true;
	boolean currentPlayer = true;
	String player1 = "";
	String player2 = "";
	
	public void FichasArray(){
		arrFichas = new Fichas[16];
		for(int i = 0; i<arrFichas.length/2; i++){
			arrFichas[i] = new Fichas(0, 0, false, true, true);
			if(i%2 == 0){
				arrFichas[i].afinidad = false;
			}
		}
		for(int i = arrFichas.length/2; i<arrFichas.length; i++){
			arrFichas[i] = new Fichas(0 , 0, true, true, true);
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
		for(Fichas i : arrFichas){
			if(i.status){
				fichasVivas = fichasVivas + 1;
			}
		}	
	}
	
	
	
	public void Juegos(){
		
		System.out.println("--------------------------\nPLAYER 2");
		if(!MainPro.logs.Logins("p2")){
			return;
		}
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
			String currentP;
			int lowlim, maxlim;
			if(currentPlayer){
				currentP = player1;
				lowlim = 0;
				maxlim = 8;
			}
			else{
				currentP = player2;
				lowlim = 8;
				maxlim = 16;
			}
			System.out.println("TURNO de " + currentP);
			System.out.print("Ingrese la coordenada X de la pieza que desea mover: ");
			icorx = scan.nextInt();
			System.out.print("Ingrese la coordenada Y de la pieza que desea mover: ");
			icory = scan.nextInt();
			for(int i = lowlim; i<maxlim; i++){
				if(arrFichas[i].status && arrFichas[i].corx == icorx && arrFichas[i].cory == icory){
					System.out.println("-Donde desea mover?-");
					//fuck no se que hacer
				}
			}
			
		}
		
		PrintBoard();
		
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
		for(Fichas i : arrFichas){
			if(i.status){
				arrBoard[i.corx][i.cory] = 'G';
			}
			else{
				arrBoard[i.corx][i.cory] = '_';
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
	}
	
	public void SetSettings(){
		switch(MainPro.inicio.dificultad){
			case 2:
				for(int i = 4; i<8; i++){
					arrFichas[i].status = false;
				}
				for(int i = 12; i<16; i++){
					arrFichas[i].status = false;
				}
				break;
			case 3:
				for(int i = 2; i<8; i++){
					arrFichas[i].status = false;
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
						System.out.println("~Player 1 ~ Fantasma en coordeanada (" + i.corx + "," + i.cory + ")");
						System.out.print("Decidir afinidad:\n1-Bueno\n2-Malo\n-");
						ingr = scan.nextInt();
						i.afinidad = ingr == 1;
					}
					else if(i.status){
						System.out.println("~Player 2 ~ Fantasma en coordeanada (" + i.corx + "," + i.cory + ")");
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
	
	public void PrintFichas(){
		for(Fichas i : arrFichas){
			if(i.status){
				System.out.println("-corx,cory: " + i.corx + "," + i.cory + " -player: " + i.player + " -afinidad: " + i.afinidad);
			}
		}
	}
}
