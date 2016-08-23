package Proyecto;
import java.util.Scanner;

public class Login {
	Scanner scan = new Scanner(System.in);
	String usuIngr, passIngr, nuevoUsu, nuevoCont, nuevoCont2;
	Usus usu1 = new Usus("null", "null", 0, "no");
	Usus usu2 = new Usus("null", "null", 0, "no");
	Usus usu3 = new Usus("null", "null", 0, "no");
	Usus usu4 = new Usus("null", "null", 0, "no");
	Usus usu5 = new Usus("null", "null", 0, "no");
	
	Usus arrUsuarios[] = {usu1, usu2, usu3, usu4, usu5};
			
	public boolean Logins(){
		System.out.print("Ingrese su usuario:\n-");
		usuIngr = scan.next();
		System.out.print("Ingrese su contraseña:\n-");
		passIngr = scan.next();
		
		for(Usus i : arrUsuarios){
			if(usuIngr.equals(i.usuario) && passIngr.equals(i.password)){
				i.logged = "main";
				return true;
			}
		}
		System.out.println("Usuario o contraseña incorrecta.");
		return false;
	}
	
	public void CrearUsu(){
		System.out.print("Nombre del usuario:\n-");
		nuevoUsu = scan.next();
		
		for(Usus i : arrUsuarios){
			if(nuevoUsu.equals(i.usuario)){
				System.out.println("Usuario ya esta tomado.");
				return;
			}
		}
		System.out.print("Contraseña:\n-");
		nuevoCont = scan.next();
		System.out.print("Repita la contraseña:\n-");
		nuevoCont2 = scan.next();
		
		if(nuevoCont.equals(nuevoCont2)){
			for(Usus i : arrUsuarios){
				if(i.usuario.equals("null")){
					i.usuario = nuevoUsu;
					i.password = nuevoCont;
					System.out.println("Usuario creado exitosamente!");
					return;
				}
			}
			System.out.println("Error: Hay demasiados usuarios");
		}
		else{
			System.out.println("Las contraseñas no coinciden");
		}
	}
}
