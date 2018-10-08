package jogo;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IJogo extends Remote {
	/**
	 * Funcão para entrar na partida
	 * @return a quantidade de jogadores logados no jogo
	 * @throws RemoteException
	 */
	public int entrarNaPartida () throws RemoteException;
	/**
	 * Verifica se é a vez do jogador
	 * @param i
	 * @return true caso verdadeiro, false caso falso
	 * @throws RemoteException
	 */
	public boolean isMinhaVez(int i)throws RemoteException;
	/**
	 * Verifica se a jogada é válida
	 * @param i
	 * @param posx
	 * @param posy
	 * @return  true caso verdadeiro, false caso falso
	 * @throws RemoteException
	 */
	public boolean isJogadaValida(int i, int posx, int posy) throws RemoteException;
	/**
	 * Função para realizar a jogada de cada jogador
	 * @param i
	 * @param posx
	 * @param posy
	 * @return a string do tabuleiro a ser printado na tela
	 * @throws RemoteException
	 */
	public String realizarJogada(int i, int posx, int posy ) throws RemoteException;
	/**
	 * Função para verificar se a partida foi encerrada
	 * @return true caso verdadeiro, false caso falso
	 * @throws RemoteException
	 */
    public boolean isPartidaEncerrada() throws RemoteException;
    /**
     * Verifica se a partida foi encerrada
     * @return o jogador vencedor
     * @throws RemoteException
     */
    public int resultadoPartida() throws RemoteException;
    /**
     * Verifica se há dois jogadores na partida
     * @return  true caso verdadeiro, false caso falso
     * @throws RemoteException
     */
    public boolean isPronto() throws RemoteException;
    /**
     * Retorna o tabuleiro
     * @return tabuleiro
     * @throws RemoteException
     */
    public String getTabuleiro() throws RemoteException;
    /**
     * Método para iniciar um novo jogo caso um dos jogadores queira.
     * @throws RemoteException
     */
    public void newGame() throws RemoteException;
    
    public boolean ultrapassouTempo() throws RemoteException;
}
