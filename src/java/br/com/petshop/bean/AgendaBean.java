package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.dao.JPAUtil;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.FormaDePagamento;
import br.com.petshop.model.ItemServico;
import br.com.petshop.model.Servico;
import br.com.petshop.service.FacesMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean
@RequestScoped
public class AgendaBean implements Serializable {

    private EntityManager em = new JPAUtil().getEntityManager();
    private FacesMessages messages = new FacesMessages();
    private DAO<Agenda> agendaDao = new DAO<>(Agenda.class);
    private Agenda agenda = new Agenda();
    private Servico servico = new Servico();
    private Integer agendaId;
    private Integer servicoId;    
    private Agenda agendaSelecionado;
    private Integer clienteId;
    private Integer animalId;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public void salvar() {
        this.agenda = agendaDao.salvarComRetorno(this.agenda);
    }

    public FormaDePagamento[] getFormaDePagamentos() {
        return FormaDePagamento.values();
    }

    public List<Servico> getServicos() {
        return new DAO(Servico.class).listaTodos();
    }

    public List<Animal> getAnimais() {
        return new DAO(Animal.class).listaTodos();
    }

    public List<Cliente> getClientes() {
        return new DAO(Cliente.class).listaTodos();
    }

    public List<Animal> animaisDoCliente(Integer id) {
        List<Animal> animaisCliente = new ArrayList<>();
        for (Animal a : getAnimais()) {
            if (id == null) {
                return getAnimais();
            } else if (a.getProprietario().getId() == id) {
                animaisCliente.add(a);
            }
        }
        return animaisCliente;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public Agenda getAgendaSelecionado() {
        return agendaSelecionado;
    }

    public void setAgendaSelecionado(Agenda agendaSelecionado) {
        this.agendaSelecionado = agendaSelecionado;
    }

    public void prepararSalvar() {
        agenda = new Agenda();
    }

//    public void salvar() {
//        Integer id = this.agenda.getId();
//        String operacao = "";
//        if (id == 0) {
//            agendaDao.salvar(this.agenda);
//            operacao = "salvo";
//        } else {
//            agendaDao.alterar(this.agenda);
//            operacao = "alterado";
//        }
//        messages.info("Serviço " + operacao + " com sucesso");
//        PrimeFaces.current().ajax().update(
//                Arrays.asList("frm:msgs", "frm:agenda-tabela")
//        );
//    }
    
    public List<Agenda> getAgendas() {
        List<Agenda> listaSevicos = agendaDao.listaTodos();
        return listaSevicos;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    public List getByServico(CharSequence pesquisa) {
        pesquisa = pesquisa.toString().trim().toLowerCase();
        List<Servico> lista = new ArrayList<>();
        for (int i = 0; i < getServicos().size(); i++) {
            if (getServicos().get(i).getDescricao().toLowerCase().contains(pesquisa)) {
                lista.add(getServicos().get(i));
            }
        }
        return lista;
    }

    public void addItemServico() {
        Agenda a = getAgenda();
        if(getAgenda().getId() == null){
            a = new DAO<>(Agenda.class).salvarComRetorno(agenda);
        }
        
        ItemServico itemServico = new ItemServico();
        Servico s = new DAO<>(Servico.class).porId(this.servicoId);
        itemServico.setAgenda(a);
        itemServico.setServico(s);
        itemServico.setValor(s.getValor());
        new DAO<>(ItemServico.class).salvar(itemServico);
        PrimeFaces.current().ajax().update(Arrays.asList("frm:servico-tabela"));
    }
    
    public List listarItensServico(){
        List<ItemServico> lista = new ArrayList<>();
        for(ItemServico i : new DAO<>(ItemServico.class).listaTodos()){
            if(i.getAgenda().getId() == agenda.getId()){
                lista.add(i);
            }
        }
        return lista;
    }
    
    public void selectServico(Integer id){
        servico = new DAO<>(Servico.class).porId(id);
    }

//    WIZARD A PARTIR DAQUI
    private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
//WIZARD TERMINA AQUI    

}
