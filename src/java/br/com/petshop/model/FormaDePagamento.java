package br.com.petshop.model;

import java.io.Serializable;

public enum FormaDePagamento implements Serializable {
    CHEQUE("Cheque"), 
    DINHEIRO("Dinheiro"), 
    CARTAO_DEBITO("Cartao de Débito"), 
    BOLETO_BANCARIO("Boleto Bancário"), 
    CARTAO_CREDITO("Cartão de Crédito"), 
    DEPOSITO_BANCARIO("Depósito Bancário");
    
    private final String DESCRICAO;

    private FormaDePagamento(String descricao) {
        this.DESCRICAO = descricao;
    }
    
    public String getDescricao(){
        return DESCRICAO;
    }
}
