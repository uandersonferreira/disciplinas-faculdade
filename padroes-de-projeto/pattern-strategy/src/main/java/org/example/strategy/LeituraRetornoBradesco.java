package org.example.strategy;

import org.example.context.Boleto;
import org.example.interfaces.LeituraRetorno;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LeituraRetornoBradesco implements LeituraRetorno {
    // Create a Logger
    Logger logger = Logger.getLogger(LeituraRetornoBradesco.class.getName());
    @Override
    public List<Boleto> lerArquivo(URI caminhoArquivo) {
        Path path = Paths.get(caminhoArquivo);

        Boleto boleto = new Boleto();
        List<Boleto> listaBoletos = new ArrayList<>();
        try {
            List<String> listaLinhas = Files.readAllLines(path);
            for (String linha : listaLinhas) {
                String[] vetorLinhas = linha.trim().split(";");

                boleto.setId(Integer.parseInt(vetorLinhas[0]));
                boleto.setCodBanco(vetorLinhas[1]);
                boleto.setAgencia(vetorLinhas[2]);
                boleto.setContaBancaria(vetorLinhas[3]);
                boleto.setDataVencimento(LocalDate.parse(vetorLinhas[4], FORMATO_DATA));
                boleto.setDataPagamento(LocalDateTime.parse(vetorLinhas[5],FORMATO_DATA_HORA));
                boleto.setCpfCliente(formatarCPF(vetorLinhas[6]));
                boleto.setValor(Double.parseDouble(vetorLinhas[7]));
                boleto.setMulta(Double.parseDouble(vetorLinhas[8]));
                boleto.setJuros(Double.parseDouble(vetorLinhas[9]));

                listaBoletos.add(boleto);
            }

            return listaBoletos;
        } catch (IOException e) {
            logger.info("Erro ao ler os dados do bradesco...");
            e.printStackTrace();
        }
        return null;
    }

    public  String formatarCPF(String cpf){
        String cpfCompleto = leftPad(cpf, 11, '0');
        return cpfCompleto.substring(0,3)+"."+cpfCompleto.substring(3,6)+"."+cpfCompleto.substring(6,9)+"-"+cpfCompleto.substring(9,11);
    }

    private String leftPad(String texto, Integer tamanho, Character caracter){
        if(texto.length() < tamanho){
            StringBuilder sb = new StringBuilder(texto);
            for(int cont = 0; cont < (tamanho-texto.length()); cont ++){
                sb.insert(0, caracter);
            }
            return sb.toString();
        }
        return texto;
    }
    //Solução: https://pt.stackoverflow.com/questions/277808/string-format-cpf-no-android
}//class
