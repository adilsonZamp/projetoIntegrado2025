package br.edu.ifpr.pgua.eic.tads25.Model;

import java.sql.Date;

public class Item {
    private int idItem;
	private int idColecao;
	private String nome;
	private String observacoes;
	private int qtdeCurtidas;
	private Date criadoEm;

    public Item() {}
    public Item(int idItem, int idColecao, String nome, String observacoes, int qtdeCurtidas, Date criadoEm) {
        this.idItem = idItem;
        this.idColecao = idColecao;
        this.nome = nome;
        this.observacoes = observacoes;
        this.qtdeCurtidas = qtdeCurtidas;
        this.criadoEm = criadoEm;
    }
    public Item(int idColecao, String nome, String observacoes, int qtdeCurtidas, Date criadoEm) {
        this.idColecao = idColecao;
        this.nome = nome;
        this.observacoes = observacoes;
        this.qtdeCurtidas = qtdeCurtidas;
        this.criadoEm = criadoEm;
    }

    public int getIdItem() {
        return idItem;
    }
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public int getIdColecao() {
        return idColecao;
    }
    public void setIdColecao(int idColecao) {
        this.idColecao = idColecao;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public int getQtdeCurtidas() {
        return qtdeCurtidas;
    }
    public void setQtdeCurtidas(int qtdeCurtidas) {
        this.qtdeCurtidas = qtdeCurtidas;
    }
    public Date getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }
}
