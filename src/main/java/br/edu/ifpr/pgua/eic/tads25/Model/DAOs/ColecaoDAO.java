package br.edu.ifpr.pgua.eic.tads25.Model.DAOs;

import java.util.ArrayList;

import br.edu.ifpr.pgua.eic.tads25.Model.Colecao;
import br.edu.ifpr.pgua.eic.tads25.Model.Item;

public interface ColecaoDAO {
    public Colecao cadastrarColecao(Colecao colecao);
    public boolean modificarColecao(Colecao colecao);
    public boolean deletarColecao(int idColecao); // atualizar regras do banco para exclusão em cascata
    public ArrayList<Colecao> listarColecoes(int idUsuario);
    public ArrayList<Item> listarItensDeColecao(int idColecao);
}
