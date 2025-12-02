package br.edu.ifpr.pgua.eic.tads25.Model.Repositories;

import java.util.ArrayList;

import br.edu.ifpr.pgua.eic.tads25.Model.Colecao;
import br.edu.ifpr.pgua.eic.tads25.Model.Usuario;
import br.edu.ifpr.pgua.eic.tads25.Model.DAOs.UsuarioDAO;

public class UsuarioRepository {
    private UsuarioDAO usuarioDAO;

    public UsuarioRepository(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuarioDAO.cadastrarUsuario(usuario) != null) {
            return true;
        }
        return false;
    }
    public boolean modificarUsuario(Usuario usuario) {
        return usuarioDAO.modificarUsuario(usuario);
    }
    public boolean deletarUsuario(int idUsuario) {
        return usuarioDAO.deletarUsuario(idUsuario);
    }
    public Usuario detalharUsuario(int idUsuario) {
        return usuarioDAO.detalharUsuario(idUsuario);
    }
    public ArrayList<Colecao> listarColecoesDeUsuario(int idUsuario) {
        return usuarioDAO.listarColecoesDeUsuario(idUsuario);
    }
}
