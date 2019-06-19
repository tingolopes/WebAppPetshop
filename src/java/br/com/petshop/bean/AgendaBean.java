package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.dao.JPAUtil;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Cliente;
import br.com.petshop.model.FormaDePagamento;
import br.com.petshop.model.Servico;
import br.com.petshop.service.FacesMessages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

@ManagedBean
@RequestScoped
public class AgendaBean implements Serializable{

    private EntityManager em = new JPAUtil().getEntityManager();
    private FacesMessages messages = new FacesMessages();

    private DAO<Agenda> agendaDao = new DAO<>(Agenda.class);
    private Agenda agenda = new Agenda();
    private Agenda agendaSelecionado;
    private Integer proprietarioId;
    private Integer animalId;
    
    public void salvar(){
        this.agenda = agendaDao.salvarComRetorno(this.agenda);
    }
    
    public FormaDePagamento[] getFormaDePagamentos(){
        return FormaDePagamento.values();
    }
    
    public List<Servico> getServicos() {
        return new DAO(Servico.class).listaTodos();
    }
    
    public List<Animal> getAnimais(){
        return new DAO(Animal.class).listaTodos();
    }
    
    public List<Cliente> getClientes(){
        return new DAO(Cliente.class).listaTodos();
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
    
    public Integer getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(Integer proprietarioId) {
        this.proprietarioId = proprietarioId;
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

    public void excluir() {
        agendaDao.excluir(this.agendaSelecionado);
        this.agendaSelecionado = null;

        getAgendas();
        messages.info("Serviço excluido com Sucesso!");
    }

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
}
