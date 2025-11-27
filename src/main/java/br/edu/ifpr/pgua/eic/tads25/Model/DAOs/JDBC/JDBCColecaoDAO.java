package br.edu.ifpr.pgua.eic.tads25.Model.DAOs.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpr.pgua.eic.tads25.Model.Colecao;
import br.edu.ifpr.pgua.eic.tads25.Model.DAOs.ColecaoDAO;
import br.edu.ifpr.pgua.eic.tads25.Model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads25.Model.Item;
import br.edu.ifpr.pgua.eic.tads25.Utils.DBUtils;

public class JDBCColecaoDAO implements ColecaoDAO {

    private FabricaConexoes fabricaConexoes;

    public JDBCColecaoDAO(FabricaConexoes fabricaConexoes) {
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Colecao cadastrarColecao(Colecao colecao) {
        String sql = "INSERT INTO `pi_colecao` (`nome`, `descricao`, `fotoCapa`, `qtdeFavoritos`, `qtdeCurtidas`, `criadaEm`) VALUES (?, ?, ?, 0, 0, ?);";

        try (Connection con = fabricaConexoes.getConnection()) {
            PreparedStatement pstmt = fabricaConexoes.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, colecao.getNome());
            pstmt.setString(2, colecao.getDescricao());
            pstmt.setBlob(3, colecao.getFotoCapa());
            pstmt.setDate(4, colecao.getCriadaEm());

            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                int id = DBUtils.getLastId(pstmt);
                colecao.setIdColecao(id);

                return colecao;
            } else {
                //erro ao inserir
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //erro ao conectar
            return null;
        }
    }

    @Override
    public boolean modificarColecao(Colecao colecao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarColecao'");
    }

    @Override
    public boolean deletarColecao(int idColecao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarColecao'");
    }

    @Override
    public ArrayList<Colecao> listarColecoes(int idUsuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarColecoes'");
    }

    @Override
    public ArrayList<Item> listarItensDeColecao(int idColecao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarItensDeColecao'");
    }
    
}
