package br.edu.ifpr.pgua.eic.tads25.Model;

import java.sql.Blob;
import java.sql.Date;

public class Colecao {
    private int idColecao;
	private String nome;
	private String descricao;
	private Blob fotoCapa;
	private int qtdeFavoritos;
	private int qtdeCurtidas;
	private Date criadaEm;
    
    public Colecao(int idColecao, String nome, String descricao, Blob fotoCapa, int qtdeFavoritos, int qtdeCurtidas, Date criadaEm) {
        this.idColecao = idColecao;
        this.nome = nome;
        this.descricao = descricao;
        this.fotoCapa = fotoCapa;
        this.qtdeFavoritos = qtdeFavoritos;
        this.qtdeCurtidas = qtdeCurtidas;
        this.criadaEm = criadaEm;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Blob getFotoCapa() {
        return fotoCapa;
    }
    public void setFotoCapa(Blob fotoCapa) {
        this.fotoCapa = fotoCapa;
    }
    public int getQtdeFavoritos() {
        return qtdeFavoritos;
    }
    public void setQtdeFavoritos(int qtdeFavoritos) {
        this.qtdeFavoritos = qtdeFavoritos;
    }
    public int getQtdeCurtidas() {
        return qtdeCurtidas;
    }
    public void setQtdeCurtidas(int qtdeCurtidas) {
        this.qtdeCurtidas = qtdeCurtidas;
    }
    public Date getCriadaEm() {
        return criadaEm;
    }
    public void setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
    }
}