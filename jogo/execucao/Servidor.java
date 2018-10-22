package execucao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import jogo.Jogo;

/**
* Classe responsavel por implementar um servidor RMI
**/
public class Servidor {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(1099);
		Jogo jogo =  new Jogo();
		Naming.rebind("rmi://localhost/Jogo", jogo);
		System.out.println("Servidor pronto e registrado no RMI Registry.");
	}

}
