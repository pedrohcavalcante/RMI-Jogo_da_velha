package jogo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


@SuppressWarnings("serial")
public class Jogo extends UnicastRemoteObject implements IJogo{
	private Tabuleiro tabuleiro;
	public int jogadorDaVez = 1;
	public int numJogadores = 0;
	
	/**
	 * Construtor...
	 * @throws RemoteException 
	 */
	public Jogo() throws RemoteException {
		tabuleiro = new Tabuleiro();
	}

	@Override
	public int entrarNaPartida( ) throws RemoteException {
		if(numJogadores < 2)
			return ++numJogadores;
		else
			return -1;
	}

	@Override
	public boolean isMinhaVez(int i) throws RemoteException {
		return i == jogadorDaVez;
	}

	@Override
	public boolean isJogadaValida(int i, int posx, int posy) throws RemoteException {
		return posx <= 3 && posx >= 1 && posy <= 3 && posy >= 1 && tabuleiro.getPosicao(posx, posy) == 0;			
	}

	@Override
	public String realizarJogada(int i, int posx, int posy) throws RemoteException {
		
		tabuleiro.setPosicao(posx,  posy, i);
		jogadorDaVez = (jogadorDaVez % 2) + 1;
		return tabuleiro.toString();
	}

	@Override
	public boolean isPartidaEncerrada() throws RemoteException {
		return tabuleiro.checagemDeColunas() != 0 
				|| tabuleiro.checagemDeLinhas() != 0
				|| tabuleiro.checagemDeDiagonais() != 0
				|| tabuleiro.tabuleiroPreenchido();
		
	}

	@Override
	public int resultadoPartida() throws RemoteException {
		int vencedor = 0;
		int resultColunas = tabuleiro.checagemDeColunas();
		int resultLinhas = tabuleiro.checagemDeLinhas();
		int resultDiagonais = tabuleiro.checagemDeDiagonais();
		if( resultColunas != 0)
			vencedor = resultColunas;
		else if (resultLinhas != 0)
			vencedor = resultLinhas;
		else if (resultDiagonais != 0)
			vencedor = resultDiagonais;
		return vencedor == -1 ? 1 : vencedor == 1 ? 2 : 0;	
	}

	@Override
	public boolean isPronto() throws RemoteException {
		return numJogadores == 2;
	}

	@Override
	public String getTabuleiro() throws RemoteException {
		return tabuleiro.toString();
	}

	@Override
	public void newGame() throws RemoteException {
		if(numJogadores != 1) {
			numJogadores = 0;
			tabuleiro.zerarTabuleiro();
			jogadorDaVez = 1;
		}
	}
}
