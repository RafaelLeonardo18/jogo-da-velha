package com.br.testes;

import org.junit.jupiter.api.Test;

import com.br.model.CPU;
import com.br.model.Jogador;
import com.br.model.Tabuleiro;

class Teste {

	@Test
	void testeTabuleiro() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogador jogador = new Jogador();
		CPU cpu = new CPU();
		jogador.setNome("Rafael");
		jogador.setEscolha("X");
		jogador.setPontuacao((byte) 0);
		cpu.setEscolha("O");
		cpu.setPontuacao((byte) 0);
		cpu.setDificuldade((byte) 3);
		System.out.println("Iniciar Jogo");
		while (true) {
			//Vez do Jogador
			tabuleiro.setTabuleiro(jogador.fazerJogada(tabuleiro.getTabuleiro()));
			//Exibe no console as marca��es do tabuleiro
			tabuleiro.exibeTabuleiro();
			if (tabuleiro.finalizarJogo(jogador.getEscolha())) {
				System.out.println("O jogador " + jogador.getNome() + " venceu a partida!");
				break;
			}
			//Verifica se um empate ocorreu ap�s a jogada do jogador
			if (tabuleiro.verificaEmpateTeste()){
				System.out.println("Empate, ningu�m conseguiu marcar ponto");
				break;
			}
			//Vez da m�quina
			tabuleiro.setTabuleiro(cpu.fazerJogada(tabuleiro.getTabuleiro()));
			//Exibe no console as marca��es do tabuleiro
			tabuleiro.exibeTabuleiro();
			if (tabuleiro.finalizarJogo(cpu.getEscolha())) {
				System.out.println("Oh n�o... A CPU venceu a partida! Tente novamente");
				break;
			}
			//Verifica se um empate ocorreu ap�s a jogada da m�quina
			if (tabuleiro.verificaEmpateTeste()) {
				System.out.println("Empate, ningu�m conseguiu marcar ponto");
				break;
			}
		}	
	}
	
	@Test
	public void doisJogadores() {
		Tabuleiro tabuleiro = new Tabuleiro();
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		jogador1.setEscolha("X");
		jogador2.setEscolha("O");
		while (true) {
			//Vez do jogador 1
			tabuleiro.setTabuleiro(jogador1.fazerJogada(tabuleiro.getTabuleiro()));
			tabuleiro.exibeTabuleiro();
			if (tabuleiro.finalizarJogo(jogador1.getEscolha())) {
				System.out.println("O jogador 1 venceu a partida!");
				break;
			}
			//Verifica se um empate ocorreu
			if (tabuleiro.verificaEmpateTeste()) {
				System.out.println("A partida terminou em empate!");
			}
			//Vez do jogador 2
			tabuleiro.setTabuleiro(jogador2.fazerJogada(tabuleiro.getTabuleiro()));
			tabuleiro.exibeTabuleiro();
			if (tabuleiro.finalizarJogo(jogador2.getEscolha())) {
				System.out.println("O jogador 2 venceu a partida!");
				break;
			}
			//Verifica se um empate ocorreu
			if (tabuleiro.verificaEmpateTeste()) {
				System.out.println("A partida terminou em empate!");
				break;
			}
		}
	}
}


