package com.br.model;

import java.util.Random;

import com.br.interfacejogo.InterfaceJogo;

/**************************************************************************************************
 * Descricao: classe que representa o computador rival do jogo
 * Data: 15/01/2021
 * ************************************************************************************************/

public class CPU implements InterfaceJogo {
	
	//Atributos da classe
	private byte dificuldade;
	private String escolha;
	
	//Construtor da classe
	public CPU () {
		this.escolha = "";
	}
	
	//Metodo de jogada da maquina conforme o valor escolhido antes da partida: 1) Facil, 2) Medio e 3) Avancado
	public String [] [] fazerJogada(String [] [] tabuleiro){
		if (this.dificuldade == 1) {
			tabuleiro = jogadaFacil(tabuleiro);
		} else if (this.dificuldade == 2) {
			tabuleiro = jogadaMedia(tabuleiro);
		} else if (this.dificuldade == 3) {
			tabuleiro = jogadaAvancada(tabuleiro);
		}
		return tabuleiro;
	}
		
	//Jogada de nivel facil: preenche de forma sequencial as casa vazias do tabuleiro
	private String [] [] jogadaFacil (String [] [] tabuleiro){
		boolean inserir = false;
		while (inserir == false) {
			for (byte i = 0; i < 3; i++) {
				for (byte j = 0; j < 3; j++) {
					if (tabuleiro [i] [j].isEmpty() || tabuleiro [i] [j].equals("blank.png")) {
						tabuleiro [i] [j] = this.escolha;
						System.out.println("A CPU escolheu a posi��o " + (i + 1) + "X" + (j + 1) + " para marcar um " + this.escolha);
						inserir = true;
						break;
					} else {
						continue;
					}
				}
				if (inserir) {
					break;
				}
			}
		}
		return tabuleiro;
	}
		
	//Jogada de nivel medio: realiza as jogadas de forma aleatoria, porem quando tem poucas casas disponíveis preenche de forma sequencial as casas vazias restantes
	private String [] [] jogadaMedia(String [] [] tabuleiro){
		Random gerador = new Random();
		boolean inserir = false;
		while (inserir == false) {
			byte casasDisponiveis = 0;
			for (byte i = 0; i < 3; i++) {
				for (byte j = 0; j < 3; j++) {
					if (tabuleiro [i] [j].isEmpty() || tabuleiro [i] [j].equals("blank.png")) {
						casasDisponiveis ++;
					}
				}
			}
			if (casasDisponiveis > 4) {
				byte linha = (byte) gerador.nextInt(2);
				byte coluna = (byte) gerador.nextInt(2);
				if (tabuleiro [linha] [coluna].isEmpty() || tabuleiro [linha] [coluna].equals("blank.png")) {
					tabuleiro [linha] [coluna] = escolha;
					System.out.println("A CPU escolheu a posi��o " + (linha + 1) + "X" + (coluna + 1) + " para marcar um " + this.escolha);
					inserir = true;
				} else {
					System.out.println("A CPU ainda est� escolhendo sua jogada!");
				}
			} else {
				tabuleiro = jogadaFacil(tabuleiro);
				inserir = true;	
			}	
		}
		return tabuleiro;
	}
		
