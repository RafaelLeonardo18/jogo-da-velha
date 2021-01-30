package com.br.model;

/**************************************************************************************************
 * Descri��o: classe que representa o tabuleiro do jogo, recebendo as marca��es dos jogadores e 
 * 			  validando o resultado
 * Data: 15/01/2021
 * ************************************************************************************************/

public class Tabuleiro {
	
	//Atributo da classe
	private String [] [] tabuleiro = new String [3] [3];
	
	//Contrutor da classe para teste
	public Tabuleiro () {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.tabuleiro [i] [j] = "";
			}
		}
	}
	
	//Construtor da classe para ser usada com a interface web, utilizando uma imagem em branco para representar as casas n�o ocupadas
	public Tabuleiro (String blankImage) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.tabuleiro [i] [j] = blankImage;
			}
		}
	}
	
	//M�todo de valida��o se um dos jogadores foi vencedor ou n�o
	public boolean finalizarJogo(String escolha) {
		if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro [0][1].equals(escolha) && this.tabuleiro[0][2].equals(escolha)){
			return true;
		} else if (this.tabuleiro[1][0].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[1][2].equals(escolha)) {
			return true;
		} else if (this.tabuleiro[2][0].equals(escolha) && this.tabuleiro[2][1].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			return true;
		} else if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro[1][0].equals(escolha) && this.tabuleiro[2][0].equals(escolha)) {
			return true;
		} else if (this.tabuleiro[0][1].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][1].equals(escolha)) {
			return true;
		} else if (this.tabuleiro[0][2].equals(escolha) && this.tabuleiro[1][2].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			return true;
		} else if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			return true;		
		} else if (this.tabuleiro[0][2].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][0].equals(escolha)) {
			return true;
		} else {
			return false;
		}
	}
	
	//M�todo de teste que valida um empate, verificando se o tabuleiro est� cheio e nenhum jogador conseguiu fechar as linhas, colunas e diagonais
	//Deve ser utilizado logo ap�s o m�todo finalizarJogo()
	public boolean verificaEmpateTeste() {
		if  (!this.tabuleiro [0] [0].isEmpty() && !this.tabuleiro [0] [1].isEmpty() && !this.tabuleiro [0] [2].isEmpty()
			&& !this.tabuleiro [1] [0].isEmpty() && !this.tabuleiro [1] [1].isEmpty() && !this.tabuleiro [1] [2].isEmpty()
			&& !this.tabuleiro [2] [0].isEmpty() && !this.tabuleiro [2] [1].isEmpty() && !this.tabuleiro [2] [2].isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	//M�todo que ser� utilizado no jogo  para verificar se o tabuleiro est� cheio e nenhum jogador conseguiu fechar as linhas, colunas e diagonais
	//Deve ser utilizado logo ap�s o m�todo finalizarJogo()
	public boolean verificaEmpateJogo() {
		if  (!this.tabuleiro [0] [0].equals("blank.png") && !this.tabuleiro [0] [1].equals("blank.png") && !this.tabuleiro [0] [2].equals("blank.png")
			&& !this.tabuleiro [1] [0].equals("blank.png") && !this.tabuleiro [1] [1].equals("blank.png") && !this.tabuleiro [1] [2].equals("blank.png")
			&& !this.tabuleiro [2] [0].equals("blank.png") && !this.tabuleiro [2] [1].equals("blank.png") && !this.tabuleiro [2] [2].equals("blank.png")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verifica se uma casa est� disponivel para marca��o, m�todo usado no modo dois jogadores
	public boolean verificaCasaVazia(String [] [] tabuleiro, byte linha, byte coluna) {
		if (tabuleiro[linha][coluna].equals("blank.png")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Exibe o tabuleiro no console com as marca��es feitas
	public void exibeTabuleiro() {
		System.out.println(" | " + this.tabuleiro [0] [0] + " | " + this.tabuleiro [0] [1] + " | " + this.tabuleiro [0] [2] + " |");
		System.out.println("----------------");
		System.out.println(" | " + this.tabuleiro [1] [0] + " | " + this.tabuleiro [1] [1] + " | " + this.tabuleiro [1] [2] + " |");
		System.out.println("----------------");
		System.out.println(" | " + this.tabuleiro [2] [0] + " | " + this.tabuleiro [2] [1] + " | " + this.tabuleiro [2] [2] + " |");
		System.out.println("----------------\n");
	}

	//Setter e Getter
	public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(String[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

}