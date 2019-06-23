package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.FormaDePagamento;
import br.com.petshop.model.ItemServico;
import br.com.petshop.model.Servico;
import br.com.petshop.service.FacesMessages;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped //vive enquanto estiver acessando essa tela. Se trocar de tela, a informação some
public class AgendaBean implements Serializable {

    private Agenda agenda = new Agenda();
    private Agenda agendaSelecionada;
    private ItemServico itemServico = new ItemServico();
    private Cliente cliente = new Cliente();
    private Animal animal = new Animal();
    private Servico servico = new Servico();
    private FacesMessages messages = new FacesMessages();

    public void salvar() {
        String operacao = "";
        if (agenda.getId() == null) {
            new DAO<>(Agenda.class).saveOrUpdate(agenda);
            operacao = "salva";
        } else {
            new DAO<>(Agenda.class).saveOrUpdate(agenda);
            operacao = "alterada";
        }
        messages.info("Agenda " + operacao + " com sucesso");
        PrimeFaces.current().ajax().update(
                Arrays.asList("frm:msgs", "frm:agenda-tabela")
        );
        agenda = new Agenda();
        agendaSelecionada = null;
        cliente = null;
    }

    public void adicionarServico() {
        itemServico.setServico(servico);
        itemServico.setAgenda(agenda);
        itemServico.setValor(servico.getValor());
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

    public List<Agenda> getAgendas() {
        List<Agenda> lista = new DAO<>(Agenda.class).listaTodos();
        Collections.sort(lista, Comparator.comparing(Agenda::getHoraAgendamento));
        Collections.sort(lista, Comparator.comparing(Agenda::getDataAgendamento));
        return lista;
    }

    public List<Cliente> getClientes() {
        List<Cliente> lista = new DAO(Cliente.class).listaTodos();
        Collections.sort(lista, Comparator.comparing(Cliente::getNome));
        return lista;
    }

    public List<Servico> getServicos() {
        List<Servico> lista = new DAO<>(Servico.class).listaTodos();
        Collections.sort(lista, Comparator.comparing(Servico::getDescricao));
        return lista;
    }

    public FormaDePagamento[] getFormaDePagamentos() {
        return FormaDePagamento.values();
    }

    public List<Servico> getItensDeServico() {
        List<Servico> listaSevicos = new ArrayList<>();
        for (ItemServico i : this.agenda.getItensDeServico()) {
            listaSevicos.add(new DAO<>(Servico.class).porId(i.getServico().getId()));
        }
        return listaSevicos;
    }

    public BigDecimal getSomaItensDeServico() {
        BigDecimal soma = BigDecimal.ZERO;
        for (ItemServico i : this.agenda.getItensDeServico()) {
            soma = soma.add(new DAO<>(Servico.class).porId(i.getServico().getId()).getValor());
        }
        return soma;
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
