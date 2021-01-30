package com.br.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean (name = "inicioBean")
public class InicioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Define o modo de jogo que o usuário selecionar: 1) Um jogador e 2) Dois jogadores
	private byte modoJogo;
	
	//Caso seja selecionado o modo de um jogador, define a dificuldade da máquina
	private byte dificuldade;
	
	//Caso seja selecionado o modo de dois jogadores, pede-se para inserir os nomes dos jogades e escolher os marcadores
	private String nome01 = "";
	private String nome02 = "";
	private String marcadores = "";
	
	//Valor da visibilidade dos botões e texto depois de selecionar as opções desejadas
	private boolean renderizarOpcaoInicial = true;
	private boolean renderizarOpcaoUm = false;
	private boolean renderizarOpcaoDois = false;
	private boolean renderizarBotaoVoltar = false;

	
	//Modo de um jogador
	public void modoJogo1() {
		this.modoJogo = 1;
		this.renderizarOpcaoInicial = false;
		this.renderizarOpcaoUm = true;
		this.renderizarBotaoVoltar = true;
	}
	
	//Modo de dois jogadores
	public void modoJogo2() {
		this.modoJogo = 2;
		this.renderizarOpcaoInicial = false;
		this.renderizarOpcaoDois = true;
		this.renderizarBotaoVoltar = true;
	}
	
	//Iniciar Jogo
	public String iniciarJogo() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("modoJogo", this.modoJogo);
		if (this.modoJogo == 1) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("dificuldade", this.dificuldade);
		} else if (this.modoJogo == 2) {
			this.marcadores = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("doisJogadores");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nome01", this.nome01);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nome02", this.nome02);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("marcadores", this.marcadores);
		}
		return "Jogo.jsf?faces-redirect=true";
	}
	
	//Voltar ao menu principal
	public void voltar() {
		this.modoJogo = 0;
		this.renderizarOpcaoInicial = true;
		this.renderizarOpcaoUm = false;
		this.renderizarOpcaoDois = false;
		this.renderizarBotaoVoltar = false;
		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().clear();
	}
	
	//Setters e Getters
	public byte getModoJogo() {
		return modoJogo;
	}

	public void setModoJogo(byte modoJogo) {
		this.modoJogo = modoJogo;
	}

	public boolean isRenderizarOpcaoInicial() {
		return renderizarOpcaoInicial;
	}

	public void setRenderizarOpcaoInicial(boolean renderizarOpcaoInicial) {
		this.renderizarOpcaoInicial = renderizarOpcaoInicial;
	}

	public boolean isRenderizarOpcaoUm() {
		return renderizarOpcaoUm;
	}

	public void setRenderizarOpcaoUm(boolean renderizarOpcaoUm) {
		this.renderizarOpcaoUm = renderizarOpcaoUm;
	}

	public boolean isRenderizarOpcaoDois() {
		return renderizarOpcaoDois;
	}

	public void setRenderizarOpcaoDois(boolean renderizarOpcaoDois) {
		this.renderizarOpcaoDois = renderizarOpcaoDois;
	}

	public boolean isRenderizarBotaoVoltar() {
		return renderizarBotaoVoltar;
	}

	public void setRenderizarBotaoVoltar(boolean renderizarBotaoVoltar) {
		this.renderizarBotaoVoltar = renderizarBotaoVoltar;
	}

	public byte getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(byte dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getNome01() {
		return nome01;
	}

	public void setNome01(String nome01) {
		this.nome01 = nome01;
	}

	public String getNome02() {
		return nome02;
	}

	public void setNome02(String nome02) {
		this.nome02 = nome02;
	}

	public String getMarcadores() {
		return marcadores;
	}

	public void setMarcadores(String marcadores) {
		this.marcadores = marcadores;
	}

}