package br.edu.ifpr.pgua.eic.tads25.Model.DAOs;

import br.edu.ifpr.pgua.eic.tads25.Model.Usuario;

public interface UsuarioDAO {
    public Usuario cadastrarUsuario(Usuario usuario);
    public boolean modificarUsuario(Usuario usuario);
    public boolean deletarUsuario(int idUsuario);
    public Usuario listarUsuario(int idUsuario);
}
