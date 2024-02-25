package org.example.context;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.example.interfaces.LeituraRetorno;

import java.beans.JavaBean;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Boleto {

    private int id;
    private String codBanco;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private String cpfCliente;
    private double valor;
    private double multa;
    private double juros;
    private String agencia;
    private String contaBancaria;

    @Override
    public String toString() {
        return "Boleto{" +
                "id=" + id +
                ", codBanco='" + codBanco + '\'' +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                ", cpfCliente='" + cpfCliente + '\'' +
                ", valor=" + valor +
                ", multa=" + multa +
                ", juros=" + juros +
                ", agencia='" + agencia + '\'' +
                ", contaBancaria='" + contaBancaria + '\'' +
                '}';
    }
}
