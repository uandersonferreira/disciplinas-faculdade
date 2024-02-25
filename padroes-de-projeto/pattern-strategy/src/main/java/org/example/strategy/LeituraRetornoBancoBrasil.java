package org.example.strategy;

import org.example.context.Boleto;
import org.example.interfaces.LeituraRetorno;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBancoBrasil implements LeituraRetorno {
    public LeituraRetornoBancoBrasil() {
    }

    @Override
    public List<Boleto> lerArquivo(URI caminhoArquivo) {
        Path path = Paths.get(caminhoArquivo);
        try {
            List<String> listaLinhas = Files.readAllLines(path);
            List<Boleto> listaBoletos = new ArrayList<>();

            for (String linha : listaLinhas) {
                String[] vetorLinhas = linha.split(";");
                Boleto boleto = new Boleto();

                boleto.setId(Integer.parseInt(vetorLinhas[0]));
                boleto.setCodBanco(vetorLinhas[1]);
                boleto.setDataVencimento(LocalDate.parse(vetorLinhas[2], FORMATO_DATA));
                boleto.setDataPagamento(LocalDate.parse(vetorLinhas[3], FORMATO_DATA).atTime(0, 0, 0));
                boleto.setCpfCliente(vetorLinhas[4]);
                boleto.setValor(Double.parseDouble(vetorLinhas[5]));
                boleto.setMulta(Double.parseDouble(vetorLinhas[6]));
                boleto.setJuros(Double.parseDouble(vetorLinhas[7]));

                listaBoletos.add(boleto);
            }

            return listaBoletos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
