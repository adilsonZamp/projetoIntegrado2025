package br.edu.ifpr.pgua.eic.tads25.Model;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
	private String nome;
	private String email;
	private Blob fotoPerfil;
	private Date membroDesde;
    private ArrayList<Colecao> colecoes;

    public Usuario(int idUsuario, String nome, String email, Blob fotoPerfil, Date membroDesde, ArrayList<Colecao> colecoes) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
        this.membroDesde = membroDesde;
        this.colecoes = colecoes;
    }
    public Usuario(String nome, String email, Blob fotoPerfil, Date membroDesde, ArrayList<Colecao> colecoes) {
        this.nome = nome;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
        this.membroDesde = membroDesde;
        this.colecoes = colecoes;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Blob getFotoPerfil() {
        return fotoPerfil;
    }
    public void setFotoPerfil(Blob fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public Date getMembroDesde() {
        return membroDesde;
    }
    public void setMembroDesde(Date membroDesde) {
        this.membroDesde = membroDesde;
    }
    public ArrayList<Colecao> getColecoes() {
        return colecoes;
    }
    public void setColecoes(ArrayList<Colecao> colecoes) {
        this.colecoes = colecoes;
    }
}