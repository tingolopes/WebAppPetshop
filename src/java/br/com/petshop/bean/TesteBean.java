package br.com.petshop.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TesteBean implements Serializable{

    private static Integer numeroDeInstancia = 0;

    public TesteBean() {
        numeroDeInstancia++;
    }
    
    public Integer getNumeroDeInstancia(){
        return numeroDeInstancia;
    }
    
    
}

// vai cair no simulado das materias

//o que é get, instancia
//o que é post, proccessa

//ViewScoped- 
//ApplicationScoped- nesse caso não fecha
//RequestScoped-
