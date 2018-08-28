package jogo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import jogador.Jogador;

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
	
//	public void inciciarJogo() {
//		carregarJogadores();
//		while (jogar());
//	}
//	
//	/**
//	 * Jogo rodando...
//	 */
//	public boolean jogar () {
//	
//		if (ganhador() == 0) {
//	        if(vez()==1)
//	            jogadorX.jogar(tabuleiro);
//	        else 
//	            jogadorY.jogar(tabuleiro);
//	       
//			
//			if (tabuleiro.tabuleiroPreenchido()) {
//                System.out.println("Jogo empatado!");
//                return false;				
//			}
//			jogadorDaVez++;
//			return true;
//		} else {
//            if(ganhador() == 1 )
//                System.out.println("Jogador 2 ganhou!");
//            else
//                System.out.println("Jogador 1 ganhou!");
//            
//            return false;
//		}
//		
//	}
//	
//    public int vez(){
//        if(jogadorDaVez%2 == 1)
//            return 1;
//        else
//            return 2;
//    }
//	
//	
//	/**
//	 * Método para validar o tipo do jogador, se vai ser duas pessoas, ou dois computadores 
//	 * ou uma pessoa e um computador... 
//	 * Ps.: Os dois computadores será pra disciplina de grafos...
//	 */
//	public int validarJogadores() {
//		int tipo_jogador = 0;
//		do {
//			System.out.printf("1. Pessoa...\n2. Computador...");
//			tipo_jogador = entrada.nextInt();
//			System.out.println(tipo_jogador);
//			if((tipo_jogador != 1 && tipo_jogador != 2))
//				System.out.println("Opção inválida!");
//		
//		} while (tipo_jogador != 1 && tipo_jogador != 2);
//		
//	
//		return tipo_jogador;
//	}
//	
//	/**
//	 * Método para carregar os jogadores no jogo...
//	 */
//	public void carregarJogadores() {
//        System.out.println("Informe quem será o Jogador 1 ?");
//        if(validarJogadores() == 1)
//            this.jogadorX = new Pessoa(1);
//        else
//            this.jogadorX = new Computador(1);
//        
//        System.out.println("================================");
//        System.out.println("Informe quem será o Jogador 2 ?");
//        
//        if(validarJogadores() == 1)
//            this.jogadorY = new Pessoa(2);
//        else
//            this.jogadorY = new Computador(2);		
//	}
//	
//	/**
//	 * Função para verificar se há ganhador.
//	 * @return 1 ou -1 para ganhador, 0 caso contrário.
//	 */
//	public int ganhador() {
//		return tabuleiro.checagemDeColunas() == -1?-1:tabuleiro.checagemDeLinhas() == -1?-1:tabuleiro.checagemDeDiagonais()==-1?-1:
//			   tabuleiro.checagemDeColunas() == 1? 1:tabuleiro.checagemDeLinhas() == 1? 1:tabuleiro.checagemDeDiagonais()== 1? 1: 0;
//	}
//	
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
