package br.edu.ifpr.pgua.eic.tads25.Model.DAOs.JDBCs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "INSERT INTO `pi_colecao` (`nome`, `descricao`, `fotoCapa`, `qtdeFavoritos`, `qtdeCurtidas`, `criadaEm`) VALUES (?, ?, ?, 0, 0, ?);";
            PreparedStatement pstmt = fabricaConexoes.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, colecao.getNome());
            pstmt.setString(2, colecao.getDescricao());
            pstmt.setBlob(3, colecao.getFotoCapa());
            pstmt.setDate(4, colecao.getCriadaEm());

            int rowsReturned = pstmt.executeUpdate();

            if (rowsReturned == 1) {
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
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "UPDATE `pi_colecao` SET `nome` = ?, `descricao` = ?, `fotoCapa` = ? WHERE `idColecao` = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, colecao.getNome());
            pstmt.setString(2, colecao.getDescricao());
            pstmt.setBlob(3, colecao.getFotoCapa());
            pstmt.setInt(4, colecao.getIdColecao());

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
    public boolean deletarColecao(int idColecao) {
        try (Connection con = fabricaConexoes.getConnection()) {
            String sql = "DELETE FROM `pi_colecao` WHERE `idColecao` = ?";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idColecao);
            
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
    public ArrayList<Colecao> listarColecoes(int idUsuario) {
        try (Connection con = fabricaConexoes.getConnection()) {
            ArrayList<Colecao> listaColecoes = new ArrayList<>();

            String sql = "select c.idColecao, c.nome from pi_colecao c, pi_usuario u, pi_usuario_dono_colecao udc" + 
                        " where c.idColecao = udc.idColecao" + 
                        " and u.idUsuario = udc.idUsuario" + 
                        " and u.idUsuario = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            ResultSet resultados = pstmt.executeQuery();
            
            while (resultados.next()) {
                Colecao colecao = new Colecao();
                colecao.setIdColecao(resultados.getInt("idUsuario"));
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

    @Override
    public ArrayList<Item> listarItensDeColecao(int idColecao) {
        try (Connection con = fabricaConexoes.getConnection()) {
            ArrayList<Item> listaItens = new ArrayList<>();

            String sql = "select i. * from pi_colecao c, pi_item i WHERE i.idColecao = c.idColecao and c.idColecao = ?"; // responsabilidade do JDBCItem
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idColecao);
            
            ResultSet resultados = pstmt.executeQuery();
            
            while (resultados.next()) {
                Item item = new Item();
                
                item.setIdItem(resultados.getInt("idItem"));
                item.setIdColecao(resultados.getInt("idColecao"));
                item.setNome(resultados.getString("nome"));
                item.setObservacoes(resultados.getString("observacoes"));
                item.setQtdeCurtidas(resultados.getInt("qtdeCurtidas"));
                item.setCriadoEm(resultados.getDate("criadoEm"));

                listaItens.add(item);
            }

            return listaItens;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
