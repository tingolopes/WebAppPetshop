package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.service.FacesMessages;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class AgendaBean {

    private Agenda agenda;
    private final DAO<Agenda> AGENDADAO = new DAO<>(Agenda.class);
    private Integer agendaId;
    private String dataAgendamento;
    private Integer horaAgendamento;
    private Integer proprietarioId;

    public Integer getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(Integer proprietarioId) {
        this.proprietarioId = proprietarioId;
    }
    private String status;
    private FacesMessages messages = new FacesMessages();
    private Agenda agendaSelecionada;

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Integer getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(Integer horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FacesMessages getMessages() {
        return messages;
    }

    public void setMessages(FacesMessages messages) {
        this.messages = messages;
    }

    public Agenda getAgendaSelecionada() {
        return agendaSelecionada;
    }

    public void setAgendaSelecionada(Agenda agendaSelecionada) {
        this.agendaSelecionada = agendaSelecionada;
    }

    public void prepararSalvar() {
        agenda = new Agenda();
    }

    public void salvar() {
        Integer id = this.agenda.getId();
        String operacao = "";
        Animal animal = new DAO<>(Animal.class).porId(this.agendaId);
        this.agenda.setAnimal(animal);
        if (id == null) {
            AGENDADAO.salvar(this.agenda);
            operacao = "salvo";
        } else {
            AGENDADAO.alterar(this.agenda);
            operacao = "alterado";
        }
        messages.info("Agenda " + operacao + " com sucesso");
        PrimeFaces.current().ajax().update(
                Arrays.asList("frm:msgs", "frm:agenda-tabela")
        );
    }
    
    public void excluir() {
        AGENDADAO.excluir(this.agendaSelecionada);
        this.agendaSelecionada = null;

        getAgenda();
        messages.info("Agenda excluido com Sucesso!");
    }
    
    public List<Cliente> getClientes() {
        return new DAO(Cliente.class).listaTodos();
    }
    
    public List<Agenda> getAgendas(){
        return new DAO(Agenda.class).listaTodos();
    }
}
