package Proyecto;

public class Match {
	String ganador, perdedor, wincond;
	int num;
	public Match(String gan, String per, String winc,  int n){
		ganador = gan;
		perdedor = per;
		num = n;
		wincond = winc;
	}
	public Match(int n){
		num = n;
	}
}
