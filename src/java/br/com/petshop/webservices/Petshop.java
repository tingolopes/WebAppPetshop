/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.webservices;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Cliente;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tingo
 */
@Path("petshop")
public class Petshop {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Petshop
     */
    public Petshop() {
    }

    /**
     * Retrieves representation of an instance of br.com.petshop.webservices.Petshop
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cliente/list")
    public String listClientes() {
        Gson g = new Gson();
        return g.toJson(new DAO<>(Cliente.class).listaTodos());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cliente/get/{id}")
    public String getCliente(@PathParam("id") Integer id) {
        Cliente c = new Cliente();
        c.setId(id);
        
        DAO<Cliente> dao = new DAO<>(Cliente.class);
        c = dao.porId(id);
        
        Gson g = new Gson();
        return g.toJson(c);
    }
    
    
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("cliente/excluir/{id}")
    public String excluir(@PathParam("id") Integer id) {
        Cliente c = new Cliente();
        c.setId(id);
        new DAO<>(Cliente.class).excluir(c);
        return "Registro excluído com sucesso!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cliente/inserir")
    public String inserir(String content){
        Gson g = new Gson();
        Cliente c = (Cliente) g.fromJson(content, Cliente.class);
        new DAO<>(Cliente.class).salvar(c);
        return "Registro salvo com sucesso!";
    }
    
    /**
     * PUT method for updating or creating an instance of Petshop
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cliente/alterar")
    public void alterar(String content) {
        Gson g = new Gson();
        Cliente c = (Cliente) g.fromJson(content, Cliente.class);
        new DAO<>(Cliente.class).alterar(c);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
}