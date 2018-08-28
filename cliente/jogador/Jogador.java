package jogador;

import jogo.Tabuleiro;

public abstract class Jogador {
	protected int id_jogador;
	protected int [] posicao = new int[2];
	
	public abstract void jogar(Tabuleiro tabuleiro);
	public abstract void jogada(Tabuleiro tabuleiro);
	public abstract boolean validarJogada(int[] posicao, Tabuleiro tabuleiro);
	
    public Jogador(int id_jogador){
        this.id_jogador = id_jogador;
        
    }
	

}
