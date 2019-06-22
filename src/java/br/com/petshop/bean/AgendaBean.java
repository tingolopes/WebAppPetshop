package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.FormaDePagamento;
import br.com.petshop.model.ItemServico;
import br.com.petshop.model.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped //vive enquanto estiver acessando essa tela. Se trocar de tela, a informação some
public class AgendaBean implements Serializable {

    private Agenda agenda = new Agenda();
    private Agenda agendaSelecionada = new Agenda();
    private ItemServico itemServico = new ItemServico();
    private Cliente cliente = new Cliente();
    private Animal animal = new Animal();
    private Servico servico = new Servico();
    
    public void adicionar(){
        new DAO<>(Agenda.class).salvar(agenda);
        agenda = new Agenda();
    }
    
    public void adicionarServico(){
        itemServico.setServico(servico);
        itemServico.setAgenda(agenda);
        agenda.getItensDeServico().add(itemServico);
        itemServico = new ItemServico();
        servico = new Servico();
    }
    
    public List<Animal> animaisDoCliente(Integer id) {
        List<Animal> animaisCliente = new ArrayList<>();
        for (Animal a : new AnimalBean().getAnimais()) {
            if (id == null) {
                return new AnimalBean().getAnimais();
            } else if (a.getProprietario().getId() == id) {
                animaisCliente.add(a);
            }
        }
        return animaisCliente;
    }
    
    public List<Servico> getByServico(CharSequence pesquisa) {
        pesquisa = pesquisa.toString().trim().toLowerCase();
        List<Servico> lista = new ArrayList<>();
        for (int i = 0; i < new ServicoBean().getServicos().size(); i++) {
            if (new ServicoBean().getServicos().get(i).getDescricao().toLowerCase().contains(pesquisa)) {
                lista.add(new ServicoBean().getServicos().get(i));
            }
        }
        return lista;
    }
    
    public List<Agenda> getAgendas() {
        List<Agenda> listaSevicos = new DAO<>(Agenda.class).listaTodos();
        return listaSevicos;
    }
    
    public List<Cliente> getClientes() {
        List<Cliente> listaSevicos = new DAO<>(Cliente.class).listaTodos();
        return listaSevicos;
    }
    
    public List<Servico> getServicos() {
        List<Servico> listaSevicos = new DAO<>(Servico.class).listaTodos();
        return listaSevicos;
    }
    
    public FormaDePagamento[] getFormaDePagamentos() {
        return FormaDePagamento.values();
    }
    
    public List<Servico> getItensDeServico() {
        List<Servico> listaSevicos = new ArrayList<>();
        for(ItemServico i : this.agenda.getItensDeServico()){
            listaSevicos.add(new DAO<>(Servico.class).porId(i.getServico().getId()));
        }
        return listaSevicos;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgendaSelecionada() {
        return agendaSelecionada;
    }

    public void setAgendaSelecionada(Agenda agendaSelecionada) {
        this.agendaSelecionada = agendaSelecionada;
    }
    
    

    public ItemServico getItemServico() {
        return itemServico;
    }

    public void setItemServico(ItemServico itemServico) {
        this.itemServico = itemServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    
}
