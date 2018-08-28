package jogo;

import java.rmi.Remote;
import java.rmi.RemoteException;

import jogador.Jogador;

public interface IJogo extends Remote {
	public int entrarNaPartida () throws RemoteException;
	public boolean isMinhaVez(int i)throws RemoteException;
	public boolean isJogadaValida(int i, int posx, int posy) throws RemoteException;
	public String realizarJogada(int i, int posx, int posy ) throws RemoteException;
    public boolean isPartidaEncerrada() throws RemoteException;
    public int resultadoPartida() throws RemoteException;
    public boolean isPronto() throws RemoteException;
    public String getTabuleiro() throws RemoteException;
    public void newGame() throws RemoteException;
    
	
	//public void inciciarJogo() throws RemoteException;
	//public boolean jogar () throws RemoteException;
	//public int validarJogadores() throws RemoteException;
	//public void carregarJogadores() throws RemoteException;
	//public int ganhador() throws RemoteException;
}
