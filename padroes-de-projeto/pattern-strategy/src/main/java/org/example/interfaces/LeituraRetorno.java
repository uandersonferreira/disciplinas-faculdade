package org.example.interfaces;

import org.example.context.Boleto;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface LeituraRetorno {
    DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public List<Boleto> lerArquivo(URI caminhoArquivo);
}
/*
    Variáveis por padrão são public e finais
 */