package com.br.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import com.br.model.CPU;
import com.br.model.Jogador;
import com.br.model.Tabuleiro;

class Teste {

	@Test
	void testeTabuleiro() {
		Tabuleiro tabuleiro = new Tabuleiro();
		boolean inserir = false;
		Jogador jogador = new Jogador();
		CPU cpu = new CPU();
		jogador.setNome("Luiz");
		jogador.setEscolha("X");
		jogador.setPontuacao(0);
		cpu.setEscolha("O");
		cpu.setPontuacao(0);
		System.out.println("Iniciar Jogo");
		for (int i = 0; i <= 9; i++) {
			//Vez do Jogador0
			while (inserir == false) {
				int linha = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma posição da linha do tabuleiro")) - 1;
				int coluna = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma posição da coluna do tabuleiro")) - 1;
				if (tabuleiro.getTabuleiro()[linha] [coluna] == null || tabuleiro.getTabuleiro()[linha] [coluna].isEmpty()) {
					tabuleiro.inserirEscolha(jogador.getEscolha(), linha, coluna);
					System.out.println("Você escolheu a posição " + (linha + 1) + "X" + (coluna + 1) + " para marcar um " + jogador.getEscolha());
					inserir = true;
					if (tabuleiro.finalizarJogo(jogador.getEscolha(), tabuleiro.getTabuleiro())) {
						System.out.println("O Jogador " + jogador.getNome() + " venceu a partida");
						break;
					} 
				} else {
					System.out.println("Essa posição já está ocupada!");
				}
			}
			inserir = false;
			//Vez da máquina
			i++;
			tabuleiro.setTabuleiro(cpu.fazerJogada(tabuleiro.getTabuleiro()));
			if (tabuleiro.finalizarJogo(cpu.getEscolha(), tabuleiro.getTabuleiro())) {
				System.out.println("Oh não... A CPU venceu a partida! Tente novamente");
				break;
			} 
		}
	}

}
