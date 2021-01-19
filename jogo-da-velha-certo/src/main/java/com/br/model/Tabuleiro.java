package com.br.model;

public class Tabuleiro {
	
	//Atributo da classe
	private String [] [] tabuleiro = new String [3] [3];
	
	//Contrutor da classe
	public Tabuleiro () {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.tabuleiro [i] [j] = "";
			}
		}
	}
	
	//Método de validação se um jogador foi vencedor ou não
	public boolean finalizarJogo(String escolha) {
		boolean finalizar = false;
		if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro [0][1].equals(escolha) && this.tabuleiro[0][2].equals(escolha)){
			finalizar = true;
		} else if (this.tabuleiro[1][0].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[1][2].equals(escolha)) {
			finalizar = true;
		} else if (this.tabuleiro[2][0].equals(escolha) && this.tabuleiro[2][1].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			finalizar = true;
		} else if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro[1][0].equals(escolha) && this.tabuleiro[2][0].equals(escolha)) {
			finalizar = true;
		} else if (this.tabuleiro[0][1].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][1].equals(escolha)) {
			finalizar = true;
		} else if (this.tabuleiro[0][2].equals(escolha) && this.tabuleiro[1][2].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			finalizar = true;
		} else if (this.tabuleiro[0][0].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][2].equals(escolha)) {
			finalizar = true;		
		} else if (this.tabuleiro[2][0].equals(escolha) && this.tabuleiro[1][1].equals(escolha) && this.tabuleiro[2][0].equals(escolha)) {
			finalizar = true;
		} else {
			finalizar = false;
		}
		return finalizar;
	}
	
	//Método que verifica se o tabuleiro está cheio e nenhum jogador conseguiu fechar as linhas, colunas e diagonais
	public boolean verificaEmpate() {
		boolean finalizar = false;
		if    (!this.tabuleiro [0] [0].isEmpty() && !this.tabuleiro [0] [1].isEmpty() && !this.tabuleiro [0] [2].isEmpty()
			&& !this.tabuleiro [1] [0].isEmpty() && !this.tabuleiro [1] [1].isEmpty() && !this.tabuleiro [1] [2].isEmpty()
			&& !this.tabuleiro [2] [0].isEmpty() && !this.tabuleiro [2] [1].isEmpty() && !this.tabuleiro [2] [2].isEmpty()) {
			finalizar = true;
		}
		return finalizar;
	}
	
	//Exibe o tabuleiro no console com as marcações
	public void exibeTabuleiro() {
		System.out.println(" | " + this.tabuleiro [0] [0] + " | " + this.tabuleiro [0] [1] + " | " + this.tabuleiro [0] [2] + " |");
		System.out.println("----------------");
		System.out.println(" | " + this.tabuleiro [1] [0] + " | " + this.tabuleiro [1] [1] + " | " + this.tabuleiro [1] [2] + " |");
		System.out.println("----------------");
		System.out.println(" | " + this.tabuleiro [2] [0] + " | " + this.tabuleiro [2] [1] + " | " + this.tabuleiro [2] [2] + " |");
		System.out.println("----------------");
	}

	//Setter e Getter
	public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(String[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

}