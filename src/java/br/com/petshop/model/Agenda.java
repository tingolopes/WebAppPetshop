package br.com.petshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agenda implements Serializable, EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_agendamento")
    private Date dataAgendamento;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_agendamento")
    private Date horaAgendamento;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<ItemServico> itensDeServico;

    private String status;

    public Agenda() {
        this.itensDeServico = new ArrayList<>(); 
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_de_pagamento")
    private FormaDePagamento formaDePagamento;

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataagendamento() {
        return dataAgendamento;
    }

    public void setDataagendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getHoraagendamento() {
        return horaAgendamento;
    }

    public void setHoraagendamento(Date horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(Date horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public List<ItemServico> getItensDeServico() {
        return itensDeServico;
    }

    public void setItensDeServico(List<ItemServico> itensDeServico) {
        this.itensDeServico = itensDeServico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "id=" + id + '}';
    }
}
