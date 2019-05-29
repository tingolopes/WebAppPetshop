package br.com.petshop.bean;

import br.com.petshop.dao.DAO;
import br.com.petshop.dao.JPAUtil;
import br.com.petshop.model.Agenda;
import br.com.petshop.model.Animal;
import br.com.petshop.model.Servico;
import br.com.petshop.service.FacesMessages;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class AgendaBean {

    private EntityManager em = new JPAUtil().getEntityManager();
    private FacesMessages messages = new FacesMessages();

    private final DAO<Agenda> SERVICODAO = new DAO<>(Agenda.class);
    private Agenda agenda;
    private Agenda agendaSelecionado;
    
    public List<Servico> getServicos() {
        return new DAO(Servico.class).listaTodos();
    }
    
    public List<Animal> getAnimais(){
        return new DAO(Animal.class).listaTodos();
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

    public void salvar() {
        Integer id = this.agenda.getId();
        String operacao = "";
        if (id == 0) {
            SERVICODAO.salvar(this.agenda);
            operacao = "salvo";
        } else {
            SERVICODAO.alterar(this.agenda);
            operacao = "alterado";
        }
        messages.info("Serviço " + operacao + " com sucesso");
        PrimeFaces.current().ajax().update(
                Arrays.asList("frm:msgs", "frm:agenda-tabela")
        );
    }

    public void excluir() {
        SERVICODAO.excluir(this.agendaSelecionado);
        this.agendaSelecionado = null;

        getAgendas();
        messages.info("Serviço excluido com Sucesso!");
    }

    public List<Agenda> getAgendas() {
        List<Agenda> listaSevicos = SERVICODAO.listaTodos();
        return listaSevicos;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
