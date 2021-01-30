package com.br.model;

import javax.swing.JOptionPane;

import com.br.interfacejogo.InterfaceJogo;

/**************************************************************************************************
 * Descri��o: classe que representa os jogadores reais 
 * Data: 15/01/2021
 * ***********************************************************************************************/

public class Jogador implements InterfaceJogo {
	
	//Atributos da classe
	private String nome;
	private String escolha;
	private byte pontuacao;
	
	//Construtor da classe
	public Jogador() {
		this.nome = "";
		this.escolha = "";
	}
	
	//M�todo de teste que realiza a Jogada do jogador, inserindo seu marcador na posi��o escolhida
	public String [] [] fazerJogada(String [] [] tabuleiro){
		boolean inserir = false;
		byte linha = 0;
		byte coluna = 0;
		while (inserir == false) {
			//Valida��o da entrada de dados, s� aceita os valores 1, 2 ou 3
			while (inserir == false) {
				String txtLinha = JOptionPane.showInputDialog("Escolha uma linha do tabuleiro: 1, 2 ou 3");
				if (txtLinha.equals("1") || txtLinha.equals("2") || txtLinha.equals("3")) {
					linha = (byte) (Integer.parseInt(txtLinha) - 1);
					inserir = true;
				} else {
					System.out.println("Valor inv�lido, insira novamente");
				}
			}
			inserir = false;
			//Depois de inserido o valor da linha valida o valor da coluna
			while (inserir == false) {
				String txtColuna = JOptionPane.showInputDialog("Escolha uma coluna do tabuleiro: 1, 2 ou 3");
				if (txtColuna.equals("1") || txtColuna.equals("2") || txtColuna.equals("3")) {
					coluna = (byte) (Integer.parseInt(txtColuna) - 1);
					inserir = true;
				} else {
					System.out.println("Valor inv�lido, insira novamente");
				}
			}
			inserir = false;
			//Ap�s validados os valores digitados verifica-se se a posi��o escolhida est� dispon�vel para marca��o
			if (tabuleiro[linha] [coluna].isEmpty()) {
				tabuleiro[linha] [coluna] = this.escolha;
				System.out.println("Voc� escolheu a posi��o " + (linha + 1) + "X" + (coluna + 1) + " para marcar um " + this.escolha);
				inserir = true;
			} else {
				System.out.println("Essa posi��o j� est� ocupada!");
			}
		}
		return tabuleiro;
	}
	
	
	//M�todo usado com interface web que realiza a Jogada do jogador, inserindo seu marcador na posi��o escolhida da tela, recebendo o valor da posi��o por par�metro
	public String [] [] fazerJogada(String [] [] tabuleiro, byte linha, byte coluna){
		tabuleiro [linha] [coluna] = this.escolha;
		return tabuleiro;
	}	
	
	//Setters e Getters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEscolha() {
		return escolha;
	}
	public void setEscolha(String escolha) {
		this.escolha = escolha;
	}
	public byte getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(byte pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((escolha == null) ? 0 : escolha.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + pontuacao;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (escolha == null) {
			if (other.escolha != null)
				return false;
		} else if (!escolha.equals(other.escolha))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontuacao != other.pontuacao)
			return false;
		return true;
	}

}