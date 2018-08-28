package execucao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import jogo.Jogo;

public class Servidor {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		LocateRegistry.createRegistry(1099);
		Jogo jogo =  new Jogo();
		Naming.rebind("rmi://localhost/Jogo", jogo);
		System.out.println("Servidor pronto e registrado no RMI Registry.");
	}

}
