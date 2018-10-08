package jogo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Jogo extends UnicastRemoteObject implements IJogo{
	private Tabuleiro tabuleiro;
	private int jogadorDaVez = 1;
	private int numJogadores = 0;
	private long DURACAO_MAXIMA = 45 * 1000;
	private long horarioInicio = System.currentTimeMillis();
	
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
		
		horarioInicio = System.currentTimeMillis();
		System.out.print("Horario de inicio: ");
		System.out.println(horarioInicio);
		return tabuleiro.toString();
	}

	@Override
	public boolean isPartidaEncerrada() throws RemoteException {
		return tabuleiro.checagemDeColunas() != 0 
				|| tabuleiro.checagemDeLinhas() != 0
				|| tabuleiro.checagemDeDiagonais() != 0
				|| tabuleiro.tabuleiroPreenchido()
				|| ultrapassouTempo();
		
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
		else if (ultrapassouTempo())
			return (jogadorDaVez % 2) + 1;
		return vencedor == -1 ? 1 : vencedor == 1 ? 2 : 0;	
	}
	
	@Override
	public boolean ultrapassouTempo() throws RemoteException{
		System.out.print("O tempo passado em milissegundos � ");
		System.out.println(System.currentTimeMillis() - horarioInicio);
		return (System.currentTimeMillis() - horarioInicio) >= DURACAO_MAXIMA;
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
			horarioInicio = System.currentTimeMillis();
		}
	}
}
