package com.br.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.br.model.CPU;
import com.br.model.Jogador;
import com.br.model.Tabuleiro;

@ViewScoped
@ManagedBean (name = "gameBean")
public class GameBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Tabuleiro tabuleiro = null;
	private Jogador jogador1 = null;
	private Jogador jogador2 = null;
	private Jogador jogadorVez = null;
	private CPU cpu = null;
	private String nome1 = "";
	private String nome2 = "";
	private byte pontuacao01 = 0;
	private byte pontuacao02 = 0;
	private String img = "";
	private boolean encerrarJogo = false;
	
	//Metodos executados apos a instanciacao do Managed Bean
	@PostConstruct
	public void prepararJogo() {
		encerrarJogo = false;
		tabuleiro = new Tabuleiro("blank.png");
		//Processa os parametros recebidos do Menu Inicial e inicia o jogo conforme a escolha do usuario
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		byte modoJogo = (byte) session.getAttribute("modoJogo");
		//Caso tenha sido escolhido o modo de um jogador, instancia os objetos 'jogador' e 'cpu'
		if (modoJogo == 1) {
			jogador1 = new Jogador();
			cpu = new CPU();
			jogador1.setEscolha("X.png");
			byte dificuldade = (byte) session.getAttribute("dificuldade");
			cpu.setDificuldade(dificuldade);
			cpu.setEscolha("O.png");
			this.nome1 = "Jogador";
			this.nome2 = "CPU";
			this.img = "cpu.jpg";
			
		//Se for escolhido o modo de dois jogadores, prepara os jogadores e instancia um objeto para alternar a vez de cada jogador
		} else if (modoJogo == 2) {
			jogador1 = new Jogador();
			jogador2 = new Jogador();
			jogador1.setNome((String) session.getAttribute("nome01"));
			jogador2.setNome((String) session.getAttribute("nome02"));
			this.img = "user02.png";
			nome1 = jogador1.getNome();
			nome2 = jogador2.getNome();
			String marcadores = (String) session.getAttribute("marcadores");
			if (marcadores.equals("XO")) {
				jogador1.setEscolha("X.png");
				jogador2.setEscolha("O.png");
			} else if (marcadores.equals("OX")) {
				jogador1.setEscolha("O.png");
				jogador2.setEscolha("X.png");
			}
			jogadorVez = jogador1;
		}
	}
	
	//Metodo de marcar o tabuleiro, recebe o valor da linha e coluna da view para preencher a posicao do tabuleiro
	public void inserirValor() {
		if (!encerrarJogo) {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			HttpSession session = request.getSession();
			String txtLinha = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("linha");
			String txtColuna = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("coluna");
			byte linha = (byte) Integer.parseInt(txtLinha);
			byte coluna = (byte) Integer.parseInt(txtColuna);
			//Recebe o modo de jogo escolhido pelo usuario: 1) Um jogador e 2) Dois jogadores
			byte modoJogo = (byte) session.getAttribute("modoJogo");
			if (modoJogo == 1) {
				//Verifica se a posicao escolhida esta livre, se estiver preenche com o marcador do jogador
				if (tabuleiro.verificaCasaVazia(tabuleiro.getTabuleiro(), linha, coluna)) {
					tabuleiro.setTabuleiro(jogador1.fazerJogada(tabuleiro.getTabuleiro(), linha, coluna));
					//Valida se apos a escolha do jogador se ele venceu a partida
					if (tabuleiro.finalizarJogo(jogador1.getEscolha())) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vitória: ", "O jogador venceu a partida!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						pontuacao01 ++;
						encerrarJogo = true;
					//Caso nao tenha preenchido a linha, verifica se houve empate
					} else if (tabuleiro.verificaEmpateJogo()) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate: ", "Ninguém conseguiu vencer...!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						encerrarJogo = true;
					//Se o jogador nao venceu e nao houve empate, a maquina faz uma jogada
					} else {
						tabuleiro.setTabuleiro(cpu.fazerJogada(tabuleiro.getTabuleiro()));
						//Verifica se a maquina venceu o jogo
						if (tabuleiro.finalizarJogo(cpu.getEscolha())) {
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Derrota: ", "A CPU venceu a partida!");
							FacesContext.getCurrentInstance().addMessage(null, msg);
							pontuacao02 ++;
							encerrarJogo = true;
						//Senao verifica se ap�s a jogada da maquina houve empate
						} else if (tabuleiro.verificaEmpateJogo()) {
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate: ", "Ninguém conseguiu vencer...!");
							FacesContext.getCurrentInstance().addMessage(null, msg);
							encerrarJogo = true;
						}
					}
				//Porem se a posicao escolhida estiver preenchida, mostra um alerta para escolher outra posicao!
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção: ", "Esta posição já está ocupada");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
				
			} else if (modoJogo == 2) {
				//No modo dois jogadores, verifica antes de tudo se a posicao escolhida esta disponivel
				if (tabuleiro.verificaCasaVazia(tabuleiro.getTabuleiro(), linha, coluna)) {
					//Se a casa estiver disponivel insere o marcador do jogador da vez
					tabuleiro.setTabuleiro(jogadorVez.fazerJogada(tabuleiro.getTabuleiro(), linha, coluna));
					//Valida se o jogador 1 venceu a partida
					if (tabuleiro.finalizarJogo(jogadorVez.getEscolha()) && jogadorVez.equals(jogador1)) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vitória: ", "O jogador " + jogador1.getNome() + " venceu a partida!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						pontuacao01 ++;
						encerrarJogo = true;
					//Senao, valida se o jogador 2 venceu a partida
					} else if (tabuleiro.finalizarJogo(jogadorVez.getEscolha()) && jogadorVez.equals(jogador2)) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vitória: ", "O jogador " + jogador2.getNome() + " venceu a partida!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						pontuacao02 ++;
						encerrarJogo = true;
					//Por �ltimo verifica, se houve empate
					} else if (tabuleiro.verificaEmpateJogo()) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate: ", "Ninguém conseguiu vencer...!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						encerrarJogo = true;
					//Se ninguem venceu e nao houve empate, alterna a vez entre os jogadores
					} else {
						if (jogadorVez.equals(jogador1)) {
							jogadorVez = jogador2;
						} else {
							jogadorVez = jogador1;
						}
					}
				//Porem se a posicao escolhida estiver ocupada, dispara um aviso de que essa posicao nao pode ser escolhida
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção: ", "Esta posição já está ocupada");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
	}
	
	//Retorna para o menu principal e limpa todos os parametros salvos no contexto
	public String retornarMenuPrincipal() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.invalidate();;
		return "Index.jsf?faces-redirect=true";
	}

	//Setters e Getters
	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public String getNome1() {
		return nome1;
	}

	public void setNome1(String nome1) {
		this.nome1 = nome1;
	}

	public String getNome2() {
		return nome2;
	}

	public void setNome2(String nome2) {
		this.nome2 = nome2;
	}

	public Jogador getJogadorVez() {
		return jogadorVez;
	}

	public void setJogadorVez(Jogador jogadorVez) {
		this.jogadorVez = jogadorVez;
	}

	public boolean isEncerrarJogo() {
		return encerrarJogo;
	}

	public void setEncerrarJogo(boolean encerrarJogo) {
		this.encerrarJogo = encerrarJogo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public byte getPontuacao01() {
		return pontuacao01;
	}

	public void setPontuacao01(byte pontuacao01) {
		this.pontuacao01 = pontuacao01;
	}

	public byte getPontuacao02() {
		return pontuacao02;
	}

	public void setPontuacao02(byte pontuacao02) {
		this.pontuacao02 = pontuacao02;
	}
		
}