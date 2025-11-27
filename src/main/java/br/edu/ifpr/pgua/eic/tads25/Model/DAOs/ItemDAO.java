package br.edu.ifpr.pgua.eic.tads25.Model.DAOs;

import br.edu.ifpr.pgua.eic.tads25.Model.Item;

public interface ItemDAO {
    public Item cadastrarItem(Item item);
    public boolean modificarItem(Item item);
    public boolean deletarItem(int idItem);
    public Item listarItem(int idItem);
}
