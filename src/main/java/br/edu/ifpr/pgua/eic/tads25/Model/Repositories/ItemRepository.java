package br.edu.ifpr.pgua.eic.tads25.Model.Repositories;

import br.edu.ifpr.pgua.eic.tads25.Model.DAOs.ItemDAO;

public class ItemRepository {
    /*
    public Item cadastrarItem(Item item);
    public boolean modificarItem(Item item);
    public boolean deletarItem(int idItem);
    public Item detalharItem(int idItem);
    */
   private ItemDAO itemDAO;

   public ItemRepository(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
   }

   
}
