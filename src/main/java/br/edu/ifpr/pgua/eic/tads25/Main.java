package br.edu.ifpr.pgua.eic.tads25;

import br.edu.ifpr.pgua.eic.tads25.Controllers.IndexController;
import br.edu.ifpr.pgua.eic.tads25.Model.FabricaConexoes;
import br.edu.ifpr.pgua.eic.tads25.Utils.JavalinUtils;

public class Main {
    public static void main(String[] args) {
        var app = JavalinUtils.makeApp(7070);
        
        // ContatoDAO contatoDAO = new JDBCContatoDAO(FabricaConexoes.getInstance());

        // ContatoRepository repositorioContato = new ContatoRepository(contatoDAOXml);
        
        IndexController indexController = new IndexController();
        // CadastroController cadastroController = new CadastroController(repositorioContato);
        // ListaController listaController = new ListaController(repositorioContato);

        //rota
        app.get("/", indexController.get);

        // app.get("/add",cadastroController.get);
        // app.post("/add",cadastroController.post);
    }
}