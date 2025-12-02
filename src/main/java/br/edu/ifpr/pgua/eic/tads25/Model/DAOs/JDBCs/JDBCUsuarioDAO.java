package br.edu.ifpr.pgua.eic.tads25.Model.DAOs.JDBCs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpr.pgua.eic.tads25.Model.Colecao;
import br.edu.ifpr.pgua.eic.tads25.Model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads25.Model.Usuario;
import br.edu.ifpr.pgua.eic.tads25.Model.DAOs.UsuarioDAO;
import br.edu.ifpr.pgua.eic.tads25.Utils.DBUtils;

public class JDBCUsuarioDAO implements UsuarioDAO{
    private FabricaConexoes fabricaConexoes;

    public JDBCUsuarioDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }
    
    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "INSERT INTO `pi_usuario` (`nome`, `email`, `fotoPerfil`, `membroDesde`) VALUES (?, ?, ?, ?);";
            PreparedStatement pstmt = fabricaConexoes.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setBlob(3, usuario.getFotoPerfil());
            pstmt.setDate(4, usuario.getMembroDesde());

            int rowsReturned = pstmt.executeUpdate();

            if (rowsReturned == 1) {
                int id = DBUtils.getLastId(pstmt);
                usuario.setIdUsuario(id);
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "UPDATE `pi_usuario` SET `nome` = ?, `email` = ?, `fotoPerfil` = ? WHERE `idUsuario` = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setBlob(3, usuario.getFotoPerfil());
            pstmt.setInt(4, usuario.getIdUsuario());

            int rowsReturned = pstmt.executeUpdate();

            if (rowsReturned == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletarUsuario(int idUsuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "DELETE FROM `pi_usuario` WHERE `idUsuario` = ?";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idUsuario);
            
            int rowsReturned = pstmt.executeUpdate();
            
            if (rowsReturned == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario detalharUsuario(int idUsuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            Usuario usuario = new Usuario();

            String sql = "select * from pi_usuario where idUsuario = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            ResultSet resultados = pstmt.executeQuery();
            
            if (resultados.next()) {
                usuario.setIdUsuario(idUsuario);
                usuario.setNome(resultados.getString("nome"));
                usuario.setEmail(resultados.getString("email"));
                usuario.setFotoPerfil(resultados.getBlob("fotoPerfil"));
                usuario.setMembroDesde(resultados.getDate("membroDesde"));
            } else {
                return null;
            }

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Colecao> listarColecoesDeUsuario(int idUsuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            ArrayList<Colecao> listaColecoes = new ArrayList<>();

            String sql = "SELECT c.* FROM pi_usuario u, pi_usuario_dono_colecao udc, pi_colecao c WHERE u.idUsuario = ? AND u.idUsuario = udc.idUsuario AND c.idColecao = udc.idColecao;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            ResultSet resultados = pstmt.executeQuery();
            
            while (resultados.next()) {
                Colecao colecao = new Colecao();

                colecao.setIdColecao(resultados.getInt("idColecao"));
                colecao.setNome(resultados.getString("nome"));
                colecao.setDescricao(resultados.getString("descricao"));
                colecao.setFotoCapa(resultados.getBlob("fotoCapa"));
                colecao.setQtdeFavoritos(resultados.getInt("qtdeFavoritos"));
                colecao.setQtdeCurtidas(resultados.getInt("qtdeCurtidas"));
                colecao.setCriadaEm(resultados.getDate("criadaEm"));

                listaColecoes.add(colecao);
            }

            return listaColecoes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