	//Jogada de nivel avancado: realiza uma serie de analises antes de fazer a jogada, verificando a possibilidade de evitar derrota ou de vencer o jogo em cada linha, coluna e diagonal
	private String [] [] jogadaAvancada(String [] [] tabuleiro){	
		
		//Linha 01
		if ( (tabuleiro [0] [0].isEmpty() || tabuleiro [0] [0].equals("blank.png"))
			&& !tabuleiro [0] [1].isEmpty()
			&& !tabuleiro [0] [1].equals("blank.png")
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& tabuleiro [0] [1].equals(tabuleiro [0] [2]) ) {
			
			tabuleiro [0] [0] = this.escolha;
			
		} else if ( (tabuleiro [0] [1].isEmpty() || tabuleiro [0] [1].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [0] [2]) ) {
			
			tabuleiro [0] [1] = this.escolha;
			
		} else if ( (tabuleiro [0] [2].isEmpty() || tabuleiro [0] [2].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [0] [1].isEmpty()
			&& !tabuleiro [0] [1].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [0] [1]) ) {
			
			tabuleiro [0] [2] = this.escolha;
		
		//Linha 02
		} else if ( (tabuleiro [1] [0].isEmpty() || tabuleiro [1] [0].equals("blank.png"))
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& !tabuleiro [1] [2].isEmpty()
			&& !tabuleiro [1] [2].equals("blank.png")
			&& tabuleiro [1] [1].equals(tabuleiro [1] [2]) ) {
			
			tabuleiro [1] [0] = this.escolha;
			
		} else if ( (tabuleiro [1] [1].isEmpty() || tabuleiro [1] [1].equals("blank.png"))
			&& !tabuleiro [1] [0].isEmpty()
			&& !tabuleiro [1] [0].equals("blank.png")
			&& !tabuleiro [1] [2].isEmpty()
			&& !tabuleiro [1] [2].equals("blank.png")
			&& tabuleiro [1] [0].equals(tabuleiro [1] [2]) ) {
			
			tabuleiro [1] [1] = this.escolha;
			
		} else if ( (tabuleiro [1] [2].isEmpty() || tabuleiro [1] [2].equals("blank.png"))
			&& !tabuleiro [1] [0].isEmpty()
			&& !tabuleiro [1] [0].equals("blank.png")
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& tabuleiro [1] [0].equals(tabuleiro [1] [1]) ) {
			
			tabuleiro [1] [2] = this.escolha;
			
		//Linha 03
		} else if ( (tabuleiro [2] [0].isEmpty() || tabuleiro [2] [0].equals("blank.png"))
			&& !tabuleiro [2] [1].isEmpty()
			&& !tabuleiro [2] [1].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [2] [1].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [2] [0] = this.escolha;
				
		} else if ( (tabuleiro [2] [1].isEmpty() || tabuleiro [2] [1].equals("blank.png"))
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [0].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [2] [0].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [2] [1] = this.escolha;
				
		} else if ( (tabuleiro [2] [2].isEmpty() || tabuleiro [2] [2].equals("blank.png"))
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [0].equals("blank.png")
			&& !tabuleiro [2] [1].isEmpty()
			&& !tabuleiro [2] [1].equals("blank.png")
			&& tabuleiro [2] [0].equals(tabuleiro [2] [1]) ) {
			
			tabuleiro [2] [2] = this.escolha;
			
		//Coluna 01
		} else if ( (tabuleiro [0] [0].isEmpty() || tabuleiro [0] [0].equals("blank.png"))
			&& !tabuleiro [1] [0].isEmpty()
			&& !tabuleiro [1] [0].equals("blank.png")
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [0].equals("blank.png")
			&& tabuleiro [1] [0].equals(tabuleiro [2] [0]) ) {
			
			tabuleiro [0] [0] = this.escolha;
				
		} else if ( (tabuleiro [1] [0].isEmpty() || tabuleiro [1] [0].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [0].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [2] [0]) ) {
			
			tabuleiro [1] [0] = this.escolha;
				
		} else if ( (tabuleiro [2] [0].isEmpty() || tabuleiro [2] [0].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [1] [0].isEmpty()
			&& !tabuleiro [1] [0].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [1] [0]) ) {
			
			tabuleiro [2] [0] = this.escolha;
				
		//Coluna 02
		} else if ( (tabuleiro [0] [1].isEmpty() || tabuleiro [0] [1].equals("blank.png"))
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& !tabuleiro [2] [1].isEmpty()
			&& !tabuleiro [2] [1].equals("blank.png")
			&& tabuleiro [1] [1].equals(tabuleiro [2] [1]) ) {
			
			tabuleiro [0] [1] = this.escolha;
							
		} else if ( (tabuleiro [1] [1].isEmpty() || tabuleiro [1] [1].equals("blank.png"))
			&& !tabuleiro [0] [1].isEmpty()
			&& !tabuleiro [0] [1].equals("blank.png")
			&& !tabuleiro [2] [1].isEmpty()
			&& !tabuleiro [2] [1].equals("blank.png")
			&& tabuleiro [0] [1].equals(tabuleiro [2] [1]) ) {
			
			tabuleiro [1] [1] = this.escolha;
								
		} else if ( (tabuleiro [2] [1].isEmpty() || tabuleiro [2] [1].equals("blank.png"))
			&& !tabuleiro [0] [1].isEmpty()
			&& !tabuleiro [0] [1].equals("blank.png")
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& tabuleiro [0] [1].equals(tabuleiro [1] [1]) ) {
			
			tabuleiro [2] [1] = this.escolha;
				
		//Coluna 03
		} else if ( (tabuleiro [0] [2].isEmpty() || tabuleiro [0] [2].equals("blank.png"))
			&& !tabuleiro [1] [2].isEmpty()
			&& !tabuleiro [1] [2].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [1] [2].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [0] [2] = this.escolha;
										
		} else if ( (tabuleiro [1] [2].isEmpty() || tabuleiro [1] [2].equals("blank.png"))
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [0] [2].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [1] [2] = this.escolha;
											
		} else if ( (tabuleiro [2] [2].isEmpty() || tabuleiro [2] [2].equals("blank.png"))
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& !tabuleiro [1] [2].isEmpty()
			&& !tabuleiro [1] [2].equals("blank.png")
			&& tabuleiro [0] [2].equals(tabuleiro [1] [2]) ) {
			
			tabuleiro [2] [2] = this.escolha;
		
		//Diagonal 01
		} else if ( (tabuleiro [0] [0].isEmpty() || tabuleiro [0] [0].equals("blank.png"))
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [1] [1].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [0] [0] = this.escolha;
			
		} else if ( (tabuleiro [1] [1].isEmpty() || tabuleiro [1] [1].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [2] [2].isEmpty()
			&& !tabuleiro [2] [2].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [2] [2]) ) {
			
			tabuleiro [1] [1] = this.escolha;
				
		} else if ( (tabuleiro [2] [2].isEmpty() || tabuleiro [2] [2].equals("blank.png"))
			&& !tabuleiro [0] [0].isEmpty()
			&& !tabuleiro [0] [0].equals("blank.png")
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& tabuleiro [0] [0].equals(tabuleiro [1] [1]) ) {
			
			tabuleiro [2] [2] = this.escolha;
		
		//Diagonal 02
		} else if ( (tabuleiro [0] [2].isEmpty() || tabuleiro [0] [2].equals("blank.png"))
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [1].equals("blank.png")
			&& tabuleiro [1] [1].equals(tabuleiro [2] [0]) ) {
			
			tabuleiro [0] [2] = this.escolha;
				
		} else if ( (tabuleiro [1] [1].isEmpty() || tabuleiro [1] [1].equals("blank.png"))
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& !tabuleiro [2] [0].isEmpty()
			&& !tabuleiro [2] [0].equals("blank.png")
			&& tabuleiro [0] [2].equals(tabuleiro [2] [0]) ) {
			
			tabuleiro [1] [1] = this.escolha;
					
		} else if ( (tabuleiro [2] [0].isEmpty() || tabuleiro [2] [0].equals("blank.png"))
			&& !tabuleiro [0] [2].isEmpty()
			&& !tabuleiro [0] [2].equals("blank.png")
			&& !tabuleiro [1] [1].isEmpty()
			&& !tabuleiro [1] [1].equals("blank.png")
			&& tabuleiro [0] [2].equals(tabuleiro [1] [1]) ) {
			
			tabuleiro [2] [0] = this.escolha;
		
		//Caso negativo oportunidade ou ameaca seja encontrada, realiza uma jogada aleatoria
		} else {
			tabuleiro = jogadaMedia(tabuleiro);
		}
		return tabuleiro;
	}
		
	//Setters e Getters
	public byte getDificuldade() {
		return dificuldade;
	}
	
	public void setDificuldade(byte dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getEscolha() {
		return escolha;
	}

	public void setEscolha(String escolha) {
		this.escolha = escolha;
	}
	
}