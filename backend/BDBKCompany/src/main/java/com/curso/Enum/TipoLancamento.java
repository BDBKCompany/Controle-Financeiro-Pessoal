package com.curso.Enum;

import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.Locale;

public enum TipoLancamento {

    PAGAR(Fluxo.DESPESA, Sinal.DEBITO, "PAGAR"),

    RECEBER(Fluxo.RECEITA, Sinal.CREDITO, "RECEBER");

    public enum Fluxo {DESPESA, RECEITA}
    public enum Sinal {DEBITO, CREDITO}

    private final Fluxo fluxo;

    private final Sinal sinal;

    private final String codigo;

    TipoLancamento(Fluxo fluxo, Sinal sinal, String codigo) {
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
    public boolean isPagar() {
        return fluxo == Fluxo.DESPESA;
    }
    public boolean isReceber() {
        return fluxo == Fluxo.RECEITA;
    }
    public BigDecimal aplicaAoSaldo(BigDecimal saldoAtual, BigDecimal valor) {
        if (saldoAtual == null) saldoAtual = BigDecimal.ZERO;
        if (valor == null) valor = BigDecimal.ZERO;
        return (sinal == Sinal.DEBITO)
                ? saldoAtual.subtract(valor)
                : saldoAtual.add(valor);
    }

    public static TipoLancamento getTipoLancamento(String raw) {
        if(raw ==  null) return null;
        String s = raw.trim().toUpperCase(Locale.ROOT);
        switch (s) {
            case "PAGAR": return TipoLancamento.PAGAR;
            case "RECEBER": return TipoLancamento.RECEBER;
            default: throw new IllegalArgumentException("Tipo Lançmento Inválido: " + raw);
        }
    }
}