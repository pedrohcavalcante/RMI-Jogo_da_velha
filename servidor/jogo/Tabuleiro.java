package jogo;

public class Tabuleiro {
	
	/**
	 * Tabuleiro do jogo
	 */
	private int tabuleiro[][] =  new int[3][3];
	/**
	 * Método para zerar o tabuleiro...
	 * */
	public void zerarTabuleiro() {
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				tabuleiro[linha][coluna] = 0;
			}
		}
	}
	
	/**
	 * Método para exibir o tabuleiro sempre que
	 * executar uma nova jogada.
	 */
	public void exibirTabuleiro() {
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
	               if(tabuleiro[linha][coluna]==-1)
	                    System.out.print(" X ");
	                
	                if(tabuleiro[linha][coluna]==1)
	                    System.out.print(" O ");
	                
	                if(tabuleiro[linha][coluna]==0)
	                    System.out.print("   ");
	                
	                
	                if(coluna==0 || coluna==1)
	                    System.out.print("/");		
			}
            System.out.println();

		}		
	}
	
	/**
	 * Função para checar as linhas do tabuleiro
	 * @return vencedor, se 1 então X, se -1 então O, se 0 então ninguém
	 */
	public int checagemDeLinhas() {
		for (int linha = 0; linha < 3; linha++) {
			if ((tabuleiro[linha][0] + tabuleiro[linha][1] + tabuleiro[linha][2]) == 3) {
				return 1;
			}
			if ((tabuleiro[linha][0] + tabuleiro[linha][1] + tabuleiro[linha][2]) == -3) {
				return -1;
			}
		}
		return 0;
	}
	
	/**
	 * Função para checar as Colunas do tabuleiro
	 * @return vencedor, se 1 então X, se -1 então O, se 0 então ninguém
	 */	
	public int checagemDeColunas() {
		for (int coluna = 0; coluna < 3; coluna++) {
			if ((tabuleiro[0][coluna] + tabuleiro[1][coluna] + tabuleiro[2][coluna]) == 3) {
				return 1;
			}
			if ((tabuleiro[0][coluna] + tabuleiro[1][coluna] + tabuleiro[2][coluna]) == -3) {
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Função para checar as diagonais do tabuleiro
	 * @return vencedor, se 1 então X, se -1 então O, se 0 então ninguém
	 */
	public int checagemDeDiagonais() {
        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == 3) {
        		return 1;
        }
            
        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == 3) {
            return 1;
        }
        
        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == -3) {
            return -1;
        }
        
        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == -3) {
            return -1;
        }
        
		return 0;
	}
	
	/**
	 * Função para verificar se o tabuleiro foi totalmente preenchido
	 * @return true caso o tabuleiro esteja totalmente preenchido
	 */
    public boolean tabuleiroPreenchido() {
        for(int linha = 0; linha < 3; linha++) {
            for(int coluna = 0; coluna < 3; coluna++) {
                if(tabuleiro[linha][coluna] == 0) {
                		return false;
                }
            }
        }
                    
        return true;
    }
    
    /**
     * Retorna posição do tabuleiro
     * @param posicao
     * @return Valor na determinada posição 
     */
    public int getPosicao(int posx, int posy){
        return tabuleiro[posx-1][posy-1];
    }
    
    /**
     * Marca a posição que o jogador escolher
     * @param posicao
     * @param jogador
     */
    public void setPosicao(int posx, int posy, int jogador){
        if(jogador == 1) {
            tabuleiro[posx-1][posy-1] = -1;
        }else {
            tabuleiro[posx-1][posy-1] = 1;
        }
        exibirTabuleiro();
    }

    /**
     * Retorna todo o tabubleiro para ser printado do lado cliente...
     */
    	@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		for (int linha = 0; linha < 3; linha++) {
    			for (int coluna = 0; coluna < 3; coluna++) {
    	               if(tabuleiro[linha][coluna]==-1)
    	                    sb.append(" X ");
    	                
    	                if(tabuleiro[linha][coluna]==1)
    	                		sb.append(" O ");
    	                
    	                if(tabuleiro[linha][coluna]==0)
    	                    sb.append("   ");
    	                
    	                
    	                if(coluna==0 || coluna==1)
    	                		sb.append("|");		
    			}
    			sb.append("\n");
    		}	
    		return sb.toString();
    	}
}
