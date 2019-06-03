/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.petshop.webservices;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.Raca;
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
    @Path("animal/list")
    public String listAnimais() {
        Gson g = new Gson();
        return g.toJson(new DAO<>(Animal.class).listaTodos());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("raca/list")
    public String listRacas() {
        Gson g = new Gson();
        return g.toJson(new DAO<>(Raca.class).listaTodos());
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("animal/get/{id}")
    public String getAnimal(@PathParam("id") Integer id) {
        Animal a = new Animal();
        a.setId(id);
        
        DAO<Animal> dao = new DAO<>(Animal.class);
        a = dao.porId(id);
        
        Gson g = new Gson();
        return g.toJson(a);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("raca/get/{id}")
    public String getRaca(@PathParam("id") Integer id) {
        Raca r = new Raca();
        r.setId(id);
        
        DAO<Raca> dao = new DAO<>(Raca.class);
        r = dao.porId(id);
        
        Gson g = new Gson();
        return g.toJson(r);
    }
    
    
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("cliente/excluir/{id}")
    public String excluirCliente(@PathParam("id") Integer id) {
        Cliente c = new Cliente();
        c.setId(id);
        new DAO<>(Cliente.class).excluir(c);
        return "Registro excluído com sucesso!";
    }
    
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("animal/excluir/{id}")
    public String excluirAnimal(@PathParam("id") Integer id) {
        Animal a = new Animal();
        a.setId(id);
        new DAO<>(Animal.class).excluir(a);
        return "Registro excluído com sucesso!";
    }
    
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("raca/excluir/{id}")
    public String excluirRaca(@PathParam("id") Integer id) {
        Raca r = new Raca();
        r.setId(id);
        new DAO<>(Raca.class).excluir(r);
        return "Registro excluído com sucesso!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cliente/inserir")
    public String inserirCliente(String content){
        Gson g = new Gson();
        Cliente c = (Cliente) g.fromJson(content, Cliente.class);
        new DAO<>(Cliente.class).salvar(c);
        return "Registro salvo com sucesso!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("animal/inserir")
    public String inserirAnimal(String content){
        Gson g = new Gson();
        Animal a = (Animal) g.fromJson(content, Animal.class);
        new DAO<>(Animal.class).salvar(a);
        return "Registro salvo com sucesso!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("raca/inserir")
    public String inserirRaca(String content){
        Gson g = new Gson();
        Raca r = (Raca) g.fromJson(content, Raca.class);
        new DAO<>(Raca.class).salvar(r);
        return "Registro salvo com sucesso!";
    }
    
    /**
     * PUT method for updating or creating an instance of Petshop
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("cliente/alterar")
    public void alterarCliente(String content) {
        Gson g = new Gson();
        Cliente c = (Cliente) g.fromJson(content, Cliente.class);
        new DAO<>(Cliente.class).alterar(c);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("animal/alterar")
    public void alterarAnimal(String content) {
        Gson g = new Gson();
        Animal a = (Animal) g.fromJson(content, Animal.class);
        new DAO<>(Animal.class).alterar(a);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("raca/alterar")
    public void alterarRaca(String content) {
        Gson g = new Gson();
        Raca r = (Raca) g.fromJson(content, Raca.class);
        new DAO<>(Raca.class).alterar(r);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
}