package com.curso.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.Locale;

public enum TipoTransacao {

    DEBITO(Fluxo.DESPESA, Sinal.DEBITO, "DEBITO"),
    CREDITO(Fluxo.RECEITA, Sinal.CREDITO, "CREDITO"),
    TRANSFERENCIA(Fluxo.TRANSFERENCIA, Sinal.NEUTRO, "TRANSFERENCIA");

    public enum Fluxo {DESPESA, RECEITA, TRANSFERENCIA}
    public enum Sinal {DEBITO, CREDITO, NEUTRO}

    private final Fluxo fluxo;
    private final Sinal sinal;
    private final String codigo;

    TipoTransacao(Fluxo fluxo, Sinal sinal, String codigo) {
        this.fluxo = fluxo;
        this.sinal = sinal;
        this.codigo = codigo;
    }

    public Fluxo getFluxo() {
        return fluxo;
    }

    public Sinal getSinal() {
        return sinal;
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    public boolean isDebito() {
        return sinal == Sinal.DEBITO;
    }

    public boolean isCredito() {
        return sinal == Sinal.CREDITO;
    }

    public boolean isTransferencia() {
        return fluxo == Fluxo.TRANSFERENCIA;
    }

    public BigDecimal aplicaAoSaldo(BigDecimal saldoAtual, BigDecimal valor) {
        if (saldoAtual == null) saldoAtual = BigDecimal.ZERO;
        if (valor == null) valor = BigDecimal.ZERO;

        switch (sinal) {
            case DEBITO: return saldoAtual.subtract(valor);
            case CREDITO: return saldoAtual.add(valor);
            default: return saldoAtual;
        }
    }

    public static TipoTransacao getTipoTransacao(String raw) {
        if(raw == null) return null;
        String s = raw.trim().toUpperCase(Locale.ROOT);
        switch (s) {
            case "DEBITO": return DEBITO;
            case "CREDITO": return CREDITO;
            case "TRANSFERENCIA": return TRANSFERENCIA;
            default: throw new IllegalArgumentException("Tipo de transação inválido: " + raw);
        }
    }
}
