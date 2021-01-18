package com.br.model;

public class Tabuleiro {
	
	//Atributo da classe
	private String [] [] tabuleiro = new String [3] [3];
	
	//Contrutor da classe
	public Tabuleiro () {
		
	}
	
	//M�todo de valida��o se um jogador foi vencedor ou n�o
	public boolean finalizarJogo(String escolha, String [] [] tabuleiroJogo) {
		boolean finalizar = false;
		if (tabuleiroJogo[0][0].equals(escolha) && tabuleiroJogo [0][1].equals(escolha) && tabuleiroJogo[0][2].equals(escolha)){
			finalizar = true;
		} else if (tabuleiroJogo[1][0].equals(escolha) && tabuleiroJogo[1][1].equals(escolha) && tabuleiroJogo[1][2].equals(escolha)) {
			finalizar = true;
		} else if (tabuleiroJogo[2][0].equals(escolha) && tabuleiroJogo[2][1].equals(escolha) && tabuleiroJogo[2][2].equals(escolha)) {
			finalizar = true;
		} else if (tabuleiroJogo[0][0].equals(escolha) && tabuleiroJogo[1][0].equals(escolha) && tabuleiroJogo[2][0].equals(escolha)) {
			finalizar = true;
		} else if (tabuleiroJogo[0][1].equals(escolha) && tabuleiroJogo[1][1].equals(escolha) && tabuleiroJogo[2][1].equals(escolha)) {
			finalizar = true;
		} else if (tabuleiroJogo[0][2].equals(escolha) && tabuleiroJogo[1][2].equals(escolha) && tabuleiroJogo[2][2].equals(escolha)) {
			finalizar = true;
		} else if (tabuleiroJogo[0][0].equals(escolha) && tabuleiroJogo[1][1].equals(escolha) && tabuleiroJogo[2][2].equals(escolha)) {
			finalizar = true;		
		} else if (tabuleiroJogo[2][0].equals(escolha) && tabuleiroJogo[1][1].equals(escolha) && tabuleiroJogo[2][0].equals(escolha)) {
			finalizar = true;
		} else {
			finalizar = false;
		}
		return finalizar;
	}

	//Setter e Getter
	public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(String[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public void inserirEscolha (String escolha, Integer linha, Integer coluna) {
		this.tabuleiro [linha] [coluna] = escolha;
	}
	
	

}
