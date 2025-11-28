package br.edu.ifpr.pgua.eic.tads25.Controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class IndexController {
    
    public Handler get = (Context c) -> {
        c.render("index.html");
    };
}
