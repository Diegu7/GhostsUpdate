package Proyecto;
import java.util.Scanner;


import java.util.Random;


public class Juego {
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();					//aqui fue donde iba a intentar programar el juego y me rendi
	Login logs = new Login();
	
	int bm = ran.nextInt(2);
	
	
	public void FichasArray(){
		Fichas arrFichas[] = new Fichas[16];
		for(int i = 0; i<arrFichas.length; i++){
			arrFichas[i] = new Fichas(0, 0, true, true, true);
		}
	}
	
	
	public void Random(){
		
	}
	
	
	public void MainMenu(){
		
	}
	
	public void Juegos(){
		
		
	}
	
			
	
	
}
