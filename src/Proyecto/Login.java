package Proyecto;
import java.util.Scanner;

public class Login {
	Scanner scan = new Scanner(System.in);
	Usus arrUsuarios[];
	String usuIngr, passIngr, nuevoUsu, nuevoCont, nuevoCont2;
	
	
	public void Array(){
		arrUsuarios = new Usus[10];
		for(int i = 0; i<arrUsuarios.length; i++){
			arrUsuarios[i] = new Usus();
		}
		arrUsuarios[0] = new Usus("dego", "dego", 5, "no");
		arrUsuarios[1] = new Usus("ivan", "ivan", 6, "no");
		arrUsuarios[2] = new Usus("raul", "raul", 2, "no");
	}
	
	public boolean Logins(String tipo){
		System.out.print("--------------------------\nIngrese su usuario:\n-");
		usuIngr = scan.next();
		System.out.print("Ingrese su contraseña:\n-");
		passIngr = scan.next();
		
		for(Usus i : arrUsuarios){
			if(usuIngr.equals(i.usuario) && passIngr.equals(i.password) && !i.logged.equals("main")){
				i.logged = tipo;
				return true;
			}
		}
		System.out.println("Usuario o contraseña incorrecta.");
		return false;
	}
	
	public void CrearUsu(){
		System.out.print("--------------------------\nNombre del usuario:\n-");
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
				if(i.usuario.equals(null)){
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
	
	public void CerrarSes(boolean todos){
		if(todos){
			for(Usus i : arrUsuarios){
				i.logged = "no";
			}
		}
		else{
			for(Usus i : arrUsuarios){
				if(i.logged.equals("p2"))
					i.logged = "no";
			}
		}
		
		
	}
	
	public void Elim(){
		System.out.print("--------------------------\n*ESTA SEGURO QUE DESEA ELIMINAR SU CUENTA?*\n1-Si\n2-No\n-");
		int elimChoice = scan.nextInt();
		switch(elimChoice){
			case 1:
				for(Usus i : arrUsuarios){
					if(i.logged.equals("main")){
						i.usuario = null;
						i.password = null;
						i.rank = 0;
					}
					i.logged = "no";
				}
				MainPro.inicio.elim = true;
			default:
				break;
		}
		
	}
	
	public void CambiarCont(){
		
		System.out.print("--------------------------\nIngrese su vieja contraseña:\n-");
		passIngr = scan.next();
		
		for(Usus i : arrUsuarios){
			if(i.logged.equals("main") && passIngr.equals(i.password)){
				System.out.print("Ingrese su nueva contraseña:\n-");
				nuevoCont = scan.next();
				System.out.print("Repita su nueva contraseña:\n-");
				nuevoCont2 = scan.next();
				if(nuevoCont.equals(nuevoCont2)){
					i.password = nuevoCont;
					System.out.println("--------------------------\nContraseña Cambiada!");
				}
				else{
					System.out.println("--------------------------\nLas nuevas contraseñas no coinciden");
				}
				return;
			}
		}
		System.out.println("--------------------------\nContraseña incorrecta");
	}

	public void PrintLogs(){
		for(Usus i : arrUsuarios){
				System.out.println("-usu: \"" + i.usuario + "\" -pass: \"" + i.password + "\" -logd: \"" + i.logged + "\" -rank: " + i.rank);
		}
	}
}

