package br.com.petshop.model;

import java.io.Serializable;

public enum FormaDePagamento implements Serializable {
    DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, CHEQUE, BOLETO_BANCARIO, DEPOSITO_BANCARIO;
}