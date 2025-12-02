package br.edu.ifpr.pgua.eic.tads25.Model.DAOs.JDBCs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.ifpr.pgua.eic.tads25.Model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads25.Model.Item;
import br.edu.ifpr.pgua.eic.tads25.Model.DAOs.ItemDAO;
import br.edu.ifpr.pgua.eic.tads25.Utils.DBUtils;

public class JDBCItemDAO implements ItemDAO {
    private FabricaConexoes fabrica;

    public JDBCItemDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Item cadastrarItem(Item item) {
        try (Connection con = fabrica.getConnection()) {
            String sql = "insert into pi_item (idColecao, nome, observacoes, qtdeCurtidas, criadoEm) values (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = fabrica.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, item.getIdColecao());
            pstmt.setString(2, item.getNome());
            pstmt.setString(3, item.getObservacoes());
            pstmt.setInt(4, item.getQtdeCurtidas());
            pstmt.setDate(5, item.getCriadoEm());

            int rowsReturned = pstmt.executeUpdate();

            if (rowsReturned == 1) {
                item.setIdItem(DBUtils.getLastId(pstmt));
                return item;
            } else {
                //erro
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean modificarItem(Item item) {
        try (Connection con = fabrica.getConnection()) {
            String sql = "UPDATE `pi_item` SET `nome` = ?, `observacao` = ?, WHERE `idItem` = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, item.getNome());
            pstmt.setString(2, item.getObservacoes());
            pstmt.setInt(4, item.getIdItem());

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
    public boolean deletarItem(int idItem) {
        try (Connection con = fabrica.getConnection()) {
            String sql = "DELETE FROM `pi_item` WHERE `idItem` = ?";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idItem);
            
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
    public Item detalharItem(int idItem) {
        try (Connection con = fabrica.getConnection()) {
            Item item = new Item();

            String sql = "select * from `pi_item` WHERE `idItem` = ?";
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, idItem);
            
            ResultSet resultado = pstmt.executeQuery();
            
            if (resultado.next()) {
                item.setIdItem(resultado.getInt("idItem"));
                item.setIdColecao(resultado.getInt("idColecao"));
                item.setNome(resultado.getString("nome"));
                item.setObservacoes(resultado.getString("observacoes"));
                item.setQtdeCurtidas(resultado.getInt("qtdeCurtidas"));
                item.setCriadoEm(resultado.getDate("criadoEm"));

                return item;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
