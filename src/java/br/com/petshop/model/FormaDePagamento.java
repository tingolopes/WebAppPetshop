package br.com.petshop.model;

import java.io.Serializable;

public enum FormaDePagamento implements Serializable {
    A_VISTA("À Vista"),
    BOLETO_BANCARIO("Boleto Bancário"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    CHEQUE("Cheque"),
    DEPOSITO_BANCARIO("Depósito Bancário");

    private final String DESCRICAO;

    private FormaDePagamento(String descricao) {
        this.DESCRICAO = descricao;
    }

    public String getDescricao() {
        return DESCRICAO;
    }
}
