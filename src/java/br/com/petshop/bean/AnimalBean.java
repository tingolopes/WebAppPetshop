package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.Raca;
import br.com.petshop.service.FacesMessages;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class AnimalBean {

    private Animal animal;
    private final DAO<Animal> ANIMALDAO = new DAO<>(Animal.class);
    private Integer racaId;
    private Integer proprietarioId;
    private String sexo;
    private FacesMessages messages = new FacesMessages();
    private Animal animalSelecionado;

    public Animal getAnimalSelecionado() {
        return animalSelecionado;
    }

    public void setAnimalSelecionado(Animal animalSelecionado) {
        this.animalSelecionado = animalSelecionado;
    }

    public void prepararSalvar() {
        animal = new Animal();

    }

    public void salvar() {
        Integer id = this.animal.getId();
        String operacao = "";
        Raca raca = new DAO<>(Raca.class).porId(this.racaId);
        Cliente proprietario = new DAO<>(Cliente.class).porId(this.proprietarioId);
        this.animal.setRaca(raca);
        this.animal.setProprietario(proprietario);
        if (id == null) {
            ANIMALDAO.saveOrUpdate(this.animal);
            operacao = "salvo";
        } else {
            ANIMALDAO.saveOrUpdate(this.animal);
            operacao = "alterado";
        }
        messages.info("Animal " + operacao + " com sucesso");
        PrimeFaces.current().ajax().update(
                Arrays.asList("frm:msgs", "frm:animal-tabela")
        );
    }
    
    public void excluir() {
        ANIMALDAO.excluir(this.animalSelecionado);
        this.animalSelecionado = null;

        getAnimais();
        messages.info("Animal excluido com Sucesso!");
    }

    public List<Raca> getRacas() {
        return new DAO<Raca>(Raca.class).listaTodos();
    }

    public List<Cliente> getClientes() {
        return new DAO(Cliente.class).listaTodos();
    }

    public List<Animal> getAnimais() {
        List<Animal> listaAnimais = ANIMALDAO.listaTodos();
        return listaAnimais;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Integer getRacaId() {
        return racaId;
    }

    public void setRacaId(Integer racaId) {
        this.racaId = racaId;
    }

    public Integer getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(Integer proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
